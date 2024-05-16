package Command;

import console.CommandCatalog;

public class RemoveLowerCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public RemoveLowerCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.removeLower();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "remove_lower {key}: удалить из коллекции все элементы, меньшие чем заданный";
    }
    @Override
    public String toString() {
        return "remove_lower";
    }
}

