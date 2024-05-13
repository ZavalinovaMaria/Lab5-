package Command;

import console.CommandCatalog;

public class InfoCommand implements Command{
    CommandCatalog commandCatalog;
    public InfoCommand(CommandCatalog commandCatalog){this.commandCatalog = commandCatalog;}
    @Override
    public void execute(){
        commandCatalog.info();
    }

    @Override
    public String description() {
        return "info : вывести в стандартный поток вывода информацию о коллекции";
    }
    @Override
    public String toString() {
        return "info";
    }
}
