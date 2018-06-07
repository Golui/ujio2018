package io2018.ii.uj.edu.pl.jurpizza.model;

public class Beverage extends BasketEntry {

    public Beverage(String s, int basePrice) {
        super(s, basePrice);
    }

    public Beverage(Beverage b) {
        super(b);
    }
}
