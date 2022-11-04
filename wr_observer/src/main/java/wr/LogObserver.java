package wr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class LogObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        try {
            FileWriter myWriter = new FileWriter("C:\\school\\5AAIF\\FSE_LAND\\sv_fse_land\\wr_observer\\src\\main\\java\\wr\\logs.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
