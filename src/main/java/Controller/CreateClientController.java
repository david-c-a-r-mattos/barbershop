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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.Clients;
import view.CreateClient;

/**
 *
 * @author david
 */
public class CreateClientController 
{
    public CreateClient view;
    public CreateClientHelper helper;
    public Clients clientsview;
    public ClientsController clientscontroller;
    private final Connection connection = Database.getConnection();
    public CreateClientController(CreateClient view, Clients clientsview) 
    {
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

    public void createClient(CreateClient view) 
    {
        ClientDAO clientDAO = new ClientDAO(connection);
        model.Client client = helper.getModel();
        try 
        {
            clientDAO.create(client);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(CreateClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        view.setVisible(false);
        clientscontroller.updateTable();
    }
    public void navegateToCreateClient()
    {
        view.CreateClient createclient = view;
        createclient.setVisible(true);
    }
    
}
