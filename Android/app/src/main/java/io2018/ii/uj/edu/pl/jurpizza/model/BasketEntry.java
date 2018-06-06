package io2018.ii.uj.edu.pl.jurpizza.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class BasketEntry implements Serializable {
    protected String baseName;
    protected List<Variant> variants;
    protected int variant = -1;

    public BasketEntry(String s, Variant... variants)
    {
        this.baseName = s;
        this.variants = new ArrayList<>(Arrays.asList(variants));
    }

    public BasketEntry(BasketEntry b)
    {
        this.variants = new ArrayList<>(b.variants);
        Collections.sort(this.variants);
        this.baseName = b.baseName;
        this.variant = b.variant;
    }

    public static class Variant implements Comparable<Variant>, Serializable
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

    public String getName() {
        return baseName;
    }

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

    public String getBasketString() {
        return this.baseName + (this.variant != -1 ? " (" + this.variants.get(this.variant).volume + ")" : "");
    }
}
