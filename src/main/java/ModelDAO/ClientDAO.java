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
import java.sql.Statement;

public class ClientDAO 
{
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo cliente no banco de dados
    /*public void create(Client client) throws SQLException {
        String sql = "INSERT INTO clients (cep, name, borndt, phone, rg, address) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //stmt.setInt(1, client.getId());
            stmt.setString(1, client.getName());
            stmt.setDate(2, (Date) client.getBorndt());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getRg());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getCep());
            stmt.execute();
        }
    }*/
    public void create(Client client) throws SQLException 
    {
        // REMOVA o ID da query de inserção
        String sql = "INSERT INTO clients (name, borndt, phone, rg, address, cep) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Índices agora começam em 1 (sem o ID)
            stmt.setString(1, client.getName());
            stmt.setDate(2, client.getBorndt() != null ? 
                        new java.sql.Date(client.getBorndt().getTime()) : null);
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getRg());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getCep());

            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1)); // Atualiza o objeto com o novo ID
                }
            }
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
    public void update(Client client) throws SQLException 
    {
        String sql = "UPDATE clients SET name = ?, borndt = ?, phone = ?, rg = ?, address = ?, cep = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
            stmt.setString(1, client.getName());

            // Convert java.util.Date → java.sql.Date
            if (client.getBorndt() != null) 
            {
                java.sql.Date sqlDate = new java.sql.Date(client.getBorndt().getTime());
                stmt.setDate(2, sqlDate);
            } else 
            {
                stmt.setNull(2, java.sql.Types.DATE); // Handle NULL case
            }
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getRg());
            stmt.setString(5, client.getAddress());
            stmt.setString(6, client.getCep());
            stmt.setInt(7, client.getId());
            stmt.execute();
        }
    }

    // Método para deletar um cliente
    public void delete(int id) throws SQLException 
    {
        String sql = "DELETE FROM clients WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    // Método para listar todos os clientes
    public List<Client> listAll() throws SQLException 
    {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients ORDER BY id";
        
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
    public List<Client> searchByName(String name) throws SQLException 
    {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients WHERE name LIKE ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) 
        {
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
