package Command;

import console.CommandCatalog;

public class InsertNullCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;

    public InsertNullCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.insertNull();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "insertNull: добавить новый элемент с заданным ключом";
    }
}
