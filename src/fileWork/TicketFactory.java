package fileWork;

import subjects.User;
import exceptions.NullValueException;
import subjects.Coordinates;
import subjects.Ticket;
import subjects.Venue;
import subjects.enams.VenueType;
import subjects.enams.TicketType;
import java.time.ZonedDateTime;


/**
 * Factory for creating Ticket type objects from Database.
 */
class TicketFactory {
    private DatabaseReader reader;

    /*
    public static Hashtable<Integer, Ticket> createTicket(JSONArray jsonArray) {
        Hashtable<Integer, Ticket> peopleTable = new Hashtable<>();
        int countOfNullFields = 0;

        for (Object obj : jsonArray) {
            countOfNullFields = 0;
            try {
                JSONObject jsonObject = (JSONObject) obj;
                Integer id = jsonObject.get("id") != null && Integer.parseInt(jsonObject.get("id").toString()) > 0 ? Integer.parseInt(jsonObject.get("id").toString()) : null;
                if (id == null) countOfNullFields++;
                //на result поменять по идее и все ура пробуем дома
                String name = jsonObject.get("name") != null ? jsonObject.get("name").toString() : null;
                if (name == null) countOfNullFields++;
                JSONObject coordinatesString = (JSONObject) jsonObject.get("coordinates");
                float x = coordinatesString != null ? Float.parseFloat(coordinatesString.get("x").toString()) : 0.0f;
                float y = coordinatesString != null && coordinatesString.get("y") != null ? Float.parseFloat(coordinatesString.get("y").toString()) : 0.0f;
                if (x == 0.0f) countOfNullFields++;
                if (y == 0.0f) countOfNullFields++;
                if (coordinatesString == null) countOfNullFields++;

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
                TicketType type = typeString != null ? TicketType.valueOf(typeString.toUpperCase()) : null;
                if (typeString == null) countOfNullFields++;

                JSONObject venue = (JSONObject) jsonObject.get("venue");
                Integer idVenue = venue.get("id") != null && Integer.parseInt(venue.get("id").toString()) > 0 ? Integer.parseInt(venue.get("id").toString()) : null;
                if (idVenue == null) countOfNullFields++;
                String nameVenue = venue.get("name") != null ? venue.get("name").toString() : null;
                if (nameVenue == null) countOfNullFields++;
                Long capacity = venue.get("capacity") != null && Long.parseLong(venue.get("capacity").toString()) > 0 ? Long.parseLong(venue.get("capacity").toString()) : 0L;
                if (capacity == 0L) countOfNullFields++;
                String typeVenueString = venue.get("type") != null ? venue.get("type").toString() : null;
                VenueType typeVenue = typeVenueString != null ? VenueType.valueOf(typeVenueString.toUpperCase()) : null;
                if (typeVenue == null) countOfNullFields++;

                if (countOfNullFields != 0)
                    throw new NullValueException(String.format("Есть пустое поле - продукт не будет собран  "), null);

                else {
                    peopleTable.put(id, new Ticket(id, name, new Coordinates(x, y), creationDate, price, discount, refundable, type, new Venue(idVenue, nameVenue, capacity, typeVenue)));
                }

            } catch (NumberFormatException e1) {
                System.out.println("Есть поле с некорректным форматом числа - продукт не будет собран ");

            } catch (NullValueException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }
        return peopleTable;
    }

     */
    public static Ticket createTicket(String[] builder) {
        int countOfNullFields = 0;
        Ticket ticket =new Ticket();
            try {
                String username = builder[0] != null ?  builder[0] : null;
                if (username == null) countOfNullFields++;
                String passwordHash = builder[1] != null ? builder[1] : null;
                if (passwordHash == null) countOfNullFields++;

                float x = builder[2] != null ? Float.parseFloat(builder[2]) : 0.0f;
                float y = builder[3] != null  ? Float.parseFloat(builder[3]) : 0.0f;
                if (x == 0.0f) countOfNullFields++;
                if (y == 0.0f) countOfNullFields++;

                Integer idVenue = builder[4] != null && Integer.parseInt(builder[4]) > 0 ? Integer.parseInt(builder[4]) : null;
                if (idVenue == null) countOfNullFields++;
                String nameVenue = builder[5] != null ? builder[5] : null;
                if (nameVenue == null) countOfNullFields++;
                Long capacity = builder[6] != null && Long.parseLong(builder[6]) > 0 ? Long.parseLong(builder[6]) : 0L;
                if (capacity == 0L) countOfNullFields++;
                String typeVenueString = builder[7] != null ? builder[7] : null;
                VenueType typeVenue = typeVenueString != null ? VenueType.valueOf(typeVenueString.toUpperCase()) : null;
                if (typeVenue == null) countOfNullFields++;

                Integer id = builder[8] != null && Integer.parseInt(builder[8]) > 0 ? Integer.parseInt(builder[8]) : null;
                if (id == null) countOfNullFields++;
                String name = builder[9] != null ? builder[9] : null;
                if (name == null) countOfNullFields++;

                String creationDateString = builder[10];
                ZonedDateTime creationDate = creationDateString != null ? ZonedDateTime.parse(creationDateString) : null;
                if (creationDateString == null) countOfNullFields++;
                float price = Float.parseFloat(builder[11]) > 0 ? Float.parseFloat(builder[11]) : 0.0f;
                if (price == 0.0f) countOfNullFields++;
                Double discount = builder[12] != null ?
                        (Double.parseDouble(builder[12]) > 0 && Double.parseDouble(builder[12]) <= 100 ?
                                Double.parseDouble(builder[12]) : null) :
                        null;
                if (discount == null) countOfNullFields++;


                String refundableObj = builder[13].trim().toLowerCase();
                Boolean refundable = null;
                if (refundableObj.equals("true")) {
                    refundable = true;
                } else if (refundableObj.equals("false")) {
                    refundable = false;
                } else {
                    refundable = null;
                    countOfNullFields++;
                }
                String typeString = builder[14] != null ? (String) builder[14] : null;
                TicketType type = typeString != null ? TicketType.valueOf(typeString.toUpperCase()) : null;
                if (typeString == null) countOfNullFields++;


                if (countOfNullFields != 0)
                    throw new NullValueException(String.format("Есть пустое поле - продукт не будет собран  "), null);

                else {
                     ticket.setCharacteristic(id, new User(username,passwordHash), name, new Coordinates(x, y), creationDate,
                             price, discount, refundable, type, new Venue(idVenue, nameVenue, capacity, typeVenue));
                }

            } catch (NumberFormatException e1) {
                System.out.println("Есть поле с некорректным форматом числа - продукт не будет собран ");

            } catch (NullValueException e) {
                System.out.println(e.getMessage());
               // continue; тк до этого шли по массиву
            }
            return ticket;

        }

    }

