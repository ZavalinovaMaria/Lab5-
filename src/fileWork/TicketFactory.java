package fileWork;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import subjects.Coordinates;
import subjects.Ticket;
import subjects.Venue;
import subjects.enams.TicketType;
import subjects.enams.VenueType;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TicketFactory {
    public static Hashtable<Integer,Ticket> createTicket(JSONArray jsonArray) {
        Hashtable<Integer,Ticket> peopleTable = new Hashtable<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            Integer id = Integer.parseInt(jsonObject.get("id").toString());
            String name = jsonObject.get("name").toString();
            JSONObject coordinatesString = (JSONObject) jsonObject.get("coordinates");
            float x = Float.parseFloat(coordinatesString.get("x").toString());
            Float y = Float.parseFloat(coordinatesString.get("y").toString());

            String creationDateString = (String) jsonObject.get("creationDate");
            ZonedDateTime creationDate = ZonedDateTime.parse(creationDateString);
            float price = Float.parseFloat(jsonObject.get("price").toString());
            double discount = Double.parseDouble(jsonObject.get("discount").toString());
            Boolean refundable = Boolean.parseBoolean((String) jsonObject.get("refundable"));
            String typeString = (String) jsonObject.get("type");
            TicketType type = TicketType.valueOf(typeString);

            JSONObject venue = (JSONObject) jsonObject.get("venue");
            Integer idVenue = Integer.parseInt(venue.get("id").toString());
            String nameVenue = venue.get("name").toString();
            Long capacity = Long.parseLong(venue.get("capacity").toString());
            String typeVenueString = venue.get("type").toString();

            VenueType typeVenue = VenueType.valueOf(typeVenueString);

            peopleTable.put(id,new Ticket(id, name, new Coordinates(x, y), creationDate, price, discount, refundable, type, new Venue(idVenue, nameVenue, capacity, typeVenue)));
        }
        return peopleTable;

    }
}
