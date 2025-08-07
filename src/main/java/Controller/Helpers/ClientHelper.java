/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import view.Client;
import view.Clients;
/**
 *
 * @author david
 */
public class ClientHelper 
{
    public final Client view;
    public final Clients clientsview;

    public ClientHelper(Clients clientsview, Client view) {
        this.view = view;
        this.clientsview = clientsview;
    }
    
    public void fillFields(model.Client client, Clients clientsview) 
    {
        view.getjTextName().setText(client.getName());
        view.getjTextCep().setText(client.getCep());
        view.getjTextPhone().setText(client.getPhone());
        Date utilDate = client.getBorndt();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formatDate = sdf.format(utilDate);
        view.getjTextBorndt().setText(formatDate);
        view.getjTextRg().setText(client.getRg());
        view.getjTextAddress().setText(client.getAddress());
        int idInt = client.getId();
        String id = String.valueOf(idInt);
        view.getjTextId().setText(id);
        clientsview.setVisible(false);
        view.setVisible(true);
    }

  
    public model.Client getModel() 
    {
        String name = view.getjTextName().getText();
        String address = view.getjTextAddress().getText();
        String cep = view.getjTextCep().getText();
        String phone = view.getjTextPhone().getText();
        String borndt = view.getjTextBorndt().getText();
        String rg = view.getjTextRg().getText();
        String idString = view.getjTextId().getText();
        int id = Integer.parseInt(idString);
        return new model.Client(cep, id, name, borndt, phone, rg, address);
    }
}
