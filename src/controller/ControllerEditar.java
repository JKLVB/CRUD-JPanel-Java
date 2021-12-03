package controller;

import config.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import model.Usuario;
import model.UsuarioDAO;
import view.ViewEditar;

public class ControllerEditar {
    
    private ViewEditar view;
    private Usuario usuario;
    
    public ControllerEditar(ViewEditar view, Integer id) {
        try{
            this.view = view;
            this.usuario = new UsuarioDAO(new Conexao().getConnection()).findByid(id);

            view.getjTextFieldNome().setText(usuario.getNome());
            view.getjTextFieldCPF().setText(usuario.getCpf());
            view.getjPasswordField1().setText(usuario.getSenha());
            view.getjTextFieldTelefone().setText(usuario.getTelefone());

            List<AbstractButton> listRadioButton = Collections.list(view.getBtnGroupCargo().getElements());

            for (AbstractButton button : listRadioButton) {
                if(((JRadioButton) button).getActionCommand().equals(usuario.getCargo())){
                    button.setSelected(true);
                }
            }
            
            List<AbstractButton> listRadioButton2 = Collections.list(view.getBtnGroupSexo().getElements());
            
            for (AbstractButton button : listRadioButton2) {
                if(((JRadioButton) button).getActionCommand().equals(usuario.getSexo())){
                    button.setSelected(true);
                }
            }
        } catch (SQLException e){
             System.out.println(e.getMessage());
        }
    }
    
    public void editar(){
        try {
            Integer id = usuario.getId();
            String nome = view.getjTextFieldNome().getText();
            String cpf = view.getjTextFieldCPF().getText();
            String senha = view.getjPasswordField1().getText();
            String telefone = view.getjTextFieldTelefone().getText();
            String cargo = view.getBtnGroupCargo().getSelection().getActionCommand();
            String sexo = view.getBtnGroupSexo().getSelection().getActionCommand();
            Usuario objUsuario = new Usuario(id, nome, cpf, senha, telefone, cargo, sexo);

            Connection conexao = new Conexao().getConnection();
            UsuarioDAO objDao = new UsuarioDAO(conexao);
            boolean result = objDao.update(objUsuario);
            
            JOptionPane.showMessageDialog(null, result ?
                    "Usuário editado com sucesso!":
                    "O CPF ou Telefone já está sendo utilizado");
          
           if(result){
                this.view.dispose();
            }           
          } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
      }
    }
}
