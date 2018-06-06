package io2018.ii.uj.edu.pl.jurpizza.model;

import java.io.Serializable;

public interface BasketEntry extends Serializable {

    String getName();

    int getPrice();

    String getBasketString();
}
