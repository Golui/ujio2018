package io2018.ii.uj.edu.pl.jurpizza.io;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public interface OfferGetter {
    public List<Pizza> downloadAvailablePizzas();
    public List<Beverage> downloadAvailableBeverages();
}
