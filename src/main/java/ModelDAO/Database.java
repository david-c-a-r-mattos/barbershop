package ModelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/BarberShop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                createTables();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    private static void createTables() {
        try (Statement stmt = connection.createStatement()) {
            // Tabela clients
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS clients ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "borndt VARCHAR(20),"
                    + "phone VARCHAR(20),"
                    + "rg VARCHAR(20),"
                    + "address VARCHAR(200),"
                    + "cep VARCHAR(10))");
            
            // Tabela users
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(100) NOT NULL,"
                    + "borndt VARCHAR(20),"
                    + "phone VARCHAR(20),"
                    + "rg VARCHAR(20),"
                    + "address VARCHAR(200),"
                    + "password VARCHAR(50) NOT NULL,"
                    + "accesslevel INT NOT NULL)");
            
            // Tabela services
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS services ("
                    + "id SERIAL PRIMARY KEY,"
                    + "description VARCHAR(200) NOT NULL,"
                    + "value NUMERIC(10,2) NOT NULL)");
            
            // Tabela schedules
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS schedules ("
                    + "id SERIAL PRIMARY KEY,"
                    + "client_id INT NOT NULL REFERENCES clients(id),"
                    + "service_id INT NOT NULL REFERENCES services(id),"
                    + "value NUMERIC(10,2) NOT NULL,"
                    + "date TIMESTAMP NOT NULL,"
                    + "observation TEXT)");
            
            System.out.println("Tabelas criadas/verificadas com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Erro ao criar tabelas", ex);
        }
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}