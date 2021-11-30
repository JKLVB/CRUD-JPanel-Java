/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import config.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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

    public void autenticar(){
        try {
            String cpf = view.getjTextFieldCpf().getText();
            String senha = view.getjPasswordField().getText();
            Usuario objUsuario = new Usuario(cpf, senha);

            Connection conexao = new Conexao().getConnection();
            UsuarioDAO objDao = new UsuarioDAO(conexao);

            objDao.autenticar(objUsuario);

            if(objDao.autenticar(objUsuario)){
                ViewMenu telaDeMenu = new ViewMenu();
                telaDeMenu.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "Email ou CPF inválido");
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
