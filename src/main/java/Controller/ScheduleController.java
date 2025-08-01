/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.ScheduleHelper;
import ModelDAO.ClientDAO;
import ModelDAO.Database;
import ModelDAO.ScheduleDAO;
import ModelDAO.ServiceDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import model.Schedule;
import model.Service;
import view.Scheduling;

/**
 *
 * @author david
 */
public class ScheduleController 
{
    private final Scheduling view;
    private final ScheduleHelper helper;
    private final Connection connection = Database.getConnection();
    
    public ScheduleController(Scheduling view) {
        this.view = view;
        this.helper = new ScheduleHelper(view);
    }
    public void updateTable()
    {
        
        ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
        try 
        {
            List<Schedule> arrayList = scheduleDAO.listAll();
            helper.fillTable(arrayList);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateClient()
    {
        ClientDAO clientDAO = new ClientDAO(connection);
        try 
        {
            List<Client> arrayList = clientDAO.listAll();
            helper.fillClients(arrayList);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateService()
    {
        ServiceDAO serviceDAO = new ServiceDAO(connection);
        try 
        {
            List<Service> arrayList = serviceDAO.listAll();
            helper.fillService(arrayList);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateValue()
    {
        Service service = helper.getService();
        helper.setValue(service.getValue());
    }

    public void toSchedule()
    {
        Schedule schedule = helper.getModel();
        try 
        {
            new ScheduleDAO(connection).create(schedule);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateTable();
        helper.clearScreen();
    }
}

