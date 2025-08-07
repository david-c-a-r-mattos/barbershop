/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelDAO;

import model.Schedule;
import model.Client;
import model.Service;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleDAO {
    private final Connection connection;
    private final ClientDAO clientDAO;
    private final ServiceDAO serviceDAO;
    private static final SimpleDateFormat[] DATE_FORMATS = {
        new SimpleDateFormat("yyyy-MM-dd HH:mm"),  // Formato ISO
        new SimpleDateFormat("dd/MM/yyyy HH:mm")   // Formato brasileiro
    };

    public ScheduleDAO(Connection connection) {
        this.connection = connection;
        this.clientDAO = new ClientDAO(connection);
        this.serviceDAO = new ServiceDAO(connection);
    }

    public void create(Schedule schedule) throws SQLException {
        String sql = "INSERT INTO schedules (client_id, service_id, value, date, observation) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
        {
            stmt.setInt(1, schedule.getClient().getId());
            stmt.setInt(2, schedule.getService().getId());
            stmt.setFloat(3, schedule.getValue());
            stmt.setTimestamp(4, new Timestamp(schedule.getDate().getTime()));
            stmt.setString(5, schedule.getObservation());
            stmt.executeUpdate();
        
            try (ResultSet rs = stmt.getGeneratedKeys()) 
            {
                if (rs.next()) 
                {
                    schedule.setId(rs.getInt(1)); // Atualiza o objeto com o novo ID
                }
            }
        }
    }

    public Schedule read(int id) throws SQLException {
        String sql = "SELECT * FROM schedules WHERE id = ?";
        Schedule schedule = null;
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Client client = clientDAO.read(rs.getInt("client_id"));
                Service service = serviceDAO.read(rs.getInt("service_id"));
                
                schedule = new Schedule(
                    rs.getInt("id"),
                    client,
                    service,
                    rs.getFloat("value"),
                    formatDate(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedule;
    }

    public void update(Schedule schedule) throws SQLException {
        String sql = "UPDATE schedules SET client_id = ?, service_id = ?, value = ?, date = ?, observation = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getClient().getId());
            stmt.setInt(2, schedule.getService().getId());
            stmt.setFloat(3, schedule.getValue());
            stmt.setTimestamp(4, new Timestamp(schedule.getDate().getTime()));
            stmt.setString(5, schedule.getObservation());
            stmt.setInt(6, schedule.getId());
            stmt.execute();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM schedules WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }
    public void deleteForIdClient(int id) throws SQLException {
        String sql = "DELETE FROM schedules WHERE client_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    public List<Schedule> listAll() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules ORDER BY id";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Client client = clientDAO.read(rs.getInt("client_id"));
                Service service = serviceDAO.read(rs.getInt("service_id"));
                
                Schedule schedule = new Schedule(
                    rs.getInt("id"),
                    client,
                    service,
                    rs.getFloat("value"),
                    formatDate(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
                schedules.add(schedule);
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    // Métodos auxiliares para tratamento de datas
    private String formatDate(Timestamp timestamp) {
        if (timestamp == null) return "";
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(timestamp.getTime()));
    }

    private Date parseDate(String dateString) throws ParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        
        ParseException lastException = null;
        
        for (SimpleDateFormat format : DATE_FORMATS) {
            try {
                return format.parse(dateString);
            } catch (ParseException e) {
                lastException = e;
            }
        }
        
        if (lastException != null) {
            throw lastException;
        }
        
        return null;
    }
}