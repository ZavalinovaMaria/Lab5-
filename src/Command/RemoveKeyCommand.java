package Command;

import console.CommandCatalog;

public class RemoveKeyCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;

    public RemoveKeyCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.removeKey();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "removeKey: удалить элемент из коллекции по его ключу";
    }
    @Override
    public String toString() {
        return "remove_key";
    }
}
