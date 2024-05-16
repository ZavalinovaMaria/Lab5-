package console;

import Command.*;
import fileWork.FileReader;
import fileWork.Writer;
import java.io.File;
import java.util.*;

public class Console {
    /**
     * Class in which all objects necessary for work are created and the program starts working
     */
    static Map<String, Command> commands = new HashMap<>();
    public static ArrayList<Integer> keyStoragee = new ArrayList<>();
    public static ArrayList<Integer> idVenueStorage = new ArrayList<>();
    public static String firstFilePath;

    /**
     * The starting point of the program. A method in which a database file is specified, commands are specified, and commands are entered.
     */
    public void toStart() {
        String path;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Введите путь для json файла");
            try {
                path = scanner.next();
                firstFilePath = path;
                File file = new File(path);
                if(!file.exists()){
                    System.out.println("Файл с указанной директорией не обнаружен");
                }
                else break;
            } catch (NoSuchElementException e) {
                System.out.println("Пустая строка, выход из программы");
                return;
            }
        }
            if (path.isEmpty()) {
                System.out.println("Передан пустой файл");
                return;
            }

            FileReader reader = new FileReader();
            Writer writer = new Writer();
            Inserting insert = new Inserting();
            keyStoragee = new ArrayList<>();
            idVenueStorage = new ArrayList<>();
            TicketCollection collection = new TicketCollection(reader.read(path));
            CommandCatalog commandCatalog = new CommandCatalog(collection, reader, writer, insert, commands);

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
                System.out.println("Введите команду");
                String query = null;
                try {
                    Scanner scan = new Scanner(System.in);
                    query = scan.nextLine().toLowerCase();
                } catch (NoSuchElementException e) {
                    System.out.println("Пустая строка, выход из программы");
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
                    System.out.println("Введенная команда не существует");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Недопустимый формат команды");
                }

            }
        }
    }
