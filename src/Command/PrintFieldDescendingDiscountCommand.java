package Command;

import console.CommandCatalog;

public class PrintFieldDescendingDiscountCommand implements Command{
    CommandCatalog commandCatalog;

    public PrintFieldDescendingDiscountCommand(CommandCatalog commandCatalog){
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.printFieldDescendingDiscount();
    }
    @Override
    public String description() {
        return "printFieldDescendingDiscount : вывести значения поля discount всех элеменетов в порядке убывания";
    }
}