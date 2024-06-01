package fileWork;

import console.DatabaseConnection;
import console.OnlineUser;

import java.sql.*;

public class DatabaseProvider {
    private final DatabaseConnection sqlConnection;
    private final OnlineUser onlineUser;
    public DatabaseProvider(OnlineUser onlineUser){
        this.onlineUser = onlineUser;
        this.sqlConnection = new DatabaseConnection();
    }
    private Integer generateNextId() {
        String query = "SELECT nextval('ticket_id_seq')";
        try (Connection connection = sqlConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean canModifyProduct(Integer productId) {
        String query = "SELECT owner FROM Tickets WHERE id = ?";
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, productId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String createdBy = resultSet.getString("owner");
                return createdBy.equals(onlineUser.getUserName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void executeCommand(Command command ){
        if(canModifyProduct(Integer productId)){
            command.execute();
            if(command.execute.sucsess){
                do(getPairedCommand(command));
            }
        }

    }
}
