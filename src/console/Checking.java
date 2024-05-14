package console;

import exceptions.NotExistingValueException;
import exceptions.NotUniqueValueException;

import java.util.ArrayList;

import static console.Console.idVenueStorage;
import static console.Console.keyStoragee;

public interface Checking{
    default boolean  checkingUniqueness(Integer value) throws NotUniqueValueException{
        if(keyStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", value));

        else return false;
    }
    default boolean  checkingIdUniqueness(Integer value) throws NotUniqueValueException{
        if(idVenueStorage.contains(value)) throw new NotUniqueValueException(String.format("Элемент с Venue id  со значением %s уже существует ", value));

        else return false;
    }

     default void  addNewKey(Integer key){
        try{
            if(!checkingUniqueness(key)){
                keyStoragee.add(key);
            }
        }
        catch (NotUniqueValueException e){
            System.out.println(e.getMessage());
        }
    }
    default void  addNewVenueId(Integer id){
        try {
            if(!checkingIdUniqueness(id)){
                idVenueStorage.add(id);
            }
        }
        catch (NotUniqueValueException e){
            System.out.println(e.getMessage());
        }
    }

    default void checkExistence(Integer value) throws NotExistingValueException {
        if(!keyStoragee.contains(value)) throw new NotExistingValueException(String.format("Элемент с ключом со значением %s не содержится в коллекции ", value));

        else keyStoragee.remove(value);
    }
    default Boolean checkingExistence(Integer value) throws NotExistingValueException {
        if(!keyStoragee.contains(value)) throw new NotExistingValueException(String.format("Элемент с ключом со значением %s не содержится в коллекции ", value));
        else {
            return true;
        }
    }
}








