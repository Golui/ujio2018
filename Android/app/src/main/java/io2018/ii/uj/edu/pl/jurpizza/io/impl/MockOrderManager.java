package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class MockOrderManager implements OrderManager {

    ArrayList<Order> lo;

    @Override
    public void loadOrderHistory(Context ctx) {

        ArrayList<BasketEntry> be = new ArrayList<>();
        this.lo.add(new Order(Order.Status.COMPLETED, be, new Date(0)));
        this.lo.add(new Order(Order.Status.CANCELLED, be, new Date(7)));
        this.lo.add(new Order(Order.Status.CONFIRMED, be, new Date(2046124312)));
        this.lo.add(new Order(Order.Status.PENDING, be, new Date(33322233)));

        Collections.sort(this.lo, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return -o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    @Override
    public void saveOrders(Context ctx) {

    }

    @Override
    public List<Order> getOrders() {
        if (this.lo == null) throw new IllegalStateException("Load orders first");
        return this.lo;
    }
}
