/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*import java.security.Provider.Service;*/
import static javax.management.Query.value;
/**
 *
 * @author david
 */
public class main 
{
    public static void main(String args[])
    {
        String name = "Thiago";
        System.out.println(name);
        Service service = new Service(1, "barba", 35);
        System.out.println(service.getDescription());
        Client client = new Client(1,"David","rua Lupa Lupa 28","3782654");
        System.out.println(client.getName());
        User user = new User(1, "João", "12435gasfgjh");
        System.out.println(user.getName());
        Schedule schedule = new Schedule(1, client, service, 35, "25/07/2035 09:35");
        System.out.println(schedule.getClient().getName());
        
    }
}
