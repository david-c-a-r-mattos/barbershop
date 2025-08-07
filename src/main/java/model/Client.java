/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author david
 */
public class Client extends Person
{
    private String cep;

    public Client(int id, String name, String address, String cep) 
    {
        super(id, name);
        this.address = address;
        this.cep = cep;
    }

    public Client(String cep, int id, String name, String borndt, String phone, String rg, String address) {
        super(id, name, borndt, phone, rg, address);
        this.address = address;
        this.cep = cep;
    }

    public Client(String cep, String name, String borndt, String phone, String rg, String address) {
        super(name, borndt, phone, rg, address);
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
    
    @Override
    public String toString()
    {
        return getName();
    }
    
}
