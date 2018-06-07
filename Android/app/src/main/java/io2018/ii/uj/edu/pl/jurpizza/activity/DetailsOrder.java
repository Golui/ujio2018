package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class DetailsOrder extends Activity {

    public static final String ORDER_INTENT = "order";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_order_status);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        Order o = (Order) getIntent().getSerializableExtra(ORDER_INTENT);

        TextView status = findViewById(R.id.details_order_status);
        TextView timeEstimate = findViewById(R.id.details_order_time_estimate);
        ImageView hourglass = findViewById(R.id.details_order_hourglass);

        MapView map = (MapView) findViewById(R.id.details_order_map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(18);
        GeoPoint startPoint = new GeoPoint(50.02894, 19.90750);
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

        status.setText(getResources().getStringArray(R.array.statuses)[o.getStatus().ordinal()]);
        // Set estimate to be random, beacuse that's jsut life™
        timeEstimate.setText(Util.formatTime(((int) (Math.random() * 12)) * 5));
        hourglass.setImageResource(o.getStatus().getResource());
    }
}
