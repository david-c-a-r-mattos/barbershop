/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
abstract public class Person 
{
    protected int id;
    protected String name;
    protected Date borndt;
    protected String phone;
    protected String rg;
    protected String address;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(int id, String name, String borndt, String phone, String rg, String address) {
        this.id = id;
        this.name = name;
        try
        {
            this.borndt = new SimpleDateFormat("dd/mm/yyyy HH:mm").parse(borndt);
        }
        catch(ParseException ex)
        {
           Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.phone = phone;
        this.rg = rg;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorndt() {
        return borndt;
    }

    public void setBorndt(Date borndt) {
        this.borndt = borndt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

}
