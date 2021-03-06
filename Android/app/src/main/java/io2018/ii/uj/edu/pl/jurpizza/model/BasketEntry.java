package io2018.ii.uj.edu.pl.jurpizza.model;

import java.io.Serializable;

public abstract class BasketEntry implements Serializable {
    protected String name;
    protected int basePrice;
    protected int aditionalPrice;
    protected String size;
    private int quantity;

    public BasketEntry(String name, int price) {
        this.name = name;
        this.basePrice = price;
        this.aditionalPrice = 0;
        this.size = "";
        this.quantity = 1;
    }

    public BasketEntry(BasketEntry b) {
        this.name = b.name;
        this.basePrice = b.basePrice;
        this.aditionalPrice = b.aditionalPrice;
        this.size = b.size;
        this.quantity = b.quantity;
    }

    public void setSize(String size) {
        if (!size.equals("32cm") && !size.equals("200ml") && !size.equals("")) {
            this.size = "(" + size + ")";
        } else {
            this.size = "";
        }
    }

    public void setAditionalPrice(int aditionalPrice) {
        this.aditionalPrice = aditionalPrice;
    }

    public String getName() {
        if (quantity > 1) {
            return quantity + "x " + name + size;
        }
        return name + size;

    }

    public int getPrice() {
        return (basePrice + aditionalPrice) * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
