package Command;

import console.CommandCatalog;

public class ExitCommand implements Command {
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;

    public ExitCommand(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute() {
        commandCatalog.exit();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "exit: завершить программу (без сохранения в файл)";
    }
    @Override
    public String toString() {
        return "exit";
    }
}
