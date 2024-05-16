package Command;

import console.CommandCatalog;

public class UpdateIdCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public UpdateIdCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.updateId();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "update_id {id}: обновить значение элемента коллекции, id которого равен заданному";
    }
    @Override
    public String toString() {
        return "update_id";
    }
}
