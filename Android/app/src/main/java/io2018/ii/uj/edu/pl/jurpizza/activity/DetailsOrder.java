package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Date;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOrderManager;
import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class DetailsOrder extends Activity implements View.OnClickListener {

    public static final String ORDER_INTENT = "order";

    Order order;
    ImageView hourglass;
    TextView status;
    OrderManager om = new MockOrderManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_order_status);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        om.loadOrderHistory(getApplicationContext());
        order = (Order) om.getOrders().get(getIntent().getIntExtra(ORDER_INTENT, -1));

        status = findViewById(R.id.details_order_status);
        TextView timeEstimate = findViewById(R.id.details_order_time_estimate);
        hourglass = findViewById(R.id.details_order_hourglass);

        MapView map = (MapView) findViewById(R.id.details_order_map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(18);
        GeoPoint startPoint = new GeoPoint(50.0250927, 19.9030436);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Lokal JurPizza");
        map.getOverlays().add(startMarker);

        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Lokal JurPizza");
        map.getOverlays().add(startMarker);

        mapController.setCenter(startPoint);
        map.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ScrollView sv = findViewById(R.id.details_order_main_scroll);
                switch (event.getAction()) {
                    //case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        sv.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        sv.requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        return true;
                }
                return v.onTouchEvent(event);
            }
        });

        status.setText(getResources().getStringArray(R.array.statuses)[order.getStatus().ordinal()]);

        if (order.getStatus() == Order.Status.IN_DELIVERY) {
            int time = (int) (order.getStartTime() / 60000000) / 600 + 60 - (int) (System.nanoTime() / 60000000) / 600;
            if (time < 0) {
                time = 0;
            }
            timeEstimate.setText(Util.formatTime(time));

        } else {
            timeEstimate.setText("N/A");
        }


        hourglass.setImageResource(order.getStatus().getResource());

        Button b = findViewById(R.id.details_order_cancel);
        b.setOnClickListener(this);

        TextView tv = findViewById(R.id.details_order_address);
        tv.setText("Adres");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsOrder.this, DetailsAddress.class);
                // Offset because header
                intent.putExtra(DetailsAddress.ADDRESS_INTENT, DetailsOrder.this.order.getAddress());
                intent.putExtra("DELETE", true);
                startActivity(intent);
            }
        });

        tv = findViewById(R.id.details_order_basket);
        tv.setText("Koszyk");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(DetailsOrder.this, BasketPreview.class);
                i.putExtra("basket", DetailsOrder.this.order.getBasketCopy());
                i.putExtra("DELETE", true);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (this.order.getStatus() == Order.Status.IN_DELIVERY || this.order.getStatus() == Order.Status.COMPLETED) {
            Toast.makeText(getBaseContext(), "Tego zamówienia nie da się anulować!", Toast.LENGTH_LONG).show();
        } else {
            om.loadOrderHistory(getApplicationContext());
            this.order.setStatus(Order.Status.CANCELLED);
            om.getOrders().get(DetailsOrder.this.getIntent().getIntExtra(ORDER_INTENT, -1)).setStatus(Order.Status.CANCELLED);
            om.saveOrders(getApplicationContext());

            status.setText(getResources().getStringArray(R.array.statuses)[order.getStatus().ordinal()]);
            hourglass.setImageResource(order.getStatus().getResource());

            Toast.makeText(getBaseContext(), "Pieniądze zostaną zwrócone w ciągu 24 godzin.", Toast.LENGTH_LONG).show();
        }

    }
}
