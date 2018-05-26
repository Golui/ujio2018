package io2018.ii.uj.edu.pl.jurpizza.model;

public class Beverage implements BasketEntry {

    @Override
    public String getName() {
        return "DUMMY";
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public String getBasketString() {
        return "DUMMY";
    }
}
