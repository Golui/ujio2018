package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.OfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockAddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOrderManager;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import org.osmdroid.config.Configuration;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOfferGetter;

public class LaunchActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("jurpizza-native");
//    }

    private OfferGetter orderGetter = new MockOfferGetter();
    private AddressManager addressManager = new MockAddressManager();
    private OrderManager orderManager = new MockOrderManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_main);


        configureOrderPizzaButton();
        configurateTrackButton();
    }

    private void configureOrderPizzaButton() {
        Button orderButton = (Button) findViewById(R.id.order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, PickPizza.class);
                intent.putExtra(PickPizza.PIZZA_LIST_INTENT, LaunchActivity.this.orderGetter);
                startActivity(intent);
            }
        });
    }

    private void configurateTrackButton() {
        Button trackButton = (Button) findViewById(R.id.track);
        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, TrackOrders.class);
                intent.putExtra(TrackOrders.PREVIOUS_ORDERS_INTENT, LaunchActivity.this.orderManager);
                startActivity(intent);
            }
        });
    }
}