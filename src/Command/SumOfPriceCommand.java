package Command;

import console.CommandCatalog;

public class SumOfPriceCommand implements Command{
    CommandCatalog commandCatalog;

    public SumOfPriceCommand (CommandCatalog commandCatalog){
        this.commandCatalog = commandCatalog;
    }
    @Override
    public void execute(){
        commandCatalog.sumOfPrice();
    }
    @Override
    public String description() {
        return "sumOfPrice : вывести сумму значений поля price всех элеменетов коллекции";
    }
    @Override
    public String toString() {
        return "sum_of_price";
    }

}