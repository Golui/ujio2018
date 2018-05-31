package io2018.ii.uj.edu.pl.jurpizza.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order {

    public static final DateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    Status status;
    List<BasketEntry> products;
    // TODO Actual address/geo coordinates
    String address;
    int totalPrice;
    Date date;

    private Order() {
    }

    public Order(Status s, List<BasketEntry> be, Date d) {
        this.status = s;
        this.products = be;
        this.date = d;

        if (!be.isEmpty()) for (BasketEntry b : be)
            this.totalPrice += b.getPrice();
    }

    public String getName() {
        return FORMAT.format(date);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public static Order empty() {
        Order o = new Order();

        o.status = Status.NO_ORDER;
        return o;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        NO_ORDER,
        PENDING,
        CONFIRMED,
        CANCELLED,
        IN_DELIVERY,
        COMPLETED
    }
}
