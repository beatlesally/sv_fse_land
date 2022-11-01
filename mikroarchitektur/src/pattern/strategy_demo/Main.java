package pattern.strategy_demo;

import java.util.ArrayList;

//Entwurfsmuster Strategy - etablierte Vorgehensweise

public class Main {
    public static void main(String[] args) {
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article("1","Win10", "Microsoft"));
        articles.add(new Article("2","Ubuntu", "Linux"));
        articles.add(new Article("3","Android", "Google"));
        articles.add(new Article("4","MacOS", "Apple"));
        articles.add(new Article("5","RedHat", "GNU"));

        ExporterKontext export = new ExporterKontext(new CSVExporter());
        //export.setExporter(new JSONExporter());
        export.doExport(articles);



    }
}
