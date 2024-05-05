package console;

import exceptions.NotExistingValueException;
import exceptions.NotUniqueValueException;

import java.util.ArrayList;

import static console.Console.keyStoragee;

public interface Checking{
    default Boolean  checkingUniqueness(Integer value) throws NotUniqueValueException{
        if(keyStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", value));

        else return false;
    }
    //default void checkingUniqueness(Integer value) throws NotUniqueValueException{}
    void addNewKey(Integer key);
    default void checkExistence(Integer value) throws NotExistingValueException {
        if(!keyStoragee.contains(value)) throw new NotExistingValueException(String.format("Элемент с ключом со значением %s не содержится в коллекции ", value));

        else keyStoragee.remove(value);
    }

    default void checkUniqueness(Integer value) throws NotUniqueValueException{
        if(keyStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", value));

        else keyStoragee.add(value);
    }
}








