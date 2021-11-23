package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
    
    private Connection connection;
    
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public boolean insert(Usuario usuario) throws SQLException{
        if(this.autenticarInsert(usuario)){
            return true;
        }
        String sqlInsert = "insert into usuario (nome, cpf, senha, telefone, cargo) values (?, ?, ?, ?, ?)";
        PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
        statementInsert.setString(1, usuario.getNome());
        statementInsert.setString(2, usuario.getCpf());
        statementInsert.setString(3, usuario.getSenha());
        statementInsert.setString(4, usuario.getTelefone());
        statementInsert.setString(5, usuario.getCargo());
        statementInsert.execute();
        return false;
    }
    
    public void update(Usuario usuario) throws SQLException{
        
        String sql = "update usuario set nome = ?, cpf = ?, senha = ?, telefone = ?, cargo = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getSenha());
        statement.setString(4, usuario.getTelefone());
        statement.setString(5, usuario.getCargo());
        statement.setInt(6, usuario.getId());
        statement.execute();
    }
    
    public void delete(Usuario usuario) throws SQLException{
        
        String sql = "delete from usuario where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setInt(1, usuario.getId());
        statement.execute();
    }
    
    public ArrayList<Usuario> findAll() throws SQLException{
        String sql = "select * from usuario";
        PreparedStatement statement = connection.prepareStatement(sql);
        return pesquisa(statement);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String nome = resultSet.getString("nome");
            String cpf = resultSet.getString("cpf");
            String senha = resultSet.getString("senha");
            String telefone = resultSet.getString("telefone");
            String cargo = resultSet.getString("cargo");
            usuarios.add(new Usuario(id, nome, cpf, senha, telefone, cargo));
        }
        return usuarios;
    }
    
    public ArrayList<Usuario> findByNome(Usuario usuario) throws SQLException{
        String sql = "select * from usuario where nome = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        return pesquisa(statement);
    }

    public boolean autenticar(Usuario usuario) throws SQLException {
        String sql = "select * from usuario where cpf = ? and senha = ? and cargo = 'administrador'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getCpf());
        statement.setString(2, usuario.getSenha());
        statement.execute();
        return statement.getResultSet().next();
    }
    
    public boolean autenticarInsert(Usuario usuario) throws SQLException {
        String sql = "select * from usuario where cpf = ? or senha = ? or telefone = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getCpf());
        statement.setString(2, usuario.getSenha());
        statement.setString(3, usuario.getTelefone());
        statement.execute();
        return statement.getResultSet().next();
    }
}