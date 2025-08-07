/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import view.CreateClient;

public class CreateClientHelper {
    private final CreateClient view;
    
    public CreateClientHelper(CreateClient view) {
        this.view = view;
    }
   
    
    public model.Client getModel() throws Exception {
        // Validar e obter cada campo
        String name = validateName();
        String address = validateAddress();
        String cep = validateCEP();
        String phone = validatePhone();
        String borndt = validateBirthDate();
        String rg = validateRG();
        
        return new model.Client(cep, name, borndt, phone, rg, address);
    }
    
    private String validateName() throws Exception {
        String name = view.getjTextName().getText().trim();
        if (name.isEmpty()) {
            throw new Exception("O campo Nome é obrigatório");
        }
        if (name.length() < 3) {
            throw new Exception("O nome deve ter pelo menos 3 caracteres");
        }
        return name;
    }
    
    private String validateAddress() throws Exception {
        String address = view.getjTextAddress().getText().trim();
        if (address.isEmpty()) {
            throw new Exception("O campo Endereço é obrigatório");
        }
        if (address.length() < 5) {
            throw new Exception("O endereço deve ter pelo menos 5 caracteres");
        }
        return address;
    }
    
    private String validateCEP() throws Exception {
        String cep = view.getjTextCep().getText().trim().replaceAll("[^0-9]", "");
        if (cep.isEmpty()) {
            throw new Exception("O campo CEP é obrigatório");
        }
        if (cep.length() != 8) {
            throw new Exception("CEP deve ter 8 dígitos");
        }
        return cep;
    }
    
    private String validatePhone() throws Exception {
        String phone = view.getjTextPhone().getText().trim().replaceAll("[^0-9]", "");
        if (phone.isEmpty()) {
            throw new Exception("O campo Telefone é obrigatório");
        }
        if (phone.length() < 10 || phone.length() > 11) {
            throw new Exception("Telefone deve ter 10 ou 11 dígitos (com DDD)");
        }
        return phone;
    }
    
    private String validateBirthDate() throws Exception {
        String borndt = view.getjTextBorndt().getText().trim();
        if (borndt.isEmpty()) {
            throw new Exception("O campo Data de Nascimento é obrigatório");
        }
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date date = sdf.parse(borndt);
            
            // Verificar se a data é no futuro
            if (date.after(new Date())) {
                throw new Exception("Data de nascimento não pode ser no futuro");
            }
        } catch (ParseException e) {
            throw new Exception("Data de nascimento inválida. Use o formato DD/MM/AAAA");
        }
        
        return borndt;
    }
    
    private String validateRG() throws Exception {
        String rg = view.getjTextRg().getText().trim();
        if (rg.isEmpty()) {
            throw new Exception("O campo RG é obrigatório");
        }
        if (rg.length() < 8) {
            throw new Exception("RG deve ter pelo menos 8 caracteres");
        }
        return rg;
    }
    
    public void clearFields() {
        view.getjTextName().setText("");
        view.getjTextAddress().setText("");
        view.getjTextCep().setText("");
        view.getjTextPhone().setText("");
        view.getjTextBorndt().setText("");
        view.getjTextRg().setText("");
    }  
}
