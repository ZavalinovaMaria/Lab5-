package Command;

import console.CommandCatalog;

public class RemoveLowerCommand implements Command{
    /* A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;

    public RemoveLowerCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.removeLower();
    }
    /* Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "removeLower: удалить из коллекции все элементы, меньшие чем заданный";
    }
}

