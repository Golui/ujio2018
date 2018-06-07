package io2018.ii.uj.edu.pl.jurpizza.model;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;

public class Pizza extends BasketEntry {
    private String ingredients;
    private String sauce;

    public Pizza(String name, List<String> ingredients, int basePrice) {
        super(name, basePrice);
        StringBuffer sb = new StringBuffer();
        for (String s : ingredients) sb.append(s).append(", ");
        sb.delete(sb.length() - 2, sb.length());
        this.ingredients = sb.toString();
        this.sauce = "brak";
    }

    public Pizza(Pizza b) {
        super(b);
        this.ingredients = b.ingredients;
        this.sauce = "";
    }

    @Override
    public String getName() {
        String s = super.getName();
        if (!sauce.equals("brak")) {
            return super.getName() +  " + " + sauce;
        }
        return s;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getSauce() {
        return sauce;
    }
}
