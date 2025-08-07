/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Client;
import view.Clients;

/**
 *
 * @author david
 */
public class ClientsHelper 
{
    private final Clients view;

    public ClientsHelper(Clients view) {
        this.view = view;
    }
    public void fillTable(List<Client> client)
    {
        DefaultTableModel tableModel = (DefaultTableModel) view.getjTable().getModel();
        tableModel.setNumRows(0);
        for(Client x : client)
        {
            tableModel.addRow(new Object[]
            {
                x.getId(),
                x.getName(),
                x.getAddress(),
                x.getCep(),
                x.getPhone(),
                x.getRg(),
                x.getBorndt()
            });
        }
    }

}
