package fileWork;


import exceptions.FormatException;
import exceptions.NullValueException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import subjects.Coordinates;
import subjects.Ticket;
import subjects.Venue;
import subjects.enams.*;



import java.io.*;
import java.time.ZonedDateTime;
import java.util.Hashtable;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;


import java.io.File;


public class FileReader  {
    //public static String filePath;
    StringBuilder jsonContent = new StringBuilder();

    public Hashtable<Integer, Ticket> read(String filePath) {
        Hashtable<Integer, Ticket> peop = new Hashtable<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            while (reader.hasNextLine()) {
                jsonContent.append(reader.nextLine());
            }

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonContent.toString());

            peop = TicketFactory.createTicket(jsonArray);




        } catch (FileNotFoundException e) {
            System.out.println("No such file in this directory");


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return peop;
    }



/*
        public static void setName (String filename){
            path = filename;
        }

        public static String getName () {
            path = System.getenv("test");
            return path;
        }

 */


}

