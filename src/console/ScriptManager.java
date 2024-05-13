package console;

import Command.*;
import java.io.*;
import java.util.*;

/**
 * This class works with the script
 */
public class ScriptManager {
    /**
     * Field that links to a script file
     */
    private File script;
    /**
     * A field that refers to a HashMap with keys - command names and the possibility of calling them
     */
    private Map<String, Command> commands;
    /**
     * A field that refers to an object with implementations of all commands
     */
    private CommandCatalog commandCatalog;

    public ScriptManager(File script, Map<String, Command> commands, CommandCatalog commandCatalog) {
        this.script = script;
        this.commands = commands;
        this.commandCatalog = commandCatalog;
    }

    /**
     * A method that calls the commands specified in the array received from the method {@link ScriptManager#scriptToTokens()}
     */
    public void executeScript() {
        String[] tokens = scriptToTokens();
        for (int i = 0; i < tokens.length; i++) {
            try {
                Command command = commands.get(tokens[i]);
                if (tokens[i].equalsIgnoreCase("remove_key") || tokens[i].equalsIgnoreCase("update_id") ||
                        tokens[i].equalsIgnoreCase("insert") || tokens[i].equalsIgnoreCase("remove_") ||
                        tokens[i].equalsIgnoreCase("execute_script") || tokens[i].equalsIgnoreCase("remove_lower") ||
                        tokens[i].equalsIgnoreCase("remove_lower_key") ||
                        tokens[i].equalsIgnoreCase("filter_contains_name")) {
                    commandCatalog.setCompositeCommand(Arrays.copyOfRange(tokens, i + 1, tokens.length));
                    System.out.println("ehhhf");
                }
                boolean isTokenCommand = commands.containsKey(tokens[i]);
                if (!isTokenCommand) {
                    continue;
                }
                command.execute();
            } catch (NullPointerException e) {
                System.err.println("Ошибка исполнения скрипта");
            }
        }
    }

    private String[] scriptToTokens() {
        char[] inputChar = new char[2048];
        try (InputStreamReader reader = new FileReader(script)) {
            reader.read(inputChar);
            reader.close();
            return getStrings(inputChar);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return new String[0];
        } catch (IOException e) {
            System.out.println("IO exception");
            return new String[0];
        }
    }

    /**
     * A method that reads a script file and converts it to a char array and return result of the {@link ScriptManager#getStrings(char[])}
     *
     * @return Command array
     */
    /*private String[] scriptToTokens() {
        try (Scanner scanner = new Scanner(script)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine().replaceAll("[\\r\\n]", " "));
            }
            return stringBuilder.toString().split(" ");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return new String[0];
    }

     */


    /**
     * Private method that turns an array of characters into an array of strings with individual commands
     *
     * @param inputChar An array of characters read from a file with a script method{@link ScriptManager#scriptToTokens()}
     * @return Command array
     */


    private static String[] getStrings(char[] inputChar) {
        List<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (char ch : inputChar) {
            if ((ch == ' ') || (ch == '\n') || ch == '\r' || ch == '\0') {
                if (token.length() > 0) {
                    tokens.add(token.toString());
                    token.setLength(0);
                }
            } else {
                token.append(ch);
            }
        }
        if (token.length() > 0) {
            tokens.add(token.toString());
        }
        return tokens.toArray(new String[0]);
    }
        /*
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[i] == '\0') {
                break;
            }
            if (inputChar[i] == '\r') {
                charBuilder.append(" ");
                continue;
            }
            if (inputChar[i] == '\n') {
                continue;
            }
            charBuilder.append(inputChar[i]);
        }
        String result = String.valueOf(charBuilder);
        String[] tokens = result.split(" ");
        System.out.println(tokens.toString());
        return tokens;
    }

         */

}