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
         
            Connection conexao = new Conexao().getConnection();
            ArrayList<Usuario> usuarios = new UsuarioDAO(conexao).findAll();
            this.lista = usuarios;
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
     
    public void atualizarLista() throws SQLException {
        DefaultTableModel modeloTabela =  (DefaultTableModel) view.getjTableUsuarios().getModel();
        modeloTabela.addRow(new String[modeloTabela.getColumnCount()]);
         consultar();
         modeloTabela.setRowCount(0);
         for(Usuario user: lista) {
            if(user.getId() != 0){
                
            
            modeloTabela.addRow(new Object[] {
                    user.getId(),
                    user.getNome(),
                    user.getCpf(),
                    user.getTelefone(),
                    user.getCargo(),
                    user.getSexo()
            });
            }
        }
    }
    
    public void deletar() throws SQLException{
        if(view.getjTableUsuarios().getSelectedRow()>= 0){

            
            
            int[] rows = view.getjTableUsuarios().getSelectedRows();
            ArrayList<Integer> listaID = new ArrayList();
            for(int i:rows){
                listaID.add((Integer) view.getjTableUsuarios().getModel().getValueAt(i, 0));
            }
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuario = new UsuarioDAO(conexao);
            
           usuario.delete(listaID);
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione O campo para exclusão");
        }
  }   
}