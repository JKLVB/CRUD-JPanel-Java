package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    
    private Connection connection;
    
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Usuario usuario) throws SQLException{
        String sql = "insert into usuario (nome, cpf, email, telefone, cargo) values ('"+usuario.getNome()+"', '"+usuario.getCpf()+"', '"+usuario.getEmail()+"', '"+usuario.getTelefone()+"', '"+usuario.getCargo()+"')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }

    public boolean autenticar(Usuario objUsuario) throws SQLException {
        
        String sql = "select * from usuario where email = '"+objUsuario.getEmail()+"' and cpf = '"+objUsuario.getCpf()+"'";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
        return resultSet.next();
    }
}
