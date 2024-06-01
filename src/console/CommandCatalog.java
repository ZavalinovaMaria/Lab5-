package console;
import Command.*;
import exceptions.NotExistingValueException;
import exceptions.NotUniqueValueException;
//import fileWork.FileReader;
import fileWork.DatabaseReader;
import fileWork.DatabaseWriter;
import subjects.*;
import subjects.Сomporators.ComparatorDiscount;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import static console.Console.*;

/**
 * A class that stores implementations of all program commands.
 * It can work with commands both from the console and from a script,
 * responsible for this {@link CommandCatalog#isScriptWorking}
 */
public class CommandCatalog {
    private final DatabaseReader reader;
    private final Inserting insert;
    private final DatabaseWriter writer;


    private TicketCollection collection;
    /**
     * A field that refers to an object whose fields contain the collection with which the program works.
     */
    private Set<String> scriptHistory = new HashSet<>();
    /**
     * A field that contains history of executed commands
     */
    private final List<String> commandHistory = new ArrayList<>();
    /**
     * An array of strings into which an array of commands from the script is passed{@link ScriptManager#executeScript()}
     */

    private String[] compositeCommand = new String[9];
    /**
     * A field that can be used to change the implementation of commands for working with a script
     *
     * @see CommandCatalog#executeScript()
     */
    private boolean isScriptWorking = false;
    /**
     * String array to which commands are sent from the console
     */
    private String[] tokens;

    public CommandCatalog(TicketCollection collection, DatabaseReader reader, DatabaseWriter writer, Inserting insert, Map<String, Command> commands) {
        this.collection = collection;
        this.reader = reader;
        this.writer = writer;
        this.insert = insert;
    }

    public void addToHistory(String command) {
        commandHistory.add(command);
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

    /**
     * Command showing information about the current state of a collection {@link InfoCommand}
     *
     * @see TicketCollection
     */
    public void info() {
        System.out.print("Информация о коллекции:");
        System.out.println(collection.toString());
    }

    /**
     * Command to call up descriptions of all commands{@link HelpCommand}
     */
    public void help() {
        for (Command com : commands.values()) {
            System.out.println(com.description());
        }
    }

    /**
     * A command that prints to the console all objects in a collection and their fields {@link ShowCommand}
     */
    public void show() {
        System.out.println("Коллекция: ");
        System.out.println(collection.getCollection());

    }
    /**
     * Command to exit the application {@link ExitCommand}
     */
    public void exit() {
        System.out.println("Конец текущей сессии");
        System.exit(0);
    }

    /**
     * Command to clear a collection {@link ClearCommand}
     */
    public void clear() {
        collection.getCollection().clear();
        collection.updateData();
        System.out.println("Коллекция пуста");
    }

    /**
     * Command to show last 13 commands {@link HistoryCommand}
     */
    public void history() {
        if (commandHistory.isEmpty()) {
            System.out.println("История команд пуста");
            return;
        }
        for (String command : commandHistory.subList(Math.max(commandHistory.size() - 13, 0), commandHistory.size())) {
            System.out.println(command);
        }
        }


    /**
     * A command that creates an object of the {@link ScriptManager} class and starts the script process
     *
     * @see ExecuteScriptCommand
     */
    public void executeScript() {
        try {
            File script;
            if (isScriptWorking) {
                script = new File(compositeCommand[0]);
            } else {
                script = new File(tokens[1]);
            }
            if (!script.exists()) {
                System.out.println("Указанный файл не существует");
                return;
            }
            if (scriptHistory.contains(compositeCommand[0]) ) {
                System.out.println("Данный скрипт уже был выполнен");
                return;
            }
            if (isScriptWorking) {
                scriptHistory.add(compositeCommand[0]);
            } else {
                scriptHistory.add(tokens[1]);
            }

            isScriptWorking = true;
            ScriptManager scriptManager = new ScriptManager(script, commands, this);
            System.out.println("Выполнение скрипта");
            if (isScriptWorking) {
                clearCompositeCommand();
            }
            scriptManager.executeScript();
            isScriptWorking = false;
            System.out.println("Выполнение скрипта завершилось успешно");

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Пожалуйста, введите имя файла");
        }
    }
    /*private boolean canModifyProduct(long productId) {
        String query = "SELECT createdBy FROM Product WHERE id = ?";
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, productId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String createdBy = resultSet.getString("createdBy");
                return createdBy.equals(userManager.getUserName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */
    /**
     * A command that allows you to print fields from collection descending discount {@link PrintFieldDescendingDiscountCommand}
     */
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

    /**
     * A command that allows you to remove elements smaller than the one specified by key {@link RemoveLowerKeyCommand}
     */
    public void removeLowerKey() {
        try {
            Integer key;
            if (isScriptWorking) {
                key = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                key = Integer.parseInt(tokens[1]);
            }


            //если можем модифицировать продукт
            Iterator<Ticket> iterator = collection.getCollection().values().iterator();
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getId() < key) {
                    collection.deleteKey(ticket.getId());
                    iterator.remove();
                    System.out.println("Удален элемент с ключом: "+ticket.getId());
                }
            }
            collection.updateData();
        } catch (NumberFormatException e) {
            System.out.println("Ключ дожен быть числом, введите команду еще раз ");
        }
    }

    /**
     * A command that allows you to remove elements smaller than the one specified  {@link RemoveLowerCommand}
     */
    public void removeLower() {
        try {
            Integer key;
            if (isScriptWorking) {
                key = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                key = Integer.parseInt(tokens[1]);
            }
            if (collection.checkingExistence(key)) {
                Iterator<Ticket> iterator = collection.getCollection().values().iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getPrice() < collection.getCollection().get(key).getPrice()) {
                        iterator.remove();
                        collection.deleteKey(ticket.getId());
                        System.out.println("Элемет c ключом "+ticket.getId()+ " успешно удален ");
                    }
                }
                collection.updateData();
            }else{
                System.out.println("Коллекция осталась без изменений ");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом ");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с таким ключом не содержится в коллекции и  не может быть удален ");
        }
    }


    /**
     * Command that saves the current collection instance to a file{@link SaveCommand}
     *
     * @see DatabaseWriter
     */
    public void save() {
        writer.writeToFile(collection, firstFilePath);
    }

    /**
     * A command that allows you to insert a new object in the collection {@link InsertCommand}
     */
    public void insert() {
        if (isScriptWorking) {
            try {
                Ticket newTicket = insert.toBuildTicket(compositeCommand);
                if (insert.checkingUniqueness(newTicket.getId()) && insert.checkingIdUniqueness(newTicket.getVenue().getId())) {
                    collection.getCollection().put(newTicket.getId(), newTicket);
                    keyStoragee.add(newTicket.getId());
                    idVenueStorage.add(newTicket.getVenue().getId());
                    clearCompositeCommand();
                    System.out.println("Новый объект добавлен в коллекцию");
                }
            } catch (NumberFormatException E) {
                System.out.println("Ошибка заполнения полей ");
            } catch (NotUniqueValueException e) {
                System.out.println(e.getMessage());
            }

        } else {
            Ticket newTicket = insert.createTicket();
            collection.getCollection().put(newTicket.getId(), newTicket);
        }
        collection.updateData();
    }

    /**
     * A command that allows you to delete an object by its key {@link RemoveKeyCommand}
     */
    public void removeKey() {
        System.out.println("Если исключение не выпало-элемент удален");
        try {
            Integer key;
            if (isScriptWorking) {
                key = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                key = Integer.parseInt(tokens[1]);
            }
            collection.deleteKey(key);
            collection.getCollection().remove(key);
            collection.updateData();


        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом ");
        }
    }

    /**
     * A command that allows you to update an object with a given id {@link UpdateIdCommand}
     */
    public void updateId() {
        try {
            Integer id;
            if (isScriptWorking) {
                System.out.println("1");
                id = Integer.parseInt(compositeCommand[0]);
                if (collection.checkingExistence(id)) {
                    System.out.println("2");
                    insert.toBuildUpdationTicket(collection.getCollection().get(id), compositeCommand);
                    idVenueStorage.add(collection.getCollection().get(id).getVenue().getId());
                    clearCompositeCommand();
                }
            } else {
                id = Integer.parseInt(tokens[1]);
                if (collection.checkingExistence(id)) {
                    insert.toUpdateTicket(collection.getCollection().get(id));
                    System.out.println("Значение элемента успешно обновлено");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Id должен быть числом ");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с подобным ключем не обнаружен ");
        }
    }

    /**
     * Command that allows you to count sum of price for all objects in collection{@link SumOfPriceCommand}
     */
    public void sumOfPrice() {
        double totalPrice = 0;
        Collection<Ticket> values = collection.getCollection().values();
        for (Ticket ticket : values) {
            totalPrice += ticket.getPrice();
        }
        System.out.println("Сумма значений поля price для всех элементов коллекции: " + totalPrice);
    }


    /**
     * A command that removes items from a collection that have such name{@link FilterContainsNameCommand}
     */
    public void filterContainsName() {
        String input;
        if (isScriptWorking) {
            input = compositeCommand[0];
            clearCompositeCommand();
        } else {
            input = tokens[1];
        }
        ArrayList<Ticket> elements = new ArrayList<>();
        for (Ticket ticket : collection.getCollection().values()) {
            String value = ticket.getName();
            if (value.contains(input)) {
                elements.add(ticket);
            }
        }
        if (elements.isEmpty()) {
            System.out.println("В коллекции нет элементов, содержащих данную подстроку");
        } else {
            System.out.println("Данную подстроку в имени имеют следущие элементы:");
            for (Ticket element : elements) {
                System.out.println(element);
            }
        }
    }
}




