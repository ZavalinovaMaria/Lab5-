import console.Console;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        //if(args.length ==0) return;
        String filepath = null;
         filepath = args[0];
        Console console = new Console();
        console.toStart(filepath);

        //   /Users/mariazavalinova/Desktop/proga/lab4/src/TestFile.json
        // /Users/mariazavalinova/Desktop/proga/lab4/src/test.json
        //   execute_script /Users/mariazavalinova/Desktop/proga/lab4/src/scriptt.txt   execute_script
        //    /Users/mariazavalinova/Desktop/proga/lab4/src/te.json
    }
}
