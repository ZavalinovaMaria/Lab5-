package Command;

import console.CommandCatalog;

public class SumOfPriceCommand implements Command{
    /**
     * A field that refers to an object with implementations of all commands
     */
    CommandCatalog commandCatalog;
    public SumOfPriceCommand (CommandCatalog commandCatalog){
        this.commandCatalog = commandCatalog;
    }
    /**
     * The command that calls the required method from {@link CommandCatalog}
     */
    @Override
    public void execute(){
        commandCatalog.sumOfPrice();
    }
    /**
     * Method that returns command description
     * @return Command description
     */
    @Override
    public String description() {
        return "sum_of_price : вывести сумму значений поля price всех элеменетов коллекции";
    }
    @Override
    public String toString() {
        return "sum_of_price";
    }

}