package io2018.ii.uj.edu.pl.jurpizza.io;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public interface OrderManager extends Serializable {

    void loadOrderHistory(Context ctx);

    void saveOrders(Context ctx);

    List<Order> getOrders();
}
