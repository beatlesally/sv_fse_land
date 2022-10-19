package strategy_demo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONExporter implements IExportable{

    @Override
    public void export(List<Article> articleList) {
        try {
            FileWriter fw = new FileWriter("JSONexport.json");
            fw.write("[\n");
            int length = articleList.size();
            for (Article a : articleList)
            {
                String line ="{ \"number\":"+a.getNumber()+
                        ", \"name\":\""+a.getName()+
                        "\", \"manufacturer\":\""+a.getManufacturer()+
                        "\"}\n";
                if(length >1)
                {
                    line+=",\n";
                }
                length--;
                fw.write(line);
            }
            fw.write("]");
            fw.flush(); //Buffer leeren
            fw.close(); //Schließen der Datei
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/* JSON TEMPLATE
[
    {
    "number":"1",
    "name":"Win10",
    "manufacturer":"Microsoft"
    },
    {
    "number":"2",
    "name":"Win10",
    "manufacturer":"Microsoft"
    }
]
 */
