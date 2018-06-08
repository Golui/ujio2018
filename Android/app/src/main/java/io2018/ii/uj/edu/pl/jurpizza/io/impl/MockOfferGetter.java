package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.OfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class MockOfferGetter implements OfferGetter {
    @Override
    public List<Pizza> downloadAvailablePizzas() {
        List<Pizza> pizzaList = Arrays.asList(
                new Pizza("Marysia", Arrays.asList("pomidory", "ser"), 900),
                new Pizza("Salamé", Arrays.asList("pomidory", "ser", "salami"), 1300),
                new Pizza("Świnka", Arrays.asList("pomidory", "ser", "szynka", "boczek"), 1600),
                new Pizza("Kanapka", Arrays.asList("pomidory", "ser", "szynka", "rzodkiewka", "ogórek", "chleb"), 1400),
                new Pizza("Cebula", Arrays.asList("pomidory", "ser", "cebula biała", "cebula czerwona", "cebula zielona"), 1500),
                new Pizza("Serowa", Arrays.asList("pomidory", "ser", "ser pleśniowy", "ser salatkowy w stylu", "ser w stylu górskim", "ser żółty w style cheddar"), 1800),
                new Pizza("Kebab", Arrays.asList("pomidory", "ser", "cebula", "mięso kebab"), 1600),
                new Pizza("Amerykańska", Arrays.asList("pomidory", "ser", "frytki", "wołowina"), 1000),
                new Pizza("Faux pas", Arrays.asList("pomidory", "ser", "bazylia", "mielona kawa"), 1000),
                new Pizza("Java", Arrays.asList("pomidory", "ser", "obiekty", "garbage collector", "\"bezpieczeństwo\""), 1400),
                new Pizza("C", Arrays.asList("pomidory", "ser", "macro assembler"), 900),
                new Pizza("Filmowa", Arrays.asList("pomidory", "ser", "statysta", "kamera", "nitroceluloza", "bite szkło"), 1000),
                new Pizza("Krykiet", Arrays.asList("pomidory", "ser", "ciasto na bazie mąki ze świerszczy", "świerze świerszcze", "cebula"), 1000),
                new Pizza("Van Helsing", Arrays.asList("pomidory", "ser", "czosnek polski", "czosnek chiński", "sos czosnkowy"), 1000)
        );

        return pizzaList;
    }

    @Override
    public List<Beverage> downloadAvailableBeverages() {

        List<Beverage> beverageList = Arrays.asList(
                new Beverage("Woda", 300),
                new Beverage("Koka", 400),
                new Beverage("Spiryt", 400),
                new Beverage("Dr. Jan", 500),
                new Beverage("Marzenia", 400),
                new Beverage("Czapi", 500)
        );

        return beverageList;
    }
}
