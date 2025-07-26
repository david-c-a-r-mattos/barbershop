/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;

import model.User;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo usuário no banco de dados
    public void create(User user) throws SQLException {
        String sql = "INSERT INTO users (id, name, borndt, phone, rg, address, password, accesslevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setDate(3, (Date) user.getBorndt());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getRg());
            stmt.setString(6, user.getAddress());
            stmt.setString(7, user.getPassword());
            stmt.setInt(8, user.getAccesslevel());
            stmt.execute();
        }
    }

    // Método para buscar um usuário pelo ID - VERSÃO CORRIGIDA
    public User read(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getString("password"),     // password
                    rs.getInt("accesslevel"),    // accesslevel
                    rs.getInt("id"),             // id
                    rs.getString("name"),        // name
                    rs.getString("borndt"),      // borndt
                    rs.getString("phone"),       // phone
                    rs.getString("rg"),          // rg
                    rs.getString("address")      // address
                );
            }
        }
        return user;
    }

    // Método para atualizar um usuário
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, borndt = ?, phone = ?, rg = ?, address = ?, password = ?, accesslevel = ? WHERE id = ?";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setDate(2, (Date) user.getBorndt());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getRg());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getPassword());
            stmt.setInt(7, user.getAccesslevel());
            stmt.setInt(8, user.getId());
            stmt.execute();
        }
    }

    // Método para deletar um usuário
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    // Método para listar todos os usuários
    public List<User> listAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User(
                    rs.getString("password"),
                    rs.getInt("accesslevel"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("borndt"),
                    rs.getString("phone"),
                    rs.getString("rg"),
                    rs.getString("address")
                );
                users.add(user);
            }
        }
        return users;
    }

    // Método para buscar usuários por nome (usando LIKE)
    public List<User> searchByName(String name) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE name LIKE ?";
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                User user = new User(
                    rs.getString("password"),
                    rs.getInt("accesslevel"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("borndt"),
                    rs.getString("phone"),
                    rs.getString("rg"),
                    rs.getString("address")
                );
                users.add(user);
            }
        }
        return users;
    }

    // Método adicional para autenticação de usuário
    public User authenticate(String name, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
        User user = null;
        
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new User(
                    rs.getString("password"),
                    rs.getInt("accesslevel"),
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("borndt"),
                    rs.getString("phone"),
                    rs.getString("rg"),
                    rs.getString("address")
                );
            }
        }
        return user;
    }
}
