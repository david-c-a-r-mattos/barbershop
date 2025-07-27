/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.Helpers.LoginHelper;
import ModelDAO.Database;
import ModelDAO.UserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import model.User;
import view.Login;
import view.PrincipalMenu;
/**
 *
 * @author david
 */
public class LoginController {

    private final Login view;
    private final LoginHelper helper;

    public LoginController(Login view) {
        this.view = view;
        this.helper = new LoginHelper(view);
        
    }
    public void enterInTheSystem(Connection connection)
    {
        User user = helper.getModel();
        connection = Database.getConnection();
        System.out.println(connection);
        UserDAO userdao = new UserDAO(connection);
        try 
        {
            User authenticateuser = userdao.authenticate(user.getName(), user.getPassword());
            if(authenticateuser != null)
            {
                PrincipalMenu menu = new PrincipalMenu();
                menu.setVisible(true);
                this.view.dispose();
            }
            else
            {
                view.showMessage("Usuário e senha inválidos!");
            }
        } 
        catch (SQLException ex) 
        {
            System.getLogger(LoginController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    public void didTask()
    {
        System.out.println("Busquei algo no banco de dados!");
        this.view.showMessage("Executei o método didTask");
    }
}
