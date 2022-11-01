package pattern.strategy_demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter implements IExportable{

    @Override
    public void export(List<Article> articleList) {
        try {
            FileWriter fw = new FileWriter("CSVexport.csv");
            fw.write("Number,Name,Manufacturer\n");
            for (Article a : articleList) {
                String line =a.getNumber()+","+a.getName()+","+a.getManufacturer()+"\n";
                fw.write(line);
            }
            fw.flush(); //Buffer leeren
            fw.close(); //Schließen der Datei
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
