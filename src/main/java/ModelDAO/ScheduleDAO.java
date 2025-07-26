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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleDAO {
    private final Connection connection;
    private final ClientDAO clientDAO;
    private final ServiceDAO serviceDAO;

    public ScheduleDAO(Connection connection) {
        this.connection = connection;
        this.clientDAO = new ClientDAO(connection);
        this.serviceDAO = new ServiceDAO(connection);
    }

    // Método para inserir um novo agendamento
    public void create(Schedule schedule) throws SQLException {
        String sql = "INSERT INTO schedules (id, client_id, service_id, value, date, observation) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getId());
            stmt.setInt(2, schedule.getClient().getId());
            stmt.setInt(3, schedule.getService().getId());
            stmt.setFloat(4, schedule.getValue());
            stmt.setTimestamp(5, new Timestamp(schedule.getDate().getTime()));
            stmt.setString(6, schedule.getObservation());
            stmt.execute();
        }
    }

    // Método para buscar um agendamento pelo ID
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
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedule;
    }

    // Método para atualizar um agendamento
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

    // Método para deletar um agendamento
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM schedules WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    // Método para listar todos os agendamentos
    public List<Schedule> listAll() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules";
        
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
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
                schedules.add(schedule);
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    // Método para buscar agendamentos por cliente
    public List<Schedule> findByClient(int clientId) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules WHERE client_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Client client = clientDAO.read(rs.getInt("client_id"));
                Service service = serviceDAO.read(rs.getInt("service_id"));
                
                Schedule schedule = new Schedule(
                    rs.getInt("id"),
                    client,
                    service,
                    rs.getFloat("value"),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
                schedules.add(schedule);
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    // Método para buscar agendamentos por data
    public List<Schedule> findByDate(Date date) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules WHERE DATE(date) = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Client client = clientDAO.read(rs.getInt("client_id"));
                Service service = serviceDAO.read(rs.getInt("service_id"));
                
                Schedule schedule = new Schedule(
                    rs.getInt("id"),
                    client,
                    service,
                    rs.getFloat("value"),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
                schedules.add(schedule);
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }

    // Método para buscar agendamentos por período
    public List<Schedule> findByDateRange(Date startDate, Date endDate) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules WHERE date BETWEEN ? AND ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new Timestamp(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Client client = clientDAO.read(rs.getInt("client_id"));
                Service service = serviceDAO.read(rs.getInt("service_id"));
                
                Schedule schedule = new Schedule(
                    rs.getInt("id"),
                    client,
                    service,
                    rs.getFloat("value"),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(rs.getTimestamp("date"))
                );
                schedule.setObservation(rs.getString("observation"));
                schedules.add(schedule);
            }
        } catch (Exception ex) {
            Logger.getLogger(ScheduleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedules;
    }
}
