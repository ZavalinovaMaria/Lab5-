package console;

import java.util.Scanner;

import exceptions.NotUniqueValueException;
import subjects.*;
import subjects.enams.TicketType;
import subjects.enams.VenueType;
import static console.Console.keyStoragee;
import java.time.ZonedDateTime;




public class Inserting implements Checking {
    Scanner scanner = new Scanner(System.in);
    String[] output = new String[4];
    Ticket ticket;

    public Ticket toBuildTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(createKey());
        ticket.setName(createName());
        ticket.setCoordinates(new Coordinates(createX(), createY()));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(createPrice());
        ticket.setDiscount(createDiscount());
        ticket.setRefundable(createRefundable());
        ticket.setType(TicketType.valueOf(createTicketType().toUpperCase()));
        ticket.setVenue(createVenue());
        return ticket;

    }
    public void toUpdateTicket(Ticket ticket) {
        ticket.setName(createName());
        ticket.setCoordinates(new Coordinates(createX(), createY()));
        ticket.setCreationDate(ZonedDateTime.now());
        ticket.setPrice(createPrice());
        ticket.setDiscount(createDiscount());
        ticket.setRefundable(createRefundable());
        ticket.setType(TicketType.valueOf(createTicketType().toUpperCase()));
        ticket.setVenue(createVenue());

    }

    public Venue toBuildVenue(String[] output) {
        Venue venue = new Venue();
        venue.setId(Integer.parseInt(output[0]));
        venue.setName(output[1]);
        venue.setCapacity(Long.parseLong(output[2]));
        venue.setType(VenueType.valueOf(output[3]));
        return venue;
    }


    public Integer createKey() {
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
                    break; //возвращает первый ключ который ввели
                }

            } catch (NumberFormatException e) {
                System.out.println("Key must be numbers");
                continue;
            }catch (NotUniqueValueException e) {
                System.out.println(e.getMessage());
                continue; // Повторяем цикл при неправильном вводе
            }
        }
        return keyValue;

    }
    @Override
    public void checkUniqueness(Integer value) throws NotUniqueValueException {
        if(keyStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", value));

        else keyStoragee.add(value);
    }
    // по моему можно удалить тк тут он не нужен, а в коллекции не используется вообще
    public void addNewKey(Integer key){
        try {
            checkUniqueness(key);
        }
        catch (NotUniqueValueException e ){
            System.out.println(e.getMessage());
        }
    }


    public String createName() {
        while (true) {
            System.out.println("Enter name");
            String name = scanner.nextLine().trim();
            if (name == null  | name.isEmpty()) {
                System.out.println("Name can't be null");
            } else {
                return name;
            }
        }
    }

    public float createX() {
        while (true) {
            try {
                System.out.println("Enter x,");
                String valueX = scanner.nextLine();
                float x = Float.parseFloat(valueX);
                return x;
            } catch (NumberFormatException e) {
                System.out.println("Coordinates must be numbers");
            }

        }
    }

    public float createY() {

        while (true) {
            try {
                System.out.println("Enter y");
                String valueY = scanner.nextLine();
                Float y = Float.parseFloat(valueY);
                if (valueY.isEmpty()) {
                    System.out.println("Y can't be null");
                } else {
                    return y;
                }
            } catch (NumberFormatException e) {
                System.out.println("Coordinates must be numbers");
            }
        }
    }

    public float createPrice() {

        while (true) {
            try {
                System.out.println("Enter price");
                String valuePrice = scanner.nextLine();
                Float price = Float.parseFloat(valuePrice);
                if (price < 0) {
                    System.out.println("Key can't be null or отрицательный ");
                } else {
                    return price;
                }
            } catch (NumberFormatException e) {
                System.out.println("Price must be numbers");
            }
        }
    }

    public double createDiscount() {


        while (true) {
            try {
                System.out.println("Enter discount");
                String valueDiscount = scanner.nextLine();
                double discount = Double.parseDouble(valueDiscount);
                if (discount < 0 | discount > 100) {
                    System.out.println("Discount can't be >100 or <0 ");
                } else {
                    return discount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Discount must be numbers");
            }
        }
    }


    public boolean createRefundable() {
        while (true) {
            System.out.println("Enter refundable (true/false):");
            String refundable = scanner.nextLine().trim().toLowerCase(); // Приводим к нижнему регистру и удаляем лишние пробелы

            if (refundable.equals("true")) {
                return true;
            } else if (refundable.equals("false")) {
                return false;
            } else {
                System.out.println("Refundable must be 'true' or 'false'");
            }
        }
    }

    //2 раза тоже
    public String createTicketType() {
        String output = null;
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
                    output = typeTicket;
                    check = false;
                    break;
                }
            }
            if (check) {
                System.out.println("You entered the wrong name");
            }
        }


        return output;
    }

    public Venue createVenue() {

        while (true) {
            try {
                System.out.println("Enter Venue id");
                String valueId = scanner.nextLine();
                Integer idVenue = Integer.parseInt(valueId);
                if (idVenue < 0) {
                    System.out.println("Id can't be null or отрицательный ");
                } else {
                    output[0] = valueId;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Id must be numbers");
            }
        }
        while (true) {
            System.out.println("Enter Venue name");
            String nameVenue = scanner.nextLine();
            if (nameVenue == null | nameVenue.isEmpty()) {
                System.out.println("Name can't be null");
            } else {
                output[1] = nameVenue;
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
                    output[2] = valueCapacity;
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
                    output[3] = typeVenue;
                    check2 = false;
                    break;
                }
            }
            if (check2) {
                System.out.println("You entered the wrong name");
            }

        }
        return toBuildVenue(output);
        //Price Discount написать, потом протестить, скопировать в lab4   метод ниже аскоментить и проверить что работает

    }



}
