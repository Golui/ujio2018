package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.OfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class MockOfferGetter implements OfferGetter {

    private List<Pizza> pizzas;
    private List<Beverage> bvgs;

    public MockOfferGetter() {
        List<String> ing = new ArrayList<String>(Arrays.asList("Kanapka", "Pomidor", "aaaaaaaaaaaaaa", "asdasdasdasdasdas", "ASDASDASDASDASDASDASDASDASD"));

        this.pizzas = Arrays.asList(
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Cap", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Fungi", ing, new BasketEntry.Variant("32 cm", 9000)),
                new Pizza( "Meksicana", ing, new BasketEntry.Variant("32 cm", 10000)),
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000)),
                new Pizza( "Martgharita", ing, new BasketEntry.Variant("32 cm", 8000))
        );

        Beverage.Variant[] dummyVariants = new Beverage.Variant[]{
                new Beverage.Variant("0.33l", 400),
                new Beverage.Variant("0.5l", 800),
                new Beverage.Variant("0.7l", 1200),
                new Beverage.Variant("1l", 2000),
        };

        this.bvgs = Arrays.asList(
                new Beverage("Koka", dummyVariants),
                new Beverage("Spiryt", dummyVariants),
                new Beverage("Dr. Jan", dummyVariants),
                new Beverage("Marzenia", dummyVariants),
                new Beverage("Czapi Pomarańczowy", dummyVariants)
        );
    }

    @Override
    public List<Pizza> downloadAvailablePizzas() {
        return this.pizzas;
    }

    @Override
    public List<Beverage> downloadAvailableBeverages() {

        return this.bvgs;
    }
}
