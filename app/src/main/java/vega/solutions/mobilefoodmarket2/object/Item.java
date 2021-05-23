package vega.solutions.mobilefoodmarket2.object;

public class Item {

    private int id;
    private String name;
    private float price;
    private String unit;
    private String category;

    public Item(int id, String name, float price, String unit, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
