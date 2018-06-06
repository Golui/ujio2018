package io2018.ii.uj.edu.pl.jurpizza.model;

import java.util.List;

public class Pizza implements BasketEntry {
    public int id;
    public String pizzaName;
    public int pizzaPrice;
    public String ingredients;

    public Pizza(int id, String pizzaName, int pizzaPrice, List<String> ingredients) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.pizzaPrice = pizzaPrice;
        StringBuffer sb = new StringBuffer();
        for (String s : ingredients) sb.append(s).append(", ");
        sb.delete(sb.length() - 2, sb.length());
        this.ingredients = sb.toString();
        //this.oldPrice = -1;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.pizzaName;
    }

    @Override
    public int getPrice() {
        return pizzaPrice;
    }

    @Override
    public String getBasketString() {
        return this.pizzaName;
    }

    public String getIngredients() {
        return ingredients;
    }
}
