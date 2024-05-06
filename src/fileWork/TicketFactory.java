package fileWork;


import exceptions.FormatException;
import exceptions.NullValueException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import subjects.Coordinates;
import subjects.Ticket;
import subjects.Venue;
import subjects.enams.VenueType;
import subjects.enams.TicketType;
import java.time.ZonedDateTime;
import java.util.Hashtable;



/*
        public static void setName (String filename){
            path = filename;
        }

        public static String getName () {
            path = System.getenv("test");
            return path;
        }


} */

class TicketFactory {
    public static Hashtable<Integer, Ticket> createTicket(JSONArray jsonArray) {
        Hashtable<Integer, Ticket> peopleTable = new Hashtable<>();
        int countOfNullFields = 0;

        for (Object obj : jsonArray) {
            try{
                JSONObject jsonObject = (JSONObject) obj;
                Integer id = jsonObject.get("id") != null && Integer.parseInt(jsonObject.get("id").toString()) > 0 ? Integer.parseInt(jsonObject.get("id").toString()) : null;
                if (id == null) countOfNullFields++;
                String name = jsonObject.get("name") != null ? jsonObject.get("name").toString() : null;
                if (name == null) countOfNullFields++;
                JSONObject coordinatesString = (JSONObject) jsonObject.get("coordinates");
                float x = coordinatesString != null ? Float.parseFloat(coordinatesString.get("x").toString()) : 0.0f;
                float y = coordinatesString != null && coordinatesString.get("y") != null ? Float.parseFloat(coordinatesString.get("y").toString()) : 0.0f;
                if (x == 0.0f) countOfNullFields++;
                if (y == 0.0f) countOfNullFields++;
                if (coordinatesString == null) countOfNullFields++;;
                String creationDateString = (String) jsonObject.get("creationDate");
                ZonedDateTime creationDate = creationDateString != null ? ZonedDateTime.parse(creationDateString) : null;
                if (creationDateString == null) countOfNullFields++;
                float price = Float.parseFloat(jsonObject.get("price").toString()) > 0 ? Float.parseFloat(jsonObject.get("price").toString()) : 0.0f;
                if (price == 0.0f) countOfNullFields++;
                Double discount = jsonObject.get("discount") != null ?
                        (Double.parseDouble(jsonObject.get("discount").toString()) > 0 && Double.parseDouble(jsonObject.get("discount").toString()) <= 100 ?
                                Double.parseDouble(jsonObject.get("discount").toString()) : null) :
                        null;
                if (discount == null) countOfNullFields++;
                String refundableObj = ((String) jsonObject.get("refundable")).trim().toLowerCase();

                Boolean refundable = null;
                if (refundableObj.equals("true")) {
                    refundable = true;
                } else if (refundableObj.equals("false")) {
                    refundable = false;
                } else {
                    refundable = null;
                    countOfNullFields++;
                }
                String typeString = jsonObject.get("type") != null ? (String) jsonObject.get("type") : null;
                TicketType type = typeString != null ? TicketType.valueOf(typeString) : null;
                if (typeString == null) countOfNullFields++;

                JSONObject venue = (JSONObject) jsonObject.get("venue");
                Integer idVenue = venue.get("id") != null && Integer.parseInt(venue.get("id").toString()) > 0 ? Integer.parseInt(venue.get("id").toString()) : null;
                if (idVenue == null) countOfNullFields++;
                String nameVenue = venue.get("name") != null ? venue.get("name").toString() : null;
                if (nameVenue == null) countOfNullFields++;
                Long capacity = venue.get("capacity") != null && Long.parseLong(venue.get("capacity").toString()) > 0 ? Long.parseLong(venue.get("capacity").toString()) : 0L;
                if (capacity == 0L) countOfNullFields++;
                String typeVenueString = venue.get("type") != null ? venue.get("type").toString() : null;
                VenueType typeVenue = typeVenueString != null ? VenueType.valueOf(typeVenueString) : null;
                if (typeVenue == null) countOfNullFields++;


                if (countOfNullFields != 0)
                    throw new NullValueException(String.format("продукт не может быть собран тк есть пустые поля "), null);
                else {
                    peopleTable.put(id, new Ticket(id, name, new Coordinates(x, y), creationDate, price, discount, refundable, type, new Venue(idVenue, nameVenue, capacity, typeVenue)));
                    }

            } catch (NullValueException e ) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e1) {
                System.out.println("некорректный формат числа ");
            }
        }
        return peopleTable;
    }
}