/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;



import model.Client;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class ClientDAO 
{
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo cliente no banco de dados
    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO clients (id, name, borndt, phone, rg, address, cep) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, client.getId());
            stmt.setString(2, client.getName());
            stmt.setDate(3, (Date) client.getBorndt());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getRg());
            stmt.setString(6, client.getAddress());
            stmt.setString(7, client.getCep());
            stmt.execute();
        }
    }

    // Método para buscar um cliente pelo ID
    public Client read(int id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id = ?";
        Client client = null;
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                client = new Client(
                    rs.getString("cep"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("borndt"),
                    rs.getString("phone"),
                    rs.getString("rg"),
                    rs.getString("address")
                );
            }
        }
        return client;
    }

    // Método para atualizar um cliente
    public void update(Client client) throws SQLException {
        String sql = "UPDATE clients SET name = ?, borndt = ?, phone = ?, rg = ?, address = ?, cep = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setDate(2, (Date) client.getBorndt());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getRg());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getCep());
            stmt.setInt(7, client.getId());
            stmt.execute();
        }
    }

    // Método para deletar um cliente
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    // Método para listar todos os clientes
    public List<Client> listAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Client client = new Client(
                    rs.getString("cep"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("borndt"),
                    rs.getString("phone"),
                    rs.getString("rg"),
                    rs.getString("address")
                );
                clients.add(client);
            }
        }
        return clients;
    }

    // Método para buscar clientes por nome (usando LIKE)
    public List<Client> searchByName(String name) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE name LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Client client = new Client(
                    rs.getString("cep"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("borndt"),
                    rs.getString("phone"),
                    rs.getString("rg"),
                    rs.getString("address")
                );
                clients.add(client);
            }
        }
        return clients;
    }
}
