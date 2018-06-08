package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.osmdroid.config.Configuration;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.TrackOrdersAdapter;
import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOrderManager;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class TrackOrders extends Activity {

    public static final String PREVIOUS_ORDERS_INTENT = "orders";
    OrderManager om;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.track_orders);

        ListView lv = findViewById(R.id.track_orders_list);

        this.om = new MockOrderManager();
        this.om.loadOrderHistory(getApplicationContext());

        lv.setAdapter(new TrackOrdersAdapter(om.getOrders(), this));
        lv.addHeaderView(getLayoutInflater().inflate(R.layout.track_orders_header, null));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TrackOrders.this, DetailsOrder.class);
                Order o = TrackOrders.this.om.getOrders().get(position - 1);
                if (o.getStatus() == Order.Status.NO_ORDER) return;
                // Offset because header
                intent.putExtra(DetailsOrder.ORDER_INTENT, TrackOrders.this.om.getOrders().get(position - 1));
                startActivity(intent);
            }
        });

    }
}
