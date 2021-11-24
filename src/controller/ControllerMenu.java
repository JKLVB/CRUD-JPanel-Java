/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import model.UsuarioDAO;
import view.ViewMenu;

/**
 *
 * @author alvarolima
 */
public class ControllerMenu {
    
    private ViewMenu view;
    public  ArrayList<Usuario> lista;
    
    public ControllerMenu(ViewMenu view) {
        this.view = view;
    }
    
    public void consultar() throws SQLException{
        try{
            System.out.println("TESTE ALIPIOOO3");
            Connection conexao = new Conexao().getConnection();
            ArrayList<Usuario> usuarios = new UsuarioDAO(conexao).findAll();
            System.out.println(usuarios.get(0).toString());
            this.lista = usuarios;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}