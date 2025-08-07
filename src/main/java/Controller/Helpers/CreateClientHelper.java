/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import view.CreateClient;

/**
 *
 * @author david
 */
public class CreateClientHelper 
{
    public CreateClient view;
    public CreateClientHelper(CreateClient view) 
    {
        this.view = view;
    }
    
    public model.Client getModel() 
    {
        String name = view.getjTextName().getText();
        String address = view.getjTextAddress().getText();
        String cep = view.getjTextCep().getText();
        String phone = view.getjTextPhone().getText();
        String borndt = view.getjTextBorndt().getText();
        String rg = view.getjTextRg().getText();
        model.Client client = new model.Client(cep, name, borndt, phone, rg, address);
        return client;
    }   
}
