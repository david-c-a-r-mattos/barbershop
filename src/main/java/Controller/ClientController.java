/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.ClientHelper;
import ModelDAO.ClientDAO;
import ModelDAO.Database;
import ModelDAO.ScheduleDAO;
import ch.qos.logback.classic.pattern.DateConverter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Client;

/**
 *
 * @author david
 */
public class ClientController 
{
    private final Connection connection = Database.getConnection();
    private final view.Client view;
    private final ClientHelper helper;
    private final ClientsController clientscontroller;
    private final view.Clients clientsview;
    public ClientController(view.Client view, view.Clients clientsview) 
    {
        this.view = view;
        this.clientsview = clientsview;
        this.clientscontroller = new ClientsController(clientsview);
        this.helper = new ClientHelper(clientsview, view);
    }
    
    public void navegateToClient()
    {
        view.Client client = view;
        client.setVisible(true);
    }
    public void saveFields()
    {
        try 
        {
            ClientDAO clientDAO = new ClientDAO(connection);
            Client client;
            client = helper.getModel();
            clientDAO.update(client);
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            view.setVisible(false);
            clientscontroller.updateTable();
            clientsview.setVisible(true);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editClient(int id) throws SQLException
    {
        
        ClientDAO clientDAO = new ClientDAO(connection);
        Client client = clientDAO.read(id);
        helper.fillFields(client, clientsview);
    }
    public void deleteClient(int id) throws SQLException 
    {
        ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
        scheduleDAO.deleteForIdClient(id);
        ClientDAO clientDAO = new ClientDAO(connection);
        clientDAO.delete(id);
        clientscontroller.updateTable();
    }
}
