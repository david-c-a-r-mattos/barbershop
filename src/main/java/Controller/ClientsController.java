/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.ClientsHelper;
import ModelDAO.ClientDAO;
import ModelDAO.Database;
import ModelDAO.ScheduleDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Client;
import model.Schedule;
import view.Clients;

/**
 *
 * @author david
 */
public class ClientsController 
{
    private final Clients view;
    private final ClientsHelper helper;
    private final Connection connection = Database.getConnection();
    public ClientsController(Clients view) 
    {
        this.view = view;
        this.helper = new ClientsHelper(view);
    }
    public void updateTable()
    {
        
        ClientDAO clientDAO = new ClientDAO(connection);
        try 
        {
            List<Client> arrayList = clientDAO.listAll();
            helper.fillTable(arrayList);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
