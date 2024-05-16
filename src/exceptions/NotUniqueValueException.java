package exceptions;

public class NotUniqueValueException extends Exception{
    /**
     * Class exception that is thrown if the value is non-unique.
     * @param message An error message explaining the reason for the exception.
     */
    public NotUniqueValueException(String message){
        super(message);
    }
}
