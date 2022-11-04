package wr;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LogObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        String[] list = (String[]) arg;
        String[] cutWR = list[0].split("2");

        try {
            FileWriter myWriter = new FileWriter("C:\\school\\5AAIF\\FSE_LAND\\sv_fse_land\\wr_observer\\src\\main\\java\\wr\\logs.txt",true);
            myWriter.write(new Date()+"\t");
            myWriter.write("Ausgangswährung: "+ cutWR[0] + "\t");
            myWriter.write("Ausgangsbetrag: "+ list[1] + "\t");
            myWriter.write("Zielwährung: "+ cutWR[1] + "\t");
            myWriter.write("Zielbetrag: "+ list[2] + "\t\n");
            myWriter.close();
            System.out.println("Successfully wrote to the log file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
