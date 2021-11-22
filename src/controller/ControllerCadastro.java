package controller;

import config.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import model.UsuarioDAO;
import view.ViewCadastro;

public class ControllerCadastro {
    
    private ViewCadastro view;

    public ControllerCadastro(ViewCadastro view) {
        this.view = view;
    }
    
    public void salvar(){
        
        String nome = view.getjTextFieldNome().getText();
        String cpf = view.getjTextFieldCPF().getText();
        String email = view.getjTextFieldEmail().getText();
        String telefone = view.getjTextFieldTelefone().getText();
        String cargo = view.getBtnGroupCargo().getSelection().getActionCommand();
        
        Usuario objUsuario = new Usuario(nome, cpf, email, telefone, cargo);
        
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO objDao = new UsuarioDAO(conexao);
            objDao.insert(objUsuario);
            
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
