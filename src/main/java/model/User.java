/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author david
 */
public class User extends Person
{
    private String password;
    private int accesslevel;

    public User(int id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    public User(String password, int accesslevel, int id, String name, String borndt, String phone, String rg, String address) {
        super(id, name, borndt, phone, rg, address);
        this.password = password;
        this.accesslevel = accesslevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(int accesslevel) {
        this.accesslevel = accesslevel;
    }
    
    

    
}
