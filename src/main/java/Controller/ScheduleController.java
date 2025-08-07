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
import javax.swing.JOptionPane;
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
            helper.fillServices(arrayList);
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
        if (!validateFields()) 
        {
            return;
        }
        try 
        {
            Schedule schedule = helper.getModel();
            ScheduleDAO scheduleDAO = new ScheduleDAO(connection);
            new ScheduleDAO(connection).create(schedule);
            JOptionPane.showMessageDialog(null, "Agendamento realizado com sucesso!");
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao agendar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        updateTable();
        helper.clearScreen();
    }
    public boolean validateFields() 
    {
        // Validar valor
        String valorText = view.getjTextValue().getText().trim();
        if (valorText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Valor não pode estar vazio", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            double valor = Double.parseDouble(valorText);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(null, "Valor deve ser maior que zero", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido (use números com . para decimal)", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar data (formato básico)
        String dateText = view.getjTextDate().getText().trim();
        if (dateText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data não pode estar vazia", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!dateText.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Data no formato inválido (use DD/MM/AAAA)", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validar hora (formato básico)
        String timeText = view.getjTextTime().getText().trim();
        if (timeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hora não pode estar vazia", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!timeText.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Hora no formato inválido (use HH:MM)", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
}

