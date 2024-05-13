package console;

import java.util.Random;
import java.util.Scanner;

import exceptions.NotUniqueValueException;
import subjects.*;
import subjects.enams.TicketType;
import subjects.enams.VenueType;
import static console.Console.keyStoragee;
import java.time.ZonedDateTime;
import java.util.concurrent.Callable;


public class Inserting implements Checking {
    Scanner scanner = new Scanner(System.in);
    String[] output = new String[12];
    String [] commandsScript = new String[11];
    Ticket ticket;


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
        ticket.setVenue(new Venue(Integer.parseInt(output[8]),output[9],Long.parseLong(output[10]),VenueType.valueOf(output[11])));
        return ticket;
    }
    public void toUpdateTicket(Ticket ticket,String[ ]commands) {
        ticket.setName(commands[1]);
        ticket.setCoordinates(new Coordinates(Float.parseFloat(commands[2]), Float.parseFloat(commands[3])));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(Float.parseFloat(commands[4]));
        ticket.setDiscount(Double.parseDouble(commands[5]));
        ticket.setRefundable(Boolean.parseBoolean(commands[6]));
        ticket.setType(TicketType.valueOf(commands[7].toUpperCase()));
        ticket.setVenue(new Venue(Integer.parseInt(output[8]),output[9],Long.parseLong(output[10]),VenueType.valueOf(output[11])));


    }
        /*
        ticket.setName(createName());
        ticket.setCoordinates(new Coordinates(createX(), createY()));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(createPrice());
        ticket.setDiscount(createDiscount());
        ticket.setRefundable(createRefundable());
        ticket.setType(TicketType.valueOf(createTicketType().toUpperCase()));
        ticket.setVenue(createVenue());
    }

         */
    public Venue toBuildVenue(String[] output) {
        Venue venue = new Venue();
        venue.setId(Integer.parseInt(output[8]));
        venue.setName(output[9]);
        venue.setCapacity(Long.parseLong(output[10]));
        venue.setType(VenueType.valueOf(output[11]));
        return venue;
    }


    public Ticket createTicket() {

        Integer keyValue = null;
            while (true) {
                try {
                    System.out.println("Enter key");
                    String value = scanner.nextLine();
                     keyValue = Integer.parseInt(value);
                    if (keyValue < 0) {
                        System.out.println("Key can't be null or отрицательный ");
                    } else {
                        checkUniqueness(keyValue);
                        output[0] = value;
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Key must be numbers");
                    continue;
                } catch (NotUniqueValueException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }


        while (true) {
            System.out.println("Enter name");
            String name = scanner.nextLine().trim();
            if (name == null | name.isEmpty()) {
                System.out.println("Name can't be null");
            } else {
                output[1] = name;
                break;

            }
        }
        while (true) {
            try {
                System.out.println("Enter x");
                String valueX = scanner.nextLine();
                float x = Float.parseFloat(valueX);
                output[2] = valueX;
                break;

            } catch (NumberFormatException e) {
                System.out.println("Coordinates must be numbers");
            }
        }
        while (true) {
            try {
                System.out.println("Enter y");
                String valueY = scanner.nextLine();
                Float y = Float.parseFloat(valueY);
                if (valueY.isEmpty()) {
                    System.out.println("Y can't be null");
                } else {
                    output[3] = valueY;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Coordinates must be numbers");
            }
        }

        while (true) {
            try {
                System.out.println("Enter price");
                String valuePrice = scanner.nextLine();
                Float price = Float.parseFloat(valuePrice);
                if (price < 0) {
                    System.out.println("Key can't be null or отрицательный ");
                } else {
                    output[4]= valuePrice;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be numbers");
            }
        }


        while (true) {
            try {
                System.out.println("Enter discount");
                String valueDiscount = scanner.nextLine();
                double discount = Double.parseDouble(valueDiscount);
                if (discount < 0 | discount > 100) {
                    System.out.println("Discount can't be >100 or <0 ");
                } else {
                    output[5] = valueDiscount;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Discount must be numbers");
            }
        }
        while (true) {
            System.out.println("Enter refundable (true/false):");
            String refundable = scanner.nextLine().trim().toLowerCase();

            if (refundable.equals("true")) {
                output[6] = "true";
                break;
            } else if (refundable.equals("false")) {
                output[6] = "false";
                break;
            } else {
                System.out.println("Refundable must be 'true' or 'false'");
            }
        }

        String out = null;
        System.out.println("Choose type of ticket: ");
        System.out.println("1.VIP");
        System.out.println("2.USUAL");
        System.out.println("3.BUDGETARY");
        System.out.println("4.CHEAP");
        boolean check = true;
        boolean firstInput = true;
        while (check) {
            if (!firstInput) {
                System.out.println("Choose type of ticket: "); // Выводим только для последующих вводов
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
                System.out.println("You entered the wrong name");
            }
        }
        output[7] = out;

        Random random = new Random();
        Integer valueId = random.nextInt(100000)+1;
        output[8] = String.valueOf(valueId);

        while (true) {
            System.out.println("Enter Venue name");
            String nameVenue = scanner.nextLine();
            if (nameVenue == null | nameVenue.isEmpty()) {
                System.out.println("Name can't be null");
            } else {
                output[9] = nameVenue;
                break;
            }
        }
        while (true) {
            try {
                System.out.println("Enter capacity");
                String valueCapacity = scanner.nextLine();
                float capacity = Long.parseLong(valueCapacity);
                if (capacity <= 0) {
                    System.out.println("Capacity must be more than 0");
                } else {
                    output[10] = valueCapacity;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Capacity must be a number");
            }
        }
        boolean check2 = true;
        while (check2) {
            System.out.println("Choose type of venue: ");
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
                System.out.println("You entered the wrong name");
            }

        }
        return toBuildTicket(output);


    }

    @Override
    public void checkUniqueness(Integer value) throws NotUniqueValueException {
        if(keyStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", value));

        else keyStoragee.add(value);
    }

    public void addNewKey(Integer key){
        try {
            checkUniqueness(key);
        }
        catch (NotUniqueValueException e ){
            System.out.println(e.getMessage());
        }
    }


}
