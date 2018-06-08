package io2018.ii.uj.edu.pl.jurpizza.io.impl;

import android.content.Context;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class MockOrderManager implements OrderManager {

    List<Order> lo;

    @Override
    public void loadOrderHistory(Context ctx) {

        try {
            ObjectInputStream ois = new ObjectInputStream(ctx.openFileInput("orders"));
            this.lo = (List<Order>) ois.readObject();
        } catch (java.io.IOException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (this.lo == null) {
            this.lo = new ArrayList<>();
        }
    }

    @Override
    public void saveOrders(Context ctx) {
        try {
            ObjectOutputStream fos = new ObjectOutputStream(ctx.openFileOutput("orders", Context.MODE_PRIVATE));
            fos.writeObject(this.lo);
            fos.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrders() {
        if (this.lo == null) throw new IllegalStateException("Load orders first");
        return this.lo;
    }

    public void sort() {
        Collections.sort(this.getOrders(), new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }
}
