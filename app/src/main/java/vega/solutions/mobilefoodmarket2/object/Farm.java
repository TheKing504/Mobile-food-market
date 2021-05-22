package vega.solutions.mobilefoodmarket2.object;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Farm {

    private int id;
    private String name;
    private String email;
    private String description;
    private String address;
    private double lat;
    private double lng;
    private String phone;
    private int rating;
    private String photo;
    private ArrayList<Item> items;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getPhone() {
        return phone;
    }

    public int getRating() {
        return rating;
    }

    public String getPhoto() {
        return photo;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", phone='" + phone + '\'' +
                ", rating=" + rating +
                ", photo='" + photo + '\'' +
                ", items=" + items +
                '}';
    }
}
