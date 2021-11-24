package controller;

import config.Conexao;
import java.sql.Connection;
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
            System.out.println(cargo + sexo);
            Usuario objUsuario = new Usuario(nome, cpf, senha, telefone, cargo, sexo);

            Connection conexao = new Conexao().getConnection();
            UsuarioDAO objDao = new UsuarioDAO(conexao);
            boolean result = objDao.insert(objUsuario);

            JOptionPane.showMessageDialog(null, result ?
                    "O CPF, Email ou Telefone já está sendo utilizado" :
                    "Usuário cadastrado com sucesso!");
            
          } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
      }
    }
}
