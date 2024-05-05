package fileWork;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import subjects.Coordinates;
import subjects.Ticket;
import subjects.Venue;
import subjects.enams.VenueType;
import subjects.enams.TicketType;


import java.io.*;
import java.time.ZonedDateTime;
import java.util.Hashtable;
import java.util.Scanner;

;
import java.util.ArrayList;
import java.util.List;


import java.io.File;


public class FileReader {
    public FileReader() {
    }

    //хороший
    public Hashtable<Integer, Ticket> read(String filePath) {
        try {
            Scanner reader = new Scanner(new File(filePath)) ;

            StringBuilder jsonContent = new StringBuilder();
            while (reader.hasNextLine()) {
                jsonContent.append(reader.nextLine());
            }

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonContent.toString());
            Hashtable<Integer, Ticket> tiiik = TicketFactory.createTicket(jsonArray);
            return tiiik;

            /* Вывод информации о людях
            for (Ticket ticket : tiiik) {
                System.out.println("Name: " + ticket.getName());
                System.out.println("Id: " + ticket.getId());
                System.out.println("Coordinates" + ticket.getCoordinates());
                System.out.println("date" + ticket.getCreationDate());
                System.out.println("Price" + ticket.getPrice());
                System.out.println("Discount" + ticket.getDiscount());
                System.out.println("refundable" + ticket.getRefundable());
                System.out.println("type" + ticket.getType());
                System.out.println("venue" + ticket.getVenue());

            }

             */
        } catch (ParseException |FileNotFoundException e) {
            System.out.println("Файл не найден ");

        }
        return null;

    }
}





/*
        public static void setName (String filename){
            path = filename;
        }

        public static String getName () {
            path = System.getenv("test");
            return path;
        }




