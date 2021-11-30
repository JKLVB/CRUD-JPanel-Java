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
        String sqlInsert = "insert into usuario (nome, cpf, senha, telefone, cargo, sexo) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
        statementInsert.setString(1, usuario.getNome());
        statementInsert.setString(2, usuario.getCpf());
        statementInsert.setString(3, usuario.getSenha());
        statementInsert.setString(4, usuario.getTelefone());
        statementInsert.setString(5, usuario.getCargo());
        statementInsert.setString(6, usuario.getSexo());
        statementInsert.execute();
        return false;
    }
    
    public boolean update(Usuario usuario) throws SQLException{
        
        String sql = "update usuario set nome = ?, cpf = ?, senha = ?, telefone = ?, cargo = ?, sexo = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getSenha());
        statement.setString(4, usuario.getTelefone());
        statement.setString(5, usuario.getCargo());
        statement.setString(6, usuario.getSexo());
        statement.setInt(7, usuario.getId());     
        int update = statement.executeUpdate();
        
        return update < 1 ? false : true;
    }
    
    public void delete(ArrayList<Integer> listaID) throws SQLException{
        for(Integer id: listaID){
            
        String sql = "delete from usuario where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setInt(1,id);
        statement.execute();
        }
    }
    
    public ArrayList<Usuario> findAll() throws SQLException{
        String sql = "select * from usuario order by id";
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
            String sexo = resultSet.getString("sexo");
            usuarios.add(new Usuario(id, nome, cpf, senha, telefone, cargo, sexo));
        }
        return usuarios;
    }
    
    public ArrayList<Usuario> findByNome(Usuario usuario) throws SQLException{
        String sql = "select * from usuario where nome = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        return pesquisa(statement);
    }
    
     public Usuario findByid(Integer id) throws SQLException{
        String sql = "select * from usuario where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return new Usuario(result.getInt("id"),result.getString("nome"),
                    result.getString("cpf"),result.getString("senha"),
                    result.getString("telefone"), result.getString("cargo"),
                    result.getString("sexo"));
        } else {
            return null;
        }
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