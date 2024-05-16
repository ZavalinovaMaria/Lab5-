package Command;

import console.CommandCatalog;

public class FilterContainsNameCommand implements Command {
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public  FilterContainsNameCommand(CommandCatalog commandCatalog){
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.filterContainsName();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "filter_contains_name {name}: вывести элеменеты, значения поля name которых содержит заданную построку";
    }
    @Override
    public String toString() {
        return "filter_contains_name";
    }
}