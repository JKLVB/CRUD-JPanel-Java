package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static String JDBC = "jdbc:postgresql://localhost:5432/projeto?currentSchema=cadastro";
    
    public Connection getConnection() throws SQLException{
        try{
            return DriverManager.getConnection(JDBC, "postgres", "123456");
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage().toString());
        }
    }
}