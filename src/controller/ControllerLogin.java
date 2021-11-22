/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.ViewLogin;
import view.ViewMenu;

/**
 *
 * @author Alvaro
 */
public class ControllerLogin {
    
    private ViewLogin view;

    public ControllerLogin(ViewLogin view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        
        String cpf = view.getjTextFieldCpf().getText();
        String email = view.getjTextFieldEmail().getText();
        
        Usuario objUsuario = new Usuario("Jonas", cpf, email, "222", "designer");
        
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO objDao = new UsuarioDAO(conexao);
        
        boolean result = objDao.autenticar(objUsuario);
        
        if(result){
            ViewMenu telaDeMenu = new ViewMenu();
            telaDeMenu.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Email ou CPF inválido");
        }
    }
}
