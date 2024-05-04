package Command;

public interface Command {
    /**
     * Command execution method
     */
    void execute();

    /**
     * Method returning description
     * @return Command description
     */
    String description();
}