package fileWork;

import console.DatabaseConnection;
import subjects.Ticket;

import java.sql.*;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class DatabaseReader {
    private DatabaseConnection sqlConnection;
    public String[] builder = new String[15];

    public DatabaseReader() {
        DatabaseConnection sqlConnection = new DatabaseConnection();
    }
    public Hashtable<Integer, Ticket> read() {
        Hashtable<Integer, Ticket> tickets = new Hashtable<>();
        String query = "SELECT t.id, u.username, u.passwordHash, t.name, c.x, c.y, t.creationDate AS ticket_creationDateString"+
        "t.price, t.discount,t.refundable, t.type AS typeString, v.id AS idVenue, v.name AS nameVenue, v.capacity, v.type AS typeVenueString" +
                "FROM Tickets t LEFT JOIN Users u ON t.owner = u.username" +
                "LEFT JOIN Coordinates c ON t.coordinates = c.y" +
                "LEFT JOIN Venue v ON t.venue = v.id;";

        try (Connection connection = sqlConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                builder[0] = resultSet.getString("username");
                        builder[1] = resultSet.getString("passwordHash");
                        builder[2] = resultSet.getString("x");
                        builder[3] = resultSet.getString("y");
                        builder[4] = resultSet.getString("idVenue");
                        builder[5] = resultSet.getString("nameVenue");
                        builder[6] = resultSet.getString("capacity");
                        builder[7] = resultSet.getString("typeVenueString");
                        builder[8] = resultSet.getString("id");
                        builder[9] = resultSet.getString("name");
                        builder[10] = resultSet.getString("creationDateString");
                        builder[11] = resultSet.getString("price");
                        builder[12] = resultSet.getString("discount");
                        builder[13] = resultSet.getString("refundable");
                        builder[14] = resultSet.getString("typeString");
                        tickets.put(TicketFactory.createTicket(builder).getId(),TicketFactory.createTicket(builder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}







