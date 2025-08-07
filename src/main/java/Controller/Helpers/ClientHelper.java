/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import view.Client;
import view.Clients;

public class ClientHelper {
    public final Client view;
    public final Clients clientsview;

    public ClientHelper(Clients clientsview, Client view) {
        this.view = view;
        this.clientsview = clientsview;
    }
    
    public void fillFields(model.Client client, Clients clientsview) {
        try {
            view.getjTextName().setText(client.getName());
            view.getjTextCep().setText(formatCEP(client.getCep()));
            view.getjTextPhone().setText(formatPhone(client.getPhone()));
            
            Date utilDate = client.getBorndt();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formatDate = sdf.format(utilDate);
            view.getjTextBorndt().setText(formatDate);
            
            view.getjTextRg().setText(formatRG(client.getRg()));
            view.getjTextAddress().setText(client.getAddress());
            
            int idInt = client.getId();
            String id = String.valueOf(idInt);
            view.getjTextId().setText(id);
            
            clientsview.setVisible(false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, 
                "Erro ao carregar dados do cliente: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public model.Client getModel() throws Exception {
        // Validar e obter cada campo
        String name = validateName();
        String address = validateAddress();
        String cep = validateCEP();
        String phone = validatePhone();
        String borndt = validateBirthDate();
        String rg = validateRG();
        int id = validateID();
        
        return new model.Client(cep, id, name, borndt, phone, rg, address);
    }
    
    // Métodos de validação
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
        return rg.replaceAll("[^0-9]", ""); // Remove formatação
    }
    
    private int validateID() throws Exception {
        String idString = view.getjTextId().getText().trim();
        if (idString.isEmpty()) {
            throw new Exception("ID do cliente não encontrado");
        }
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new Exception("ID do cliente inválido");
        }
    }
    
    // Métodos auxiliares para formatação
    private String formatCEP(String cep) {
        if (cep == null || cep.length() != 8) return cep;
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }
    
    private String formatPhone(String phone) {
        if (phone == null) return phone;
        String digits = phone.replaceAll("[^0-9]", "");
        
        if (digits.length() == 10) {
            return "(" + digits.substring(0, 2) + ") " + digits.substring(2, 6) + "-" + digits.substring(6);
        } else if (digits.length() == 11) {
            return "(" + digits.substring(0, 2) + ") " + digits.substring(2, 7) + "-" + digits.substring(7);
        }
        return phone;
    }
    
    private String formatRG(String rg) {
        if (rg == null || rg.length() < 9) return rg;
        rg = rg.replaceAll("[^0-9]", "");
        return rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + 
               (rg.length() > 8 ? rg.substring(8, 9) : "X");
    }
}