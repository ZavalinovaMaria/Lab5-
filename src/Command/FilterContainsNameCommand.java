package Command;

import console.CommandCatalog;

public class FilterContainsNameCommand implements Command {
    CommandCatalog commandCatalog;

    public  FilterContainsNameCommand(CommandCatalog commandCatalog){
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.filterContainsName();
    }
    @Override
    public String description() {
        return "filterContainsName : вывести элеменеты, значения поля name которых содержит заданную построку";
    }
    @Override
    public String toString() {
        return "filter_contains_name";
    }
}