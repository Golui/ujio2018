package io2018.ii.uj.edu.pl.jurpizza.model;

import java.util.List;

public class Pizza extends BasketEntry {
    private int id;
    private String ingredients;

    public Pizza(String name, List<String> ingredients, Variant... variants) {
        super(name, variants);
        StringBuffer sb = new StringBuffer();
        for (String s : ingredients) sb.append(s).append(", ");
        sb.delete(sb.length() - 2, sb.length());
        this.ingredients = sb.toString();
    }

    public Pizza(Pizza b) {
        super(b);
        this.id = b.id;
        this.ingredients = b.ingredients;
    }

    public int getId() {
        return id;
    }

    public String getIngredients() {
        return ingredients;
    }
}
