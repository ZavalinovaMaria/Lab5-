package console;

import Command.*;

import console.*;
import fileWork.FileReader;
import fileWork.FileWriter;
import subjects.*;

import java.util.*;

/* Class in which all objects necessary for work are created and the program starts working
 */
    public class Console {
    /* A field that refers to a HashMap with keys - command names and the possibility of calling them
     */
        Map<String, Command> commands = new HashMap<>();
    public static ArrayList<Integer> keyStoragee= new ArrayList<>();

        /**
         * The starting point of the program. A method in which a database file is specified, commands are specified, and commands are entered.
         */
        public void toStart() {
            String path;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the json file path");
            try {
                path = scanner.next();
            }
            catch (NoSuchElementException e){
                System.out.println("No line, exit from the program");
                return;
            }
            if (path.isEmpty()) {
                System.out.println("You didn't indicated the json file");
                return;
            }
            FileReader reader = new FileReader();
            FileWriter writer = new FileWriter();
            keyStoragee = new ArrayList<>();
            keyStoragee.addAll(reader.read(path).keySet());
            TicketCollection collection = new TicketCollection(reader.read(path));
            CommandCatalog commandCatalog = new CommandCatalog(collection, reader, writer, commands);


            commands.put("help", new HelpCommand(commandCatalog));
            commands.put("save", new SaveCommand(commandCatalog));
            commands.put("clear", new ClearCommand(commandCatalog));
            commands.put("show", new ShowCommand(commandCatalog));
            commands.put("exit", new ExitCommand(commandCatalog));
            commands.put("update_id", new UpdateIdCommand(commandCatalog));
            commands.put("info", new InfoCommand(commandCatalog));
            commands.put("insert_null", new InsertNullCommand(commandCatalog));
            commands.put("remove_key", new RemoveKeyCommand(commandCatalog));
            commands.put("execute_script_file_name", new ExecuteScriptCommand(commandCatalog));
            commands.put("remove_lower", new RemoveLowerCommand(commandCatalog));
            commands.put("remove_lower_key", new RemoveLowerKeyCommand(commandCatalog));
            commands.put("history", new HistoryCommand(commandCatalog));
            commands.put("filter_contains_name", new FilterContainsNameCommand(commandCatalog));
            commands.put("sum_of_price", new SumOfPriceCommand(commandCatalog));
            commands.put("print_field_descending_discount", new PrintFieldDescendingDiscountCommand(commandCatalog));
            System.out.println("Print the command");
            while (true) {
                String query = null;
                try{
                    Scanner scan = new Scanner(System.in);
                    query = scan.nextLine().toLowerCase();
                }
                catch (NoSuchElementException e){
                    System.out.println("No line, exit from the program");
                    break;
                }
                String[] tokens = query.split(" ");
                if (tokens.length == 0) {
                    tokens = query.split("\n");
                }
                commandCatalog.setTokens(tokens);
                Command command = commands.get(tokens[0]);
                try {
                    command.execute();
                } catch (NullPointerException e) {
                    System.out.println("The entered command does not exist");
                }
            }
        }
    }

