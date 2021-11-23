/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.UsuarioDAO;
import view.ViewMenu;

/**
 *
 * @author alvarolima
 */
public class ControllerMenu {
    
    private ViewMenu view;

    public ControllerMenu(ViewMenu view) {
        this.view = view;
    }
    
    public void consultar() throws SQLException{
        try{
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO objDao = new UsuarioDAO(conexao);
            
            objDao.findAll();
            
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
