package console;

import subjects.*;

import subjects.enams.TicketType;
import subjects.enams.VenueType;

import java.time.ZonedDateTime;
import java.util.Date;

import java.util.Scanner;

public class Inserting {
    //проверка на единственность ключа
    Scanner scanner = new Scanner(System.in);
    public static KeyStorage keyStorage;
    public static Ticket insert() {
        String[] output = new String[11];
        Scanner scanner = new Scanner(System.in);}

    public Integer createKey(){
        while (true){
            try{
            System.out.println("Enter key");
            //String value = scanner.nextLine();
            Integer key = Integer.parseInt(scanner.nextLine());
            keyStorage.addNewKey(key);}
            catch ()

        }

        //name
        while (true) {
            System.out.println("Enter name");
            String name = scanner.nextLine();
            if (name == null || name.isEmpty()) {
                System.out.println("Name can't be null");
            } else {
                output[1] = name;
                break;
            }
        }
        while (true) {    //coordinates
            try {
                System.out.println("Enter x,");
                String valueX = scanner.nextLine();
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
                float price = Float.parseFloat(valuePrice);
                if (price <= 0) {
                    System.out.println("Price must be more than 0");
                } else {
                    output[4] = valuePrice;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be a number");
            }
        }

        while (true) {

            //discount
            try {
                System.out.println("Enter discount");
                String valueDiscount = scanner.nextLine();
                double discount = Double.parseDouble(valueDiscount);
                if (discount < 0 && discount >= 100) {
                    System.out.println("Discount must be between  0 and 100");
                } else {
                    output[5] = valueDiscount;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Discount must be a number");
            }
        }
        while (true) {

            try {
                System.out.println("Enter refundable");
                String value = scanner.nextLine();
                //Boolean refundable = Boolean.parseBoolean(value);
                if (value == null) {
                    System.out.println("Refundable can't be null");
                } else {
                    output[6] = value;
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Refundable must be boolean type");
            }

        }
        //type
        boolean check = true;

        while (check) {
            System.out.println("Choose type of ticket: ");
            System.out.println("1.VIP");
            System.out.println("2.GOVERNMENT");
            System.out.println("3.TRUST");
            System.out.println("4.OPEN_JOINT_STOCK_COMPANY");
            String typeTicket = scanner.nextLine();
            for (TicketType type : TicketType.values()) {
                if (typeTicket.equalsIgnoreCase(type.name)) {
                    output[7] = typeTicket;
                    check = false;
                    break;
                }
            }
            if (check) {
                System.out.println("You entered the wrong name");
            }
        }
        while (true) {

            System.out.println("Enter Venue name");
            String nameVenue = scanner.nextLine();
            if (nameVenue == null || nameVenue.isEmpty()) {
                System.out.println("Name can't be null");
            } else {
                output[8] = nameVenue;
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
                    output[9] = valueCapacity;
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
            String typeVenue = scanner.nextLine();
            for (VenueType type : VenueType.values()) {
                if (typeVenue.equalsIgnoreCase(type.name)) {
                    output[10] = typeVenue;
                    check2 = false;
                    break;
                }
            }
            if (check) {
                System.out.println("You entered the wrong name");
            }
        }
        return

                toBuildTicket(output);
    }


    /**
     * This method built new {@link Ticket} object with data.
     *
     * @param commands
     * @return
     */

    public static Ticket toBuildTicket(String[] commands) {
        Ticket ticket = new Ticket();
        ticket.setId(Integer.parseInt(commands[0]));
        ticket.setName(commands[1]);
        ticket.setCoordinates(new Coordinates(Float.parseFloat(commands[2]), Float.parseFloat(commands[3])));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(Float.parseFloat(commands[4]));
        ticket.setDiscount(Double.parseDouble(commands[5]));
        ticket.setRefundable(Boolean.parseBoolean(commands[6]));
        ticket.setType(TicketType.valueOf(commands[7].toUpperCase()));
        ticket.setVenue(new Venue((int) (Math.random() * 100000 + 5), commands[8], Long.parseLong(commands[9]), VenueType.valueOf(commands[10].toUpperCase())));
        return ticket;
    }
}







