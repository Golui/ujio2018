package io2018.ii.uj.edu.pl.jurpizza.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class BasketEntry implements Serializable {
    protected String name;
    protected int basePrice;
    protected int aditionalPrice;
    protected String size;

    public BasketEntry(String name, int price) {
        this.name = name;
        this.basePrice = price;
        this.aditionalPrice = 0;
        this.size = "";
    }

    public BasketEntry(BasketEntry b) {
        this.name = b.name;
        this.basePrice = b.basePrice;
        this.aditionalPrice = b.aditionalPrice;
        this.size = b.size;
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
        return name + size;
    }

    public int getPrice() {
        return basePrice + aditionalPrice;
    }
}
