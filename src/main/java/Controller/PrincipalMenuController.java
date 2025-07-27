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
    private final PrincipalMenu view;
    public PrincipalMenuController(PrincipalMenu view) 
    {
        this.view = view;
    }
    public void navegateToSchedule()
    {
        Scheduling schedule = new Scheduling();
        schedule.setVisible(true);
    }
}
