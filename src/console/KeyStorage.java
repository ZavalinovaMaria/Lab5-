package console;

import exceptions.NotUniqueValueException;

import java.util.ArrayList;
import java.util.Set;

public class KeyStorage {

    public static ArrayList<Integer> keyStorage;
    public KeyStorage(){
        ArrayList<Integer> keyStorage = new ArrayList<>();


    }

    public void checkKeyUniqueness(Integer key) throws NotUniqueValueException {
        if(keyStorage.contains(key)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", key));
        else keyStorage.add(key);
    }

    public void addNewKey(Integer key){
        try {
            checkKeyUniqueness(key);
        }
        catch (NotUniqueValueException e ){
            System.out.println(e.getMessage());
        }
    }


    public void deleteKey(Integer key){
        if(keyStorage.contains(key)) keyStorage.remove(key);
        else System.out.println("ключа нет такого ");

    }

    public void addAll(Set<Integer> integers) {
    }
}
