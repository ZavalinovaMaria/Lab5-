package console;

import java.util.Random;
import java.util.Scanner;
import exceptions.NotUniqueValueException;
import subjects.*;
import subjects.enams.TicketType;
import subjects.enams.VenueType;
import java.time.ZonedDateTime;
import static console.Console.*;

/**
 * A class with which objects of the Organization type are created using console input and using commands from a script.
 */
public class Inserting implements Checking {

    Scanner scanner = new Scanner(System.in);
    String[] output = new String[12];

    public Ticket toBuildTicket(String[] commands) {
        Ticket ticket = new Ticket();
        ticket.setId(Integer.parseInt(commands[0]));
        ticket.setName(commands[1]);
        ticket.setCoordinates(new Coordinates(Float.parseFloat(commands[2]), Float.parseFloat(commands[3])));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(Float.parseFloat(commands[4]));
        ticket.setDiscount(Double.parseDouble(commands[5]));
        ticket.setRefundable(Boolean.parseBoolean(commands[6]));
        ticket.setType(TicketType.valueOf(commands[7].toUpperCase()));
        ticket.setVenue(new Venue(Integer.parseInt(commands[8]), commands[9], Long.parseLong(commands[10]), VenueType.valueOf(commands[11].toUpperCase())));
        return ticket;
    }

    public void toBuildUpdationTicket(Ticket ticket,String[] commands) {
        try{
            if(checkingIdUniqueness(Integer.parseInt(commands[8]))){
                ticket.setName(commands[1]);
                ticket.setCoordinates(new Coordinates(Float.parseFloat(commands[2]), Float.parseFloat(commands[3])));
                ticket.setCreationDate(ZonedDateTime.now());
                ticket.setPrice(Float.parseFloat(commands[4]));
                ticket.setDiscount(Double.parseDouble(commands[5]));
                ticket.setRefundable(Boolean.parseBoolean(commands[6]));
                ticket.setType(TicketType.valueOf(commands[7].toUpperCase()));
                ticket.setVenue(new Venue(Integer.parseInt(commands[8]), commands[9], Long.parseLong(commands[10]), VenueType.valueOf(commands[11].toUpperCase())));
            }
        }catch (NotUniqueValueException e ){
            System.out.println(e.getMessage());

        }
    }
    public void toUpdateTicket(Ticket ticket){
        ticket.setName(createName());
        ticket.setCoordinates(new Coordinates(Float.parseFloat(createX()), Float.parseFloat(createY())));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(Float.parseFloat(createPrice()));
        ticket.setDiscount(Double.parseDouble(createDiscount()));
        ticket.setRefundable(Boolean.parseBoolean(createRefundable()));
        ticket.setType(TicketType.valueOf(createType().toUpperCase()));
        ticket.setVenue(new Venue(Integer.parseInt(createVenueId()),  createVenueName(), Long.parseLong(createVenueCapacity()), VenueType.valueOf(createVenueType().toUpperCase())));
    }
    public Ticket createTicket() {
        createKey();
        createName();
        createX();
        createY();
        createPrice();
        createDiscount();
        createRefundable();
        createType();
        createVenueId();
        createVenueName();
        createVenueCapacity();
        createVenueType();
        return toBuildTicket(output);
    }

    public String createKey() {
        Integer keyValue = null;
        while (true) {
            try {
                System.out.println("Введите key");
                String value = scanner.nextLine();
                keyValue = Integer.parseInt(value);
                if (keyValue < 0) {
                    System.out.println("Key не может быть отрицательным или null ");
                } else {
                    if (checkingUniqueness(keyValue)) {
                        keyStoragee.add(keyValue);
                        output[0] = value;
                        break;
                    }
                }

            } catch (NotUniqueValueException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Кey должен быть номером");
                continue;
            }
        }
        return output[0];
    }

    public String createName() {
        while (true) {
            System.out.println("Введите name");
            String name = scanner.nextLine().trim();
            if (name == null | name.isEmpty()) {
                System.out.println("Name не может быть null");
            } else {
                output[1] = name;
                break;

            }
        }
        return output[1];
    }
    public String createX() {
        while (true) {
            try {
                System.out.println("Введите x");
                String valueX = scanner.nextLine();
                float x = Float.parseFloat(valueX);
                output[2] = valueX;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return  output[2];
    }
    public String createY() {
        while (true) {
            try {
                System.out.println("Введите y");
                String valueY = scanner.nextLine();
                Float y = Float.parseFloat(valueY);
                if (valueY.isEmpty()) {
                    System.out.println("Y не может быть null");
                } else {
                    output[3] = valueY;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Координаты должны быть числом");
            }
        }
        return  output[3];
    }
    public String createPrice() {
        while (true) {
            try {
                System.out.println("Введите price");
                String valuePrice = scanner.nextLine();
                Float price = Float.parseFloat(valuePrice);
                if (price < 0) {
                    System.out.println("Price не может быть отрицательным или null ");
                } else {
                    output[4] = valuePrice;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price должно быть числом");
            }
        }
        return  output[4];
    }
    public String createDiscount() {
        while (true) {
            try {
                System.out.println("Введите discount");
                String valueDiscount = scanner.nextLine();
                double discount = Double.parseDouble(valueDiscount);
                if (discount < 0 | discount > 100) {
                    System.out.println("Discount не может быть >100 или <0 ");
                } else {
                    output[5] = valueDiscount;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Discount должно быть числом");
            }
        }
        return  output[5];
    }
    public String createRefundable() {
        while (true) {
            System.out.println("Введите refundable (true/false):");
            String refundable = scanner.nextLine().trim().toLowerCase();

            if (refundable.equals("true")) {
                output[6] = "true";
                break;
            } else if (refundable.equals("false")) {
                output[6] = "false";
                break;
            } else {
                System.out.println("Refundable должно быть 'true' или 'false'");
            }
        }
        return  output[6];
    }
    public String createType() {
        String out = null;
        System.out.println("Выберите тип билета: ");
        System.out.println("1.VIP");
        System.out.println("2.USUAL");
        System.out.println("3.BUDGETARY");
        System.out.println("4.CHEAP");
        boolean check = true;
        boolean firstInput = true;
        while (check) {
            if (!firstInput) {
                System.out.println("Выберите тип билета: ");
            }
            String typeTicket = scanner.nextLine();
            firstInput = false;
            for (TicketType type : TicketType.values()) {
                if (typeTicket.equalsIgnoreCase(type.name)) {
                    out = typeTicket;
                    check = false;
                    break;
                }
            }
            if (check) {
                System.out.println("Вы ввели неправильное название ");
            }
        }
        output[7] = out;
        return  output[7];
    }
    public String createVenueId() {

        Random random = new Random();
        Integer valueId = random.nextInt(100000) + 1;
        try {
            if (checkingIdUniqueness(valueId)) {
                idVenueStorage.add(valueId);
                output[8] = String.valueOf(valueId);
            }
        } catch (NotUniqueValueException e) {
            System.out.println(e.getMessage());
        }
        return  output[8];
    }
    public String createVenueName() {

        while (true) {
            System.out.println("Введите Venue name");
            String nameVenue = scanner.nextLine();
            if (nameVenue == null) {
                System.out.println("Name не может быть null");
            } else {
                output[9] = nameVenue;
                break;
            }
        }
        return  output[9];
    }
    public String createVenueCapacity() {
        while (true) {
            try {
                System.out.println("Введите capacity");
                String valueCapacity = scanner.nextLine();
                float capacity = Long.parseLong(valueCapacity);
                if (capacity <= 0) {
                    System.out.println("Capacity должно быть больше 0");
                } else {
                    output[10] = valueCapacity;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Capacity должно быть числом");
            }
        }
        return  output[10];
    }
    public String createVenueType() {
        boolean check2 = true;
        while (check2) {
            System.out.println("Выберите тип venue: ");
            System.out.println("1.BAR");
            System.out.println("2.CINEMA");
            System.out.println("3.MALL");
            System.out.println("4.STADIUM");
            String typeVenue = scanner.nextLine().trim().toUpperCase();
            for (VenueType type : VenueType.values()) {
                if (typeVenue.equalsIgnoreCase(type.name)) {
                    output[11] = typeVenue;
                    check2 = false;
                    break;
                }
            }
            if (check2) {
                System.out.println("Вы ввели неправильное название ");
            }
        }
       return output[11];
    }

}
