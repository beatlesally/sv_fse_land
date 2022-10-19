package strategy_demo;

import java.util.List;

public class ExporterKontext {
    private IExportable exporter;

    public ExporterKontext(IExportable exporter) {
        this.exporter = exporter;
    }

    public void setExporter(IExportable exp) {
        this.exporter = exp;
    }

    public void doExport(List<Article> articleList)
    {
        this.exporter.export(articleList);
    }
}
