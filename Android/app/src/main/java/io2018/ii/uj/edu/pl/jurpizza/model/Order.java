package io2018.ii.uj.edu.pl.jurpizza.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;

public class Order implements Serializable {

    public static final DateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    Status status;
    List<BasketEntry> products;
    // TODO Actual deliveryAddress/geo coordinates
    DeliveryAddress deliveryAddress;
    int totalPrice;
    Date date;

    private Order() {
    }

    public Order(Status status, ArrayList<BasketEntry> basketEntryList, Date date) {
        this.status = status;
        this.products = basketEntryList;
        this.date = date;
        this.totalPrice = 0;
        for (int i = 0; i < basketEntryList.size(); i++) {
            totalPrice = totalPrice + basketEntryList.get(i).getPrice();
        }
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

    public Date getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        NO_ORDER(R.mipmap.hourglass),
        PENDING(R.mipmap.pending),
        CONFIRMED(R.mipmap.confirmed),
        CANCELLED(R.mipmap.cancelled),
        IN_DELIVERY(R.mipmap.indelivery),
        COMPLETED(R.mipmap.confirmed);

        int resource;

        Status(int res) {
            this.resource = res;
        }

        public int getResource() {
            return resource;
        }
    }
}
