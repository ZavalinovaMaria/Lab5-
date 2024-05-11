package Command;

import console.CommandCatalog;

public class InsertCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;

    public InsertCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.insert();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "insert: добавить новый элемент с заданным ключом";
    }
}
