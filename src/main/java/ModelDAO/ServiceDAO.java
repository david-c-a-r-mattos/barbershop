/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;

import model.Service;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO {
    private final Connection connection;

    public ServiceDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo serviço
    public void create(Service service) throws SQLException {
        String sql = "INSERT INTO services (id, description, value) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, service.getId());
            stmt.setString(2, service.getDescription());
            stmt.setFloat(3, service.getValue());
            stmt.execute();
        }
    }

    // Método para buscar um serviço pelo ID
    public Service read(int id) throws SQLException {
        String sql = "SELECT * FROM services WHERE id = ?";
        Service service = null;
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                service = new Service(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getFloat("value")
                );
            }
        }
        return service;
    }

    // Método para atualizar um serviço
    public void update(Service service) throws SQLException {
        String sql = "UPDATE services SET description = ?, value = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, service.getDescription());
            stmt.setFloat(2, service.getValue());
            stmt.setInt(3, service.getId());
            stmt.execute();
        }
    }

    // Método para deletar um serviço
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM services WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    // Método para listar todos os serviços
    public List<Service> listAll() throws SQLException {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getFloat("value")
                );
                services.add(service);
            }
        }
        return services;
    }

    // Método para buscar serviços por descrição (usando LIKE)
    public List<Service> searchByDescription(String description) throws SQLException {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services WHERE description LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + description + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getFloat("value")
                );
                services.add(service);
            }
        }
        return services;
    }

    // Método para buscar serviços por faixa de valor
    public List<Service> findByValueRange(float minValue, float maxValue) throws SQLException {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services WHERE value BETWEEN ? AND ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, minValue);
            stmt.setFloat(2, maxValue);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Service service = new Service(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getFloat("value")
                );
                services.add(service);
            }
        }
        return services;
    }
}
