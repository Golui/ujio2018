package io2018.ii.uj.edu.pl.jurpizza.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BeverageTest {

    @Test
    public void testNaming() {
        Beverage b = new Beverage("Koka", new Beverage.Variant("0.33l", 400));
        assertEquals("Koka", b.getName());
        assertEquals("Koka", b.getBasketString());
        b.setVariant(0);
        assertEquals("Koka (0.33l)", b.getBasketString());
    }
}