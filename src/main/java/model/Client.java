/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    @Override
    public String toString()
    {
        return getName();
    }
    
}
