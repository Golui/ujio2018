package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;

import org.osmdroid.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.TrackOrdersAdapter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class TrackOrders extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.track_orders);

        ListView lv = findViewById(R.id.track_orders_list);
        List<Order> lo = new ArrayList<>();
        List<BasketEntry> be = new ArrayList<>();
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lo.add(new Order(Order.Status.COMPLETED, be, new Date()));
        lv.setAdapter(new TrackOrdersAdapter(lo, this));
        lv.addHeaderView(getLayoutInflater().inflate(R.layout.track_orders_header, null));
        //lv.set

    }
}
