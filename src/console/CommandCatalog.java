package console;
import Command.*;
import fileWork.FileReader;
import subjects.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

public class CommandCatalog {
    private final FileReader reader;
    private final FileWriter writer;
    /* клсс для обработки команд пользователя
    const jsonString ="{ ffffff}';
    const person = JSON.parse(jsonString);

/* ЭТО НАШ АНАЛОГ МЭНЭДЖЕРА
ЕСЛИ ВКРАТЦЕ ТО ЭТО  К ЧЕМУ МЫ МОЖЕМ ПРИМЕНЯТЬ МЕТОДЫ

ЗДЕСЬ В КАЧЕСТВЕ МЕТОДОВ БУДУТ ВСЕ КОМАНДЫ КОТОРЫЕ НАМ НУЖНЫ
     */


    /**
     * Command to exit the application {@link Command.ExitCommand}
     */
    private TicketCollection collection;
    /* private json files*/
    private Map<String, Command> commands;
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

    public CommandCatalog(TicketCollection collection, FileReader reader, FileWriter writer, Map<String, Command> commands) {
        this.collection = collection;
        this.reader = reader;
        this.writer = writer;
        this.commands = commands;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * The method resets the variable{@link CommandCatalog#compositeCommand}
     */
    public void clearCompositeCommand() {
        compositeCommand = new String[9];
    }

    public void setCompositeCommand(String[] compositeCommand) {
        this.compositeCommand = compositeCommand;
    }

    /**
     * Command to call up descriptions of all commands{@link Command.HelpCommand}
     */
    public void help() {
        for (Command com : commands.values()) {
            System.out.println(com.description());
        }
    }

    /**
     * Command that saves the current collection instance to a file{@link Commands.ConcreteCommands.SaveToXmlCommand}
     *
     * @see XmlCommandsDOM
     */
    public void save() {
        try {
            if (xmlCommands.toSaveToXML() < 0) {
                System.out.println("Specified file is not exist");
                return;
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Ошибка сохранения " + e.getMessage());
        }
        System.out.println("Saved successfully");
    }

    /**
     * Command to exit the application {@link Command.ExitCommand}
     */
    public void exit() {
        System.out.println("Session ended");
        System.exit(0);
    }

    /**
     * Command to clear a collection {@link Command.ClearCommand}
     */
    public void clear() {
        collection.getCollection().clear();
        collection.updateData();
        System.out.println("Collection cleared");
    }

    /**
     * A command that prints to the console all objects in a collection and their fields {@link Command.ShowCommand}
     */
    public void show() {
        System.out.println("The collection: ");
        System.out.println(collection.getCollection());
    }

    /**
     * A command that adds a new object to the collection, created with {@link Filler} class methods
     * {@link Commands.ConcreteCommands.AddCommand}
     */
    public void insert(Integer Key) {
        // if (isScriptWorking) {
        // collection.getCollection().add(Filler.toBuildOrganization(compositeCommand));
        clearCompositeCommand();
        // } else {

        collection.getCollection().insert(Inserting.insert(key));
        // }
        collection.updateData();
        System.out.println("Organization successfully added");
    }


    /**
     * Command showing information about the current state of a collection {@link Command.InfoCommand}
     *
     * @see TicketCollection
     */
    public void info() {
        System.out.print("Collection information:");
        System.out.println(collection);
    }

    /**
     * A command that allows you to insert a new object at a given index in the collection {@link Commands.ConcreteCommands.InsertAnIndexCommand}
     */

    /**
     * A command that allows you to update an object with a given id {@link Commands.ConcreteCommands.UpdateCommand}
     */
    public void update() {
        try {
            long id;
            if (isScriptWorking) {
                id = Long.parseLong(compositeCommand[0]);
                compositeCommand = Arrays.copyOfRange(compositeCommand, 1, compositeCommand.length);
            } else {
                id = Long.parseLong(tokens[1]);
            }
            if (id < 0) {
                throw new ArithmeticException();
            }
            if (collection.setLastIdWorkedWith(id) < 0) {
                System.out.println("Organization with id " + id + " is not exist");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Id must be integer");
            return;
        } catch (ArithmeticException e) {
            System.out.println("Id cannot be negative");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, enter the id in the command");
            return;
        }
        Ticket ticket = collection.getCollection().stream().filter(o -> o.getId() == collection.getLastIdWorkedWith()).findFirst().get();
        collection.setLastIndexWorkedWith(collection.getCollection().indexOf(org));
        collection.getCollection().remove(org);
        if (isScriptWorking) {
            collection.getCollection().add(collection.getLastIndexWorkedWith(), Filler.toBuildOrganization(compositeCommand));
            clearCompositeCommand();
        } else {
            collection.getCollection().add(collection.getLastIndexWorkedWith(), Filler.fill());
        }
        collection.getCollection().get(collection.getLastIndexWorkedWith()).setId(collection.getLastIdWorkedWith());
        System.out.println("Organization with ID: " + collection.getLastIdWorkedWith() + " successfully updated");
    }

    /**
     * A command that creates an object of the {@link ScriptManager} class and starts the script process
     *
     * @see Command.ExecuteScriptCommand
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
                System.out.println("Specified file is not exist");
                return;
            }
            isScriptWorking = true;
            ScriptManager scriptManager = new ScriptManager(script, commands, this);
            System.out.println("Script is executing");
            if (isScriptWorking) {
                clearCompositeCommand();
            }
            scriptManager.executeScript();
            isScriptWorking = false;
            System.out.println("Script executed successfully");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, enter the file name in the command");
        }
    }

    /**
     * A command that allows you to remove elements smaller than the one specified by id {@link Commands.ConcreteCommands.RemoveLowerCommand}
     */
    public void removeLower() {
        try {
            long id;
            if (isScriptWorking) {
                id = Long.parseLong(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                id = Long.parseLong(tokens[1]);
            }
            if (id < 0) {
                throw new ArithmeticException();
            }
            if (collection.setLastIdWorkedWith(id) < 0) {
                System.out.println("Organization with id " + id + " is not exist");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Id must be integer");
            return;
        } catch (ArithmeticException e) {
            System.out.println("Id cannot be negative");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, enter the id in the command");
            return;
        }
        Organization organization = collection.getCollection().stream().filter(o -> o.getId() == collection.getLastIdWorkedWith()).findFirst().get();
        List<Organization> list = collection.getCollection().stream().filter(o -> o.getAnnualTurnover() + o.getEmployeesCount()
                >= organization.getAnnualTurnover() + organization.getEmployeesCount()).toList();
        if (list.size() == collection.getCollection().size()) {
            System.out.println("No one organization was deleted");
            return;
        }
        System.out.println(collection.getCollection().size() - list.size() + " organizations smaller than the specified one were successfully deleted");
        collection.setList(list);
    }

    /**
     * A command that allows you to delete an object by its id {@link Commands.ConcreteCommands.RemoveByIdCommand}
     */
    public void removeKey() {  //ключ
        try {
            long id;
            if (isScriptWorking) {
                id = Long.parseLong(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                id = Long.parseLong(tokens[1]);
            }
            if (id < 0) {
                throw new ArithmeticException();
            }
            if (collection.setLastIdWorkedWith(id) < 0) {
                System.out.println("Organization with id " + id + " is not exist");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Id must be integer");
            return;
        } catch (ArithmeticException e) {
            System.out.println("Id cannot be negative");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, enter the id in the command");
            return;
        }
        collection.setList(collection.getCollection().stream().filter(o -> o.getId() != collection.getLastIdWorkedWith()).toList());
        System.out.println("Organization with id: " + collection.getLastIdWorkedWith() + " was successfully removed");
    }

    public double sumOfPrice() {
        double totalPrice = 0;

        Collection<Ticket> values = collection.getCollection().values();
        for (Ticket ticket : values) {
            totalPrice += ticket.getPrice();
        }
        return totalPrice;
    }
    public void filterContainsName(){
        Scanner scanner = new Scanner(System.in);//по идее это нужно переделать под консоль
        System.out.print ("Введите подстроку, которую нужно найти:");
        String name = scanner.nextLine();
        Collection<Ticket> values = collection.getCollection().values();
        ArrayList<Ticket> elements = new ArrayList<>();
        for (Ticket ticket : values) {
            String value =ticket.getName();
            if(value.contains(name)) elements.add(ticket);
        }
        System.out.println("Данную подстроку в имени имеют следущие элементы:"+ elements);
        //вывод должен быть в столбик




}

    /**
     * A command that sorts a collection in the standard way {@link Commands.ConcreteCommands.SortCommand}
     * @see Organization#compareTo(Organization)
     */

    /*public void sort() {
        LinkedList<Organization> toSort = new LinkedList<>(collection.getCollection());
        Collections.sort(toSort);
        collection.setList(toSort);
    }*/

    /**
     *A command that removes items from a collection that have less than a specified annual turnover{@link Commands.ConcreteCommands.FilterGreaterThanAnnualTurnoverCommand}
     */
  /*  public void filterTurnover() {    содержит ли заданную подстроку
        try {
            long annualTurnover;
            if (isScriptWorking) {
                annualTurnover = Long.parseLong(compositeCommand[0]);
                clearCompositeCommand();
            } else {
                annualTurnover = Long.parseLong(tokens[1]);
            }
            collection.setLastAnnualTurnoverWorkedWith(annualTurnover);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, enter the annual turnover the command");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Annual Turnover must be Integer");
            return;
        }
        List<Organization> list = collection.getCollection().stream().filter(o -> o.getAnnualTurnover() > collection.getLastAnnualTurnoverWorkedWith()).toList();
        if (!list.isEmpty()) {
            System.out.println(list);
        } else {
            System.out.println("There aren't such organizations");
        }
    }*/

    /**
     * A command that removes one object of a given type from a collection{@link Commands.ConcreteCommands.RemoveAnyByTypeCommand}
     */
   /* public void removeByType() {   тоже по ключу
        try {
            String organizationType;
            if (isScriptWorking) {
                organizationType = compositeCommand[0];
                clearCompositeCommand();
            } else {
                organizationType = tokens[1];
            }
            int count = 0;
            for (OrganizationType orgT : OrganizationType.values()) {
                if (organizationType.equalsIgnoreCase(orgT.name)) {
                    collection.setLastOrganizationTypeWorkedWith(orgT);
                    break;
                }
                count++;
            }
            if (count == OrganizationType.values().length - 1) {
                System.out.println("There isn't such type");
                return;
            }
            int amount = collection.getAmountOfElements();
            Optional<Organization> organization = collection.getCollection().stream().filter(o -> o.getType() == collection.getLastOrganizationTypeWorkedWith()).findAny();
            if (organization.isEmpty()) {
                System.out.println("There isn't any organization with type: " + collection.getLastOrganizationTypeWorkedWith().name);
            } else {
                collection.getCollection().remove(organization.get());
                collection.updateData();
            }
            if (amount != collection.getAmountOfElements()) {
                System.out.println("One organization with type: \"" + collection.getLastOrganizationTypeWorkedWith().name + "\" was successfully deleted");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Type not entered");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please, enter the type in the command");
        }
    } */

    /**
     * A command that displays a list of objects in a collection in ascending order {@link Commands.ConcreteCommands.PrintAscendingCommand}
     */
    /*public void printAscending() {   у них по возрастания нам нужно по убыванию sort по disount
        sort();
        System.out.println(collection.getCollection());
    }*/
}
