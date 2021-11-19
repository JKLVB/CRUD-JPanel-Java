package model;

import config.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import view.Cadastro;

public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO(){};
    
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Usuario usuario) throws SQLException{
        String sql = "insert into usuario (nome, cpf, email, telefone, nascimento) values ("+usuario.getNome()+", "+usuario.getCpf()+", "+usuario.getEmail()+", "+usuario.getTelefone()+", "+usuario.getNascimento()+");";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        connection.close();
    }
}
