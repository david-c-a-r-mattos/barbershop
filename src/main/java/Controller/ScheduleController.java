/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.ScheduleHelper;
import ModelDAO.Database;
import ModelDAO.ScheduleDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Schedule;
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
}

