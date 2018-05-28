package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import io2018.ii.uj.edu.pl.jurpizza.R;

public class OrderStatus extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        //setContentView(R.layout.activity_main);
        setContentView(R.layout.details_order);

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
        //setContentView(R.layout.details_pizza);

        //getActionBar().hide();
//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(aR.id.sample_text);
//        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
