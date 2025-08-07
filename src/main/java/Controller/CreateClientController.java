/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.CreateClientHelper;
import ModelDAO.ClientDAO;
import ModelDAO.Database;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.Clients;
import view.CreateClient;

public class CreateClientController {
    private final CreateClient view;
    private final CreateClientHelper helper;
    private final Clients clientsview;
    private final ClientsController clientscontroller;
    private final Connection connection = Database.getConnection();
    
    public CreateClientController(CreateClient view, Clients clientsview) {
        this.view = view;
        this.clientsview = clientsview;
        this.clientscontroller = new ClientsController(clientsview);
        this.helper = new CreateClientHelper(view);
    }
    public void navegateToCreateClient(CreateClient view)
    {
        view.CreateClient createclient = view;
        createclient.setVisible(true);
    }

    public void createClient(CreateClient view) {
        try {
            ClientDAO clientDAO = new ClientDAO(connection);
            model.Client client = helper.getModel();
            
            clientDAO.create(client);
            
            JOptionPane.showMessageDialog(view, 
                "Cliente criado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            helper.clearFields();
            view.setVisible(false);
            clientscontroller.updateTable();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao acessar o banco de dados: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, 
                ex.getMessage(), 
                "Erro de validação", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}