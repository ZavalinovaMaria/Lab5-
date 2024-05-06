package exceptions;

public class NullValueException extends Exception{
    public NullValueException(String message,Throwable cause){
        super(message,cause);
    }
}