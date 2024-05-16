package fileWork;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import subjects.Ticket;
import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;


public class FileReader {
    StringBuilder jsonContent = new StringBuilder();
    public Hashtable<Integer, Ticket> read(String filePath) {
        Hashtable<Integer, Ticket> peop = new Hashtable<>();
        try (Scanner reader = new Scanner(new File(filePath))) {
            while (reader.hasNextLine()) {
                jsonContent.append(reader.nextLine());
            }
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonContent.toString());
            peop = TicketFactory.createTicket(jsonArray);
        } catch (FileNotFoundException e) {
            System.err.println("Файл с указанной директорией не обнаружен");
        } catch (ParseException e) {
            System.err.println("Ошибка парсинга JSON: " + e.getMessage());
        } catch (ClassCastException e) {
            System.err.println("Ошибка чтения файла");
        }
        return peop;
    }
}





