package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.OfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class MockOfferGetter implements OfferGetter {
    @Override
    public List<Pizza> downloadAvailablePizzas() {
        
        List<String> ing = new ArrayList<String>(Arrays.asList("Kanapka", "Pomidor", "aaaaaaaaaaaaaa", "asdasdasdasdasdas", "ASDASDASDASDASDASDASDASDASD"));
        
        List<Pizza> l = Arrays.asList(
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
                
               //new Pizza("Marysia", 1000, Arrays.asList("pomidory", "ser")),
               //new Pizza("Salamé", 1300, Arrays.asList("pomidory", "ser", "salami")),
               //new Pizza("Świnka", 1600, Arrays.asList("pomidory", "ser", "szynka", "boczek")),
               //new Pizza("Kanapka", 1400, Arrays.asList("pomidory", "ser", "szynka", "rzodkiewka", "ogórek", "chleb")),
               //new Pizza("Cebula", 1500, Arrays.asList("pomidory", "ser", "cebula biała", "cebula czerwona", "cebula zielona")),
               //new Pizza("Serowa", 1800, Arrays.asList("pomidory", "ser", "ser pleśniowy", "ser salatkowy w stylu", "ser w stylu górskim", "ser żółty w style cheddar")),
               //new Pizza("Pizza", 1100, Arrays.asList("pomidory", "ser", "ciasto do pizzy")),
               //new Pizza("Kebab", 1600, Arrays.asList("pomidory", "ser", "cebula", "mięso kebab")),
               //new Pizza("Amerykańska", 1000, Arrays.asList("pomidory", "ser", "frytki", "wołowina")),
               //new Pizza("Faux pas", 1000, Arrays.asList("pomidory", "ser", "bazylia", "mielona kawa")),
               //new Pizza("Java", 1400, Arrays.asList("pomidory", "ser", "obiekty", "garbage collector", "\"bezpieczeństow\"")),
               //new Pizza("C", 900, Arrays.asList("pomidory", "ser", "macro assembler")),
               //new Pizza("Filmowa", 1000, Arrays.asList("pomidory", "ser", "statysta", "kamera", "nitroceluloza", "bite szkło")),
               //new Pizza("Krykiet", 1000, Arrays.asList("pomidory", "ser", "ciasto na bazie mąki ze świerszczy", "świerze świerszcze", "cebula")),
               //new Pizza("Van Helsing", 1000, Arrays.asList("pomidory", "ser", "czosnek polski", "czosnek chiński", "sos czosnkowy"))
        );

        return l;
    }

    @Override
    public List<Beverage> downloadAvailableBeverages() {

        Beverage.Variant[] dummyVariants = new Beverage.Variant[]{
                new Beverage.Variant("0.33l", 400),
                new Beverage.Variant("0.5l", 800),
                new Beverage.Variant("0.7l", 1200),
                new Beverage.Variant("1l", 2000),
        };

        List<Beverage> bvgs = Arrays.asList(
                new Beverage("Koka", dummyVariants),
                new Beverage("Spiryt", dummyVariants),
                new Beverage("Dr. Jan", dummyVariants),
                new Beverage("Marzenia", dummyVariants),
                new Beverage("Czapi Pomarańczowy", dummyVariants)
        );

        return bvgs;
    }
}
