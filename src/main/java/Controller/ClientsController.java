/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.ClientHelper;
import Controller.Helpers.ClientsHelper;
import ModelDAO.ClientDAO;
import ModelDAO.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Client;

/**
 *
 * @author david
 */
public class ClientsController 
{
    private view.Clients clientsview;
    private final ClientsHelper clientshelper;
    private final view.Client clientview;
    private final ClientHelper clienthelper;
    private final Connection connection = Database.getConnection();
    public ClientsController(view.Clients view, view.Client clientview) 
    {
        this.clientsview = view;
        this.clientshelper = new ClientsHelper(view);
        this.clientview = clientview;
        this.clienthelper = new ClientHelper(view, clientview);
    }
    public void updateTable()
    {
        
        ClientDAO clientDAO = new ClientDAO(connection);
        try 
        {
            List<Client> arrayList = clientDAO.listAll();
            clientshelper.fillTable(arrayList);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void editClient(int id)
    {
        try 
        {
            ClientDAO clientDAO = new ClientDAO(connection);
            Client client = clientDAO.read(id);
            clienthelper.fillFields(client, clientsview);
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saveFields(int id)
    {
        try 
        {
            ClientDAO clientDAO = new ClientDAO(connection);
            Client client = clienthelper.getModel();
            clientDAO.update(client);
            clientsview = new view.Clients();
            JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            clientview.setVisible(false);
            clientsview.setVisible(true);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteClient(int id) 
    {
        // Implemente a lógica de exclusão aqui
        System.out.println("Excluindo cliente com ID: " + id);
        // Atualize a tabela após a exclusão
        updateTable();
    }
}
