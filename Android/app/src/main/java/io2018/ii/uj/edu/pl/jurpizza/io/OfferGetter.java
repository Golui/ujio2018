package io2018.ii.uj.edu.pl.jurpizza.io;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public interface OfferGetter extends Serializable {
    public List<Pizza> downloadAvailablePizzas();
    public List<Beverage> downloadAvailableBeverages();
}
