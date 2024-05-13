package console;
import Command.*;
import exceptions.NotExistingValueException;
import exceptions.NotUniqueValueException;
import fileWork.FileReader;
import fileWork.Writer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import subjects.*;
import subjects.Сomporators.ComparatorDiscount;
//import subjects.Сomporators.ComparatorPrice;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static console.Console.firstFilePath;
import static console.Console.commands;

public class CommandCatalog {
    private final FileReader reader;
    private final Inserting insert;
    private final Writer writer;


    private TicketCollection collection;
    private Set<String> scriptHistory = new HashSet<>();
    //private Map<String, Command> commands;
    private final List<String> commandHistory = new ArrayList<>();

    private String[] compositeCommand = new String[9];

    private boolean isScriptWorking = false;

    private String[] tokens;

    public CommandCatalog(TicketCollection collection, FileReader reader, Writer writer, Inserting insert, Map<String, Command> commands) {
        this.collection = collection;
        this.reader = reader;
        this.writer = writer;
        //this.commands = commands;
        this.insert = insert;
    }
    public void addToHistory(String command){
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

    public void history() {
            if (commandHistory.isEmpty()) {
                System.out.println("История команд пуста");
                return; // завершение метода
            }
            for (String command : commandHistory.subList(Math.max(commandHistory.size() - 13, 0), commandHistory.size())) {
                System.out.println(command);
            }
        }

        public void executeScript(){
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
                if (scriptHistory.contains(compositeCommand[0])) {
                    System.out.println("This script has already been executed");
                    return;
                }
                if (isScriptWorking) {
                    scriptHistory.add(compositeCommand[0]);
                }
                else {
                    scriptHistory.add(tokens[1]);
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
        try {
            Integer key;
            if(isScriptWorking){
                key = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
            key = Integer.parseInt(tokens[1]);}
            Iterator<Ticket> iterator = collection.getCollection().values().iterator();
            while (iterator.hasNext()) {
                Ticket ticket = iterator.next();
                if (ticket.getId() < key) {
                    iterator.remove();
                    collection.deleteKey(ticket.getId());
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
            if(isScriptWorking){

                key = Integer.parseInt(compositeCommand[0]);
                clearCompositeCommand();
            } else {
            key = Integer.parseInt(tokens[1]);}
            if (collection.checkingExistence(key)) {
                Iterator<Ticket> iterator = collection.getCollection().values().iterator();
                while (iterator.hasNext()) {
                    Ticket ticket = iterator.next();
                    if (ticket.getPrice() < collection.getCollection().get(key).getPrice()) {
                        iterator.remove();
                        collection.deleteKey(ticket.getId());
                    }
                }
                System.out.println("Элеметы успешно удалены ");
                collection.updateData();
            }

        } catch (NumberFormatException e) {
            System.out.println("Ключ должен быть числом ");
        } catch (NotExistingValueException e) {
            System.out.println("Элемент с таким ключом не содержится в коллекции не может быть удален ");;
        }
    }

    public void save() {
    String filePath = null;
    String option ;
    System.out.println("Выберете место сохранения и введите соответствующую цифру:"+
            "\n 1. Сохранить коллекцию в файл, из которого производили чтение"+
            "\n 2.Сохранить коллекцию в другой файл"+
            "\n 3. Создать новый файл и сохранить коллекцию туда ");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            option = scanner.nextLine();
            if (option == null | option.isEmpty()) {
                System.out.println("Необходимо выбрать параметр сохраненея,введите число 1 или 2 ");
            }
            if(!option.equals("1") && !option.equals("2") && !option.equals("3")){
                System.out.println("Необходимо ввести 1, 2 или 3");
            }else {
                break;
            }
        }
        switch (option) {
            case "1" -> filePath = firstFilePath;

            case "2" -> {
                System.out.println("Введите путь до файла, в который хотите сохранить коллекцию");
                while(true) {
                    try{
                        String path = scanner.next();
                        File file = new File(path);
                        if (path == null || path.isEmpty())  {
                            System.out.println("Пустой путь недопустим");
                        }
                        if(!file.exists()){ throw  new FileNotFoundException("Файл не найден");}
                        else {
                            System.out.println("Путь к файлу успешно получен");
                            filePath = path;
                            break;
                        }
                    }catch (FileNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                }}
                case "3"->{
                    System.out.println("Введите путь, в котором хотите создать файл ");
                    while(true) {
                        String path = scanner.next();
                        File file = new File(path);
                        if (path == null || path.isEmpty())  {
                            System.out.println("Пустой путь недопустим");
                        }
                        else {
                            System.out.println("Путь к файлу успешно получен");
                            filePath = path;
                            break;}
                        try{
                            if(file.createNewFile()){
                                System.out.println("Файл создан");
                            }
                            else {System.out.println("беда ");}
                        }catch(IOException e ){
                            System.out.println("Ошибка при создании файла ");
                        }
                    }
            }

        }
    writer.writeToFile(collection, filePath);
}


    public void insert() {
        if(isScriptWorking){
            try {
                Ticket newTicket = insert.toBuildTicket(compositeCommand);
                if(insert.checkingUniqueness(newTicket.getId())){
                    collection.addNewKey(newTicket.getId());
                    collection.getCollection().put(newTicket.getId(), newTicket);}
            }catch (NumberFormatException E){
                System.out.println("Ошибка заполнения полей ");
            } catch (NotUniqueValueException e) {
            System.out.println("Элемент с таким id уже есть в коллекции");
        }

        }else{
        Ticket newTicket = insert.createTicket();
        collection.getCollection().put(newTicket.getId(), newTicket);}
        collection.updateData();
        System.out.println("Новый элемент добавлен в коллекцию");
    }
//сделать в методе ниже разный вывод текста Ж сейчас в любом случае выведет что элемент удален даже если его нет НО все работает
    public void removeKey() {
            try {
                Integer key;
                if(isScriptWorking){
                    key = Integer.parseInt(compositeCommand[0]);
                    clearCompositeCommand();
                }else{
                key = Integer.parseInt(tokens[1]);
                collection.deleteKey(key);
                collection.getCollection().remove(key);
                collection.updateData();

            }} catch (NumberFormatException e) {
                System.out.println("Ключ должен быть числом ");
            }
        }


        public void updateId () {
            try {
                Integer id;
                if(isScriptWorking){
                    id = Integer.parseInt(compositeCommand[0]);
                    clearCompositeCommand();
                }else{
                id = Integer.parseInt(tokens[1]);}
                if (collection.checkingExistence(id)) {
                    insert.toUpdateTicket(collection.getCollection().get(id),compositeCommand);
                    System.out.println("Значение элемента успешно обновлено");
                }

            } catch (NumberFormatException e) {
                System.out.println("Id должен быть числом ");
            }
            catch (NotExistingValueException e){
                System.out.println("Элемент с подобным ключем не обнаружен ");
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
            Scanner scanner = new Scanner(System.in);
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



