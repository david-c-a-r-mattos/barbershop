/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Schedule;
import view.Scheduling;

/**
 *
 * @author david
 */
public class ScheduleHelper 
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
}
