package io2018.ii.uj.edu.pl.jurpizza.model;

public class Beverage extends BasketEntry {

    public Beverage(String s, Variant... variants) {
        super(s, variants);
    }

    public Beverage(Beverage b) {
        super(b);
    }
}
