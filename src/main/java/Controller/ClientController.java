/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.ClientHelper;
import ModelDAO.ClientDAO;
import ModelDAO.Database;
import ModelDAO.ScheduleDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.Client;
import view.Clients;

public class ClientController {
    private final Connection connection = Database.getConnection();
    private final view.Client view;
    private final ClientHelper helper;
    private final ClientsController clientscontroller;
    private final view.Clients clientsview;
    
    public ClientController(view.Client view, view.Clients clientsview) {
        this.view = view;
        this.clientsview = clientsview;
        this.clientscontroller = new ClientsController(clientsview);
        this.helper = new ClientHelper(clientsview, view);
    }
    
    public void navegateToClient() {
        view.Client client = view;
        client.setVisible(true);
    }
    
    public void saveFields() {
        try {
            ClientDAO clientDAO = new ClientDAO(connection);
            model.Client client = helper.getModel(); // Isso valida os campos
            
            clientDAO.update(client);
            
            JOptionPane.showMessageDialog(view, 
                "Cliente salvo com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            view.setVisible(false);
            clientscontroller.updateTable();
            clientsview.setVisible(true);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao atualizar cliente no banco de dados: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, 
                ex.getMessage(), 
                "Erro de validação", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void editClient(int id) {
        try {
            ClientDAO clientDAO = new ClientDAO(connection);
            model.Client client = clientDAO.read(id);
            
            if (client == null) {
                JOptionPane.showMessageDialog(view, 
                    "Cliente não encontrado com ID: " + id, 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            helper.fillFields(client, clientsview);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao carregar cliente do banco de dados: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteClient(int id) {
        try {
            // Confirmar exclusão
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Tem certeza que deseja excluir este cliente e todos os seus agendamentos?", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            
            ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
            scheduleDAO.deleteForIdClient(id);
            
            ClientDAO clientDAO = new ClientDAO(connection);
            clientDAO.delete(id);
            
            JOptionPane.showMessageDialog(view, 
                "Cliente e agendamentos excluídos com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            clientscontroller.updateTable();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao excluir cliente: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}