package fileWork;

import console.TicketCollection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import subjects.Ticket;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    TicketCollection collection;
    JSONArray jsonArray = new JSONArray();
    String filePath;
    public Writer(){}
    public void create(){
        for(Ticket ticket:collection.getCollection().values()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",ticket.getId());
            jsonObject.put("name",ticket.getName());
            JSONObject coordinatesObject = new JSONObject();
            jsonObject.put("coordinates", coordinatesObject);
            coordinatesObject.put("x", ticket.getCoordinates().getX());
            coordinatesObject.put("y", ticket.getCoordinates().getY());
            jsonObject.put("creationDate", ticket.getCreationDate());
            jsonObject.put("price", ticket.getPrice());
            jsonObject.put("discount", ticket.getDiscount());
            jsonObject.put("refundable", ticket.getRefundable());
            jsonObject.put("type", ticket.getType());
            JSONObject venueObject = new JSONObject();
            jsonObject.put("venue",venueObject);
            venueObject.put("venueId", ticket.getVenue().getId());
            venueObject.put("venueName", ticket.getVenue().getName());
            venueObject.put("venueCapacity", ticket.getVenue().getCapacity());
            venueObject.put("venueType", ticket.getVenue().getType());

            jsonArray.add(jsonObject);
        }
        System.out.println(jsonArray);
    }

    public void write(){
        try{
           FileWriter fileWriter = new FileWriter(filePath);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
