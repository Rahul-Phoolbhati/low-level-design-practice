package ecom.models;

import java.util.UUID;
import ecom.enums.ProductCategory;

public class Product {

    private final String id;
    private int stock;
    private String name;
    private ProductCategory cat;
    private int price;

    public Product(String name, int stock, ProductCategory category, int price) {
        this.name = name;
        this.stock = stock;
        this.id = UUID.randomUUID().toString();
        this.cat = category;
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void updateStock(int q) {
        this.stock = q;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public ProductCategory getCategory(){
        return this.cat;
    }

    public int getPrice() {
        return price;
    }
}