package Command;

import console.CommandCatalog;

public class PrintFieldDescendingDiscountCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public PrintFieldDescendingDiscountCommand(CommandCatalog commandCatalog){
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.printFieldDescendingDiscount();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "print_field_descending_discount : вывести значения поля discount всех элеменетов в порядке убывания";
    }
    @Override
    public String toString() {
        return "print_field_descending_discount";
    }
}