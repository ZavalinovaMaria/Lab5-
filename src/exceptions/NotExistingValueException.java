package exceptions;

public class NotExistingValueException extends Exception{
    /**
     * Class exception that is thrown if the value does not exist in the collection.
     * @param message An error message explaining the reason for the exception.
     */
    public  NotExistingValueException(String message){
        super(message);
    }
}
