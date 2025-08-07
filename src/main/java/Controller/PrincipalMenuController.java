/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import view.PrincipalMenu;
import view.Scheduling;

/**
 *
 * @author david
 */
public class PrincipalMenuController 
{
    public PrincipalMenu view;

    public PrincipalMenuController(PrincipalMenu view) {
        this.view = view;
    }
    
    public void navegateToSchedule()
    {
        Scheduling scheduling = new Scheduling();
        scheduling.setVisible(true);
    }

    public void navegateToClients() 
    {
        view.Clients clients = new view.Clients();
        clients.setVisible(true);
    }
}
