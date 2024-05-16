package console;

import exceptions.NotExistingValueException;
import exceptions.NotUniqueValueException;

import java.util.ArrayList;

import static console.Console.idVenueStorage;
import static console.Console.keyStoragee;
/**
 * Interface for checking the uniqueness of id and keys in a collection.
 */
public interface Checking{
    /**
     * Checks the uniqueness of the key value.
     *
     * @param value The value of the key to check.
     * @return true if the key value is unique, false otherwise.
     * @throws NotUniqueValueException If the key value is not unique.
     */
    default boolean  checkingUniqueness(Integer value) throws NotUniqueValueException{
        if(keyStoragee.contains(value)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", value));

        else return true;
    }
    default boolean  checkingIdUniqueness(Integer value) throws NotUniqueValueException{
        if(idVenueStorage.contains(value)) throw new NotUniqueValueException(String.format("Элемент с Venue id  со значением %s уже существует ", value));
        else return true;
    }

    /**
     * Adds a new key to the collection.
     *
     * @param key New key to add.
     */

     default void  addNewKey(Integer key){
        try{
            if(checkingUniqueness(key)){
                keyStoragee.add(key);
            }
        }
        catch (NotUniqueValueException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new venue`s id to the collection.
     *
     * @param id New id to add.
     */

    default void  addNewVenueId(Integer id){
        try {
            if(checkingIdUniqueness(id)){
                idVenueStorage.add(id);
            }
        }
        catch (NotUniqueValueException e){
            System.out.println(e.getMessage());
        }
    }

  /**
   * Checks the existence of an element with the given key in the collection and removes it.
   *
   * @param value Key to check for existence.
   * @throws NotExistingValueException If the element with the given key does not exist.
    */
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








