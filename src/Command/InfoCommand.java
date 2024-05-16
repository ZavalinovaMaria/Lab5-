package Command;

import console.CommandCatalog;

public class InfoCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public InfoCommand(CommandCatalog commandCatalog){this.commandCatalog = commandCatalog;}
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.info();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции";
    }
    @Override
    public String toString() {
        return "info";
    }
}
