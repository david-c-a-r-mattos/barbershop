/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import model.User;
import view.Login;

/**
 *
 * @author david
 */
public class LoginHelper implements IHelper 
{
    private final Login view;

    public LoginHelper(Login view) 
    {
        this.view = view;
    }
    @Override
    public User getModel()
    {
        String name = view.getUserName().getText();
        char[] password = view.getUserPassword().getPassword();
        User model = new User(0, name, String.valueOf(password));
        java.util.Arrays.fill(password, '0');
        return model;
    }
    public void setModel(User model)
    {
        String name = model.getName();
        String password = model.getPassword();
        view.getUserName().setText(name);
        view.getUserName().setText(password);
    }
    @Override
    public void clearScreen()
    {
        view.getUserName().setText("");
    }
}
