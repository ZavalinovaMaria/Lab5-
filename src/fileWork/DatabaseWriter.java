package fileWork;

import console.TicketCollection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import subjects.Ticket;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseWriter {
    String filePath;
    public DatabaseWriter() {
    }

    public JSONArray createJsonArray(TicketCollection collection) {
        JSONArray jsonArray = new JSONArray();
        System.out.println(collection.getCollection().elements());
        try {
            for (Ticket ticket : collection.getCollection().values()) {

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", ticket.getId());
                jsonObject.put("name", ticket.getName());
                JSONObject coordinatesObject = new JSONObject();
                jsonObject.put("coordinates", coordinatesObject);
                coordinatesObject.put("x", ticket.getCoordinates().getX());
                coordinatesObject.put("y", ticket.getCoordinates().getY());
                jsonObject.put("creationDate", ticket.getCreationDate().toString());
                jsonObject.put("price", ticket.getPrice());
                jsonObject.put("discount", ticket.getDiscount());
                jsonObject.put("refundable", ticket.getRefundable().toString());
                jsonObject.put("type", ticket.getType().toString());
                JSONObject venueObject = new JSONObject();
                jsonObject.put("venue", venueObject);
                venueObject.put("id", ticket.getVenue().getId());
                venueObject.put("name", ticket.getVenue().getName());
                venueObject.put("capacity", ticket.getVenue().getCapacity());
                venueObject.put("type", ticket.getVenue().getType().toString());
                jsonArray.add(jsonObject);
            }

        } catch (NullPointerException e) {
            System.out.println("что-то пошло не так ");
        }
        return jsonArray;
    }

    public void writeToFile(TicketCollection collection,String filePath) {
        try {

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(createJsonArray(collection).toJSONString());
            System.out.println("Коллекция успешно сохранена");
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




