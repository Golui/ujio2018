package io2018.ii.uj.edu.pl.jurpizza.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Beverage implements BasketEntry {

    public Beverage(String s, Variant... variants)
    {
        this.baseName = s;
        this.variants = new ArrayList<>(Arrays.asList(variants));
    }

    public Beverage(Beverage b)
    {
        this.variants = new ArrayList<>(b.variants);
        Collections.sort(this.variants);
        this.baseName = b.baseName;
        this.variant = b.variant;
    }

    public static class Variant implements Comparable<Variant>
    {
        String volume;
        int price;

        public Variant(String v, int p)
        {
            this.volume = v;
            this.price = p;
        }

        @Override
        public int compareTo(Variant o2) {
            return Integer.compare(this.price, o2.price);
        }
    }

    private String baseName;
    private List<Variant> variants;
    private int variant = -1;

    @Override
    public String getName() {
        return baseName;
    }

    @Override
    /**
     * Returns lowest price, to entice clients
     */
    public int getPrice() {
        return this.variants.get(this.variant == -1 ? 0 : this.variant).price;
    }

    public List<Variant> getAvailableVolumes() { return this.variants; }

    public void setVariants(List<Variant> var) {
        this.variants = var;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    @Override
    public String getBasketString() {
        return this.baseName + (this.variant != -1 ? " (" + this.variants.get(this.variant).volume + ")" : "");
    }
}
