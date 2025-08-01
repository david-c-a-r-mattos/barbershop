/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
            System.out.print(x);
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
    public void fillClients(List<Client> arrayList) 
    {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getjComboBoxClient().getModel();
        for(Client x : arrayList)
        {
            comboBoxModel.addElement(x);
        }
    }

    public void fillService(List<Service> arrayList) 
    {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getjComboBoxService().getModel();
        for(Service x : arrayList)
        {
            comboBoxModel.addElement(x);
        }
    }

    @Override
    public Schedule getModel() 
    {
        String idString = view.getjTextId().getText();
        int id = Integer.parseInt(idString);
        Client client = getClient();
        Service service = getService();
        String valueString = view.getjTextValue().getText();
        float value = Float.parseFloat(valueString);
        String date = view.getjTextDate().getText();
        String time = view.getjTextTime().getText();
        String dateTime = date+" "+time;
        String observation = view.getjTextAreaObservation().getText();
        Schedule schedule = new Schedule(id, client, service, value, dateTime, observation);
        return schedule;
    }

    @Override
    public void clearScreen() 
    {
        view.getjTextId().setText("");
        view.getjTextDate().setText("");
        view.getjTextTime().setText("");
        view.getjTextAreaObservation().setText("");
    }

    public void setValue(float value) 
    {
        view.getjTextValue().setText(value+"");
    }
    
}
