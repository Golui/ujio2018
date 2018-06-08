package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.osmdroid.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockAddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOrderManager;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class LaunchActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("jurpizza-native");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_main);

        configureOrderButton();
        configureTrackButton();
        configureAddressButton();

        ImageView iv = findViewById(R.id.activity_main_logo);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressManager am = new MockAddressManager();
                am.loadAddresses(getApplicationContext());
                am.getAddresses().clear();
                am.saveAddresses(getApplicationContext());

                OrderManager om = new MockOrderManager();

                om.loadOrderHistory(getApplicationContext());
                om.getOrders().clear();
                om.getOrders().addAll(Arrays.asList(
                        new Order(Order.Status.PENDING, new ArrayList<BasketEntry>(), new Date()),
                        new Order(Order.Status.CONFIRMED, new ArrayList<BasketEntry>(), new Date()),
                        new Order(Order.Status.CANCELLED, new ArrayList<BasketEntry>(), new Date()),
                        new Order(Order.Status.IN_DELIVERY, new ArrayList<BasketEntry>(), new Date()),
                        new Order(Order.Status.COMPLETED, new ArrayList<BasketEntry>(), new Date())
                ));
                //for(int i = 0; i < om.getOrders().size(); i++) om.getOrders().get(i).setDeliveryAddress(adr);
                om.saveOrders(LaunchActivity.this.getApplicationContext());


                Toast.makeText(getBaseContext(), "Sekretny debugowy przycisk do mordowania aktywowany.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configureOrderButton() {
        Button orderButton = (Button) findViewById(R.id.order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, PickItem.class));
            }
        });
    }

    private void configureTrackButton() {
        Button trackButton = (Button) findViewById(R.id.track);
        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, TrackOrders.class));
            }
        });
    }

    private void configureAddressButton() {
        Button trackButton = (Button) findViewById(R.id.add_address);
        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchActivity.this, ManageAddresses.class));
            }
        });
    }
}