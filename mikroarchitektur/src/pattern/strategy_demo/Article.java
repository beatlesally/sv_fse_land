package pattern.strategy_demo;

public class Article {
    private String number;
    private String name;
    private String manufacturer;

    public Article(String number, String name, String manufacturer) {
        this.number = number;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
