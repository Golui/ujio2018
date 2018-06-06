package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io2018.ii.uj.edu.pl.jurpizza.exception.AddressFormatException;
import io2018.ii.uj.edu.pl.jurpizza.model.Address;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import org.osmdroid.config.Configuration;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.io.AddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockAddressManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOfferGetter;

public class LaunchActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("jurpizza-native");
//    }

    public static final String PIZZA_LIST_INTENT = "pizzas";
    public static final String PREVIOUS_ORDERS_INTENT = "orders";

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
                intent.putExtra(PIZZA_LIST_INTENT, new MockOfferGetter());
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
                // intent.putExtra(PREVIOUS_ORDERS_INTENT, null);
                startActivity(intent);
            }
        });
    }
}