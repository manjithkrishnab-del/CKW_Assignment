package ckw.models;

public class ProductDetails {

    private final String name;
    private final String price;

    public ProductDetails(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}