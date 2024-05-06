package console;
import Command.*;
import exceptions.NotUniqueValueException;
import fileWork.FileReader;
import subjects.*;
import subjects.Сomporators.ComparatorDiscount;
import subjects.Сomporators.ComparatorPrice;


import java.io.FileWriter;
import java.util.*;



public class CommandCatalog {
    private final FileReader reader;
    //private final FileWriter writer;
    private final Inserting insert;


    private TicketCollection collection;
    /* private json files*/
    private Map<String, Command> commands;

    private String[] compositeCommand = new String[9];

    private boolean isScriptWorking = false;

    private String[] tokens;

    public CommandCatalog(TicketCollection collection, FileReader reader, Inserting insert, Map<String, Command> commands) {
        this.collection = collection;
        this.reader = reader;

        this.commands = commands;
        this.insert = insert;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }


    public void clearCompositeCommand() {
        compositeCommand = new String[9];
    }

    public void setCompositeCommand(String[] compositeCommand) {
        this.compositeCommand = compositeCommand;
    }

    public void info() {
        System.out.print("Collection information:");
        System.out.println(collection.toString());
    }


    public void help() {
        for (Command com : commands.values()) {
            System.out.println(com.description());
        }
    }

    public void show() {
        System.out.println("The collection: ");
        System.out.println(collection.getCollection());

    }

    public void exit() {
        System.out.println("Конец текущей сессии");
        System.exit(0);
    }

    public void clear() {
        collection.getCollection().clear();
        collection.updateData();
        System.out.println("Коллекция пуста");
    }
/*
    public void history() {
        @Override
        public void execute (String args) throws InvalidArguments, ExitProgram {
            if (!args.isBlank()) throw new InvalidArguments();
            List<String> history = commandManager.getCommandHistory();
            if (history.isEmpty()) {
                console.println("История команд пуста");
                return; // завершение метода
            }
            for (String command : history.subList(Math.max(history.size() - 9, 0), history.size())) {
                console.println(command);

            }
        }
    }*/

    public void printFieldDescendingDiscount() {
        List<Ticket> sortable = new ArrayList<>(collection.getCollection().values());
        Comparator<Ticket> sortibility = new ComparatorDiscount();
        sortable.sort(sortibility);
        Collections.reverse(sortable);
        System.out.println("Вывод значения поля discount всех элементов в порядке убывания: ");
        for (Ticket ticket : sortable) {
            System.out.println(ticket.getDiscount());
        }


    }

    public void removeLowerKey() {
        //удалить ключи из хранилища
        try {
            Integer key;
            key = Integer.parseInt(tokens[1]);

            Iterator<Ticket> iterator = collection.getCollection().values().iterator();
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getId() < key) {
                    iterator.remove();
                }
            }
            collection.updateData();
            System.out.println("Элеметы успешно удалены, убедитесь сами: ");
            show();

        } catch (NumberFormatException e) {
            System.out.println("Ключ дожен быть числом, введите команду еще раз ");

        }
    }


    public void removeLower() {
        try {
            Integer key;
            key = Integer.parseInt(tokens[1]);
            //проверка на то что элемент с данным ключом существует
            Iterator<Ticket> iterator = collection.getCollection().values().iterator();
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getPrice() < collection.getCollection().get(key).getPrice()) {
                    iterator.remove();
                }
            }
            System.out.println("Элеметы успешно удалены ");
            collection.updateData();

        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом ");
        }
    }

    public void save() {

    }

    public void insertNull() {
        Inserting insert = new Inserting();
        Ticket newTicket = insert.toBuildTicket();
        collection.getCollection().put(newTicket.getId(), newTicket);
        collection.updateData();
        System.out.println("Новый элемент добавлен в коллекцию");
    }

    public void removeKey() {
        try {
            Integer key;
            key = Integer.parseInt(tokens[1]);
            collection.deleteKey(key);
            collection.getCollection().remove(key);
            collection.updateData();
            System.out.println("Элемент успешно удален");
        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом ");
        }
    }

        public void updateId () {
            try {
                Integer id;
                id = Integer.parseInt(tokens[1]);
                if (collection.checkingUniqueness(id)) {
                    insert.toUpdateTicket(collection.getCollection().get(id));
                    System.out.println("Значение элемента успешно обновлено");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ключ должен быть числом ");
            }
            catch (NotUniqueValueException e){
                System.out.println("Такого нет у нас ");
            }
        }

        public void sumOfPrice () {
            double totalPrice = 0;
            Collection<Ticket> values = collection.getCollection().values();
            for (Ticket ticket : values) {
                totalPrice += ticket.getPrice();
            }
            System.out.println("Сумма значений поля price для всех элементов коллекции: " + totalPrice);
        }
        public void filterContainsName () {
            Scanner scanner = new Scanner(System.in);//по идее это нужно переделать под консоль
            System.out.print("Введите подстроку, которую нужно найти:");
            String name = scanner.nextLine();
            Collection<Ticket> values = collection.getCollection().values();
            ArrayList<Ticket> elements = new ArrayList<>();
            for (Ticket ticket : values) {
                String value = ticket.getName();
                if (value.contains(name)) elements.add(ticket);
            }
            System.out.println("Данную подстроку в имени имеют следущие элементы:");
            for (Ticket element : elements) {
                System.out.println(element);
            }
        }


    }



