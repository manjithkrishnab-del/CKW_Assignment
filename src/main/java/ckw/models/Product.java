package ckw.models;

public enum Product {

    BACKPACK("product-0", "Sauce Labs Backpack", "$29.99"),
    BIKE_LIGHT("product-1", "Sauce Labs Bike Light", "$9.99"),
    BOLT_TSHIRT("product-2", "Sauce Labs Bolt T-Shirt", "$15.99"),
    FLEECE("product-3", "Sauce Labs Fleece Jacket", "$49.99"),
    ONESIE("product-4", "Sauce Labs Onesie", "$7.99"),
    RED_TSHIRT("product-5", "Test.allTheThings() T-Shirt (Red)", "$15.99");



    private final String resourceId;
    private final String name;
    private final String price;

    Product(String resourceId, String name,String price) {
        this.resourceId = resourceId;
        this.name = name;
        this.price = price;
    }
    public String getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

}