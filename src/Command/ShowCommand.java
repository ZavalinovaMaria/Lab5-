package Command;

import console.CommandCatalog;

public class ShowCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public ShowCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.show();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "show: вывод в стандартный поток элементов коллекции в строковом представлении";
    }
    @Override
    public String toString(){
        return "show";
    }
}
