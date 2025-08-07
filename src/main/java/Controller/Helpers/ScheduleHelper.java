/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Schedule;
import model.Service;
import view.Scheduling;

/**
 *
 * @author david
 */
public class ScheduleHelper implements IHelper
{
    private final Scheduling view;

    public ScheduleHelper(Scheduling view) {
        this.view = view;
    }
    public void fillTable(List<Schedule> schedule)
    {
        DefaultTableModel tableModel = (DefaultTableModel) view.getjTable().getModel();
        tableModel.setNumRows(0);
        for(Schedule x : schedule)
        {
            tableModel.addRow(new Object[]
            {
                x.getId(),
                x.getClient().getName(),
                x.getService().getDescription(),
                x.getValue(),
                x.getDate(),
                x.getObservation()
            });
        }
    }
    public Client getClient()
    {
        return (Client) view.getjComboBoxClient().getSelectedItem();
    }
    public Service getService()
    {
        return (Service) view.getjComboBoxService().getSelectedItem();
    }
    public void fillClients(List<Client> clients) 
    {
        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjComboBoxClient().getModel();
        // 3. Popular o modelo
        for (Client client : clients) {
             model.addElement(client);
        }
    }

    public void fillServices(List<Service> services) 
    {
        DefaultComboBoxModel model = (DefaultComboBoxModel) view.getjComboBoxService().getModel();
        // 3. Popular o modelo
        for (Service service : services) {
             model.addElement(service);
        }
    }

    @Override
    public Schedule getModel() 
    {
        Client client = getClient();
        Service service = getService();
        String valueString = view.getjTextValue().getText();
        float value = Float.parseFloat(valueString);
        String date = view.getjTextDate().getText();
        String time = view.getjTextTime().getText();
        String dateTime = date+" "+time;
        String observation = view.getjTextAreaObservation().getText();
        Schedule schedule = new Schedule(client, service, value, dateTime, observation);
        return schedule;
    }

    @Override
    public void clearScreen() 
    {
        view.getjTextDate().setText("");
        view.getjTextTime().setText("");
        view.getjTextAreaObservation().setText("");
    }

    public void setValue(float value) 
    {
        view.getjTextValue().setText(value+"");
    }
    
}
