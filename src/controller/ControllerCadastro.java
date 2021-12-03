package controller;

import config.Conexao;
import java.sql.SQLException;
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
        try {
            String nome = view.getjTextFieldNome().getText();
            String cpf = view.getjTextFieldCPF().getText();
            String senha = view.getjPasswordField1().getText();
            String telefone = view.getjTextFieldTelefone().getText();
            String cargo = view.getBtnGroupCargo().getSelection().getActionCommand();
            String sexo = view.getBtnGroupSexo().getSelection().getActionCommand();
            
            Usuario objUsuario = new Usuario(nome, cpf, senha, telefone, cargo, sexo);
            UsuarioDAO objDao = new UsuarioDAO(new Conexao().getConnection());
            boolean result = objDao.insert(objUsuario);
            String msg = result ? "CPF ou Telefone já está sendo utilizado" : "Usuário cadastrado com sucesso!";
            JOptionPane.showMessageDialog(null, msg);

            if(!result){
                this.view.dispose();
            }
            
          } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
      }
    }
    
}
