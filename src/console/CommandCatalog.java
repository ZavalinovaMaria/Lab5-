package console;
import Command.*;
import fileWork.FileReader;
import subjects.*;
import subjects.Сomporators.ComparatorDiscount;
import subjects.Сomporators.ComparatorPrice;

import java.io.FileWriter;
import java.util.*;



public class CommandCatalog {
    private final FileReader reader;
    private final FileWriter writer;
    private final Inserting insert;


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

    public CommandCatalog(TicketCollection collection, FileReader reader, Inserting insert, FileWriter writer, Map<String, Command> commands) {
        this.collection = collection;
        this.reader = reader;
        this.writer = writer;
        this.commands = commands;
        this.insert = insert;
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
        collection.updateCharacteristic();
        System.out.println("Collection cleared");
    }

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
    }

            /**
             * A command that prints to the console all objects in a collection and their fields {@link Command.ShowCommand}
             */
            public void show () {
                System.out.println("The collection: ");
                System.out.println(collection.getCollection());
            }
            public void printFieldDescendingDiscount () {
                List<Ticket> sortable = new ArrayList<>(collection.getCollection().values());
                Comparator<Ticket> sortibility = new ComparatorDiscount();
                Collections.sort(sortable, sortibility);
                Collections.reverse(sortable);
                System.out.println("ля ля яля что сделали ");
                for (Ticket ticket : sortable) {
                    System.out.println(ticket.getPrice());
                }
                //может быть вывод красивее

            }
            public void removeLowerKey() {
                Integer key;
                Iterator<Ticket> iterator = collection.getCollection().values().iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getId() < key) {
                        iterator.remove(); // Безопасное удаление элемента с использованием итератора
                    }
                }
                System.out.println("Элеметы успешно удалены ");
            }


            public void removeLower () {
                double price;
                List<Ticket> sortable = new ArrayList<>(collection.getCollection().values());
                Comparator<Ticket> sortibility = new ComparatorPrice();
                Collections.sort(sortable, sortibility);


                System.out.println("ля ля яля что сделали ");
                for (Ticket ticket : sortable) {
                    System.out.println(ticket.getPrice());
                }
                //может быть вывод красивее

            }





/*
    /**
     * A command that adds a new object to the collection, created with {@link Filler} class methods
     * {@link Commands.ConcreteCommands.AddCommand}

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
            public void info () {
                System.out.print("Collection information:");
                System.out.println(collection);
            }

            /**
             * A command that allows you to insert a new object at a given index in the collection {@link Commands.ConcreteCommands.InsertAnIndexCommand}
             *
             * @return
             */
/*
    /**
     * A command that allows you to update an object with a given id {@link Commands.ConcreteCommands.UpdateCommand}

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
    /*
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


/*
    /**
     * A command that allows you to delete an object by its id {@link Commands.ConcreteCommands.RemoveByIdCommand}

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

 */
            public void insertNull () {
                Inserting insert = new Inserting();
                Ticket newTicket = insert.toBuildTicket();
                collection.addTicket(newTicket.getId(), newTicket);
                System.out.println("Новый элемент добавлен в коллекцию");
            }

    public void removeKey() {

    }
            public void updateId() {
                try {
                    Integer id;
                    if (collection.getCollection().containsKey(id)) {
                        Ticket ticket = collection.getCollection().get(id);
                        ticket = insert.toUpdateTicket(id);
                    }

                } catch (Exception e) {
                    System.out.println(" ");
                }
            }


            public void sumOfPrice () {
                double totalPrice = 0;

                Collection<Ticket> values = collection.getCollection().values();
                for (Ticket ticket : values) {
                    totalPrice += ticket.getPrice();
                }
                System.out.println("Сумма значений поля price для всех элементов коллекции: "+totalPrice);
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
                System.out.println("Данную подстроку в имени имеют следущие элементы:" + elements);
                //вывод должен быть в столбик


            }


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

        }
    }



