package console;

import Command.*;

import fileWork.FileReader;
import fileWork.Writer;

import java.util.*;

/* Class in which all objects necessary for work are created and the program starts working
 */
public class Console {
    /* A field that refers to a HashMap with keys - command names and the possibility of calling them
     */
    static Map<String, Command> commands = new HashMap<>();
    public static ArrayList<Integer> keyStoragee= new ArrayList<>();
    public static String firstFilePath;

    /**
     * The starting point of the program. A method in which a database file is specified, commands are specified, and commands are entered.
     */
    public void toStart() {
        String path;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the json file path");
        try {
            path = scanner.next();
            firstFilePath = path;

        }
        catch (NoSuchElementException e){
            System.out.println("No line, exit from the program");
            return;
        }
        if (path.isEmpty()) {
            System.out.println("Передан пустой файл");
            return;
        }
        FileReader reader = new FileReader();
        Writer writer = new Writer();
        Inserting insert = new Inserting();
        keyStoragee = new ArrayList<>();
        TicketCollection collection = new TicketCollection(reader.read(path));
        CommandCatalog commandCatalog = new CommandCatalog(collection, reader,writer, insert, commands);


        commands.put("help", new HelpCommand(commandCatalog));
        commands.put("save", new SaveCommand(commandCatalog));
        commands.put("clear", new ClearCommand(commandCatalog));
        commands.put("show", new ShowCommand(commandCatalog));
        commands.put("exit", new ExitCommand(commandCatalog));
        commands.put("update_id", new UpdateIdCommand(commandCatalog));
        commands.put("info", new InfoCommand(commandCatalog));
        commands.put("insert", new InsertCommand(commandCatalog));
        commands.put("remove_key", new RemoveKeyCommand(commandCatalog));
        commands.put("execute_script", new ExecuteScriptCommand(commandCatalog));
        commands.put("remove_lower", new RemoveLowerCommand(commandCatalog));
        commands.put("remove_lower_key", new RemoveLowerKeyCommand(commandCatalog));
        commands.put("history", new HistoryCommand(commandCatalog));
        commands.put("filter_contains_name", new FilterContainsNameCommand(commandCatalog));
        commands.put("sum_of_price", new SumOfPriceCommand(commandCatalog));
        commands.put("print_field_descending_discount", new PrintFieldDescendingDiscountCommand(commandCatalog));
        while (true) {
            System.out.println("Print the command");
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
                commandCatalog.addToHistory(String.valueOf(command));
            } catch (NullPointerException e) {
                System.out.println("The entered command does not exist");
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Недопустимый формат команды");}

        }
    }
}