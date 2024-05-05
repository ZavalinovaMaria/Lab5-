package Command;
import console.CommandCatalog;
public class RemoveLowerKeyCommand implements Command{
    CommandCatalog commandCatalog;
    public RemoveLowerKeyCommand(CommandCatalog commandCatalog){this.commandCatalog = commandCatalog;}
    @Override
    public void execute(){
       // commandCatalog.removeLowerKey();
    }

    @Override
    public String description() {
        return "removeLowerKey : удалить из коллекции все элементы,меньшие,чем заданный";
    }
}