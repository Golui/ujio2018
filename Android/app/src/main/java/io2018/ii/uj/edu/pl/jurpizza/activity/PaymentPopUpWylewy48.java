package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOrderManager;

public class PaymentPopUpWylewy48 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wylewy48);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        getWindow().setLayout((int) (displayMetrics.widthPixels), (int) (displayMetrics.heightPixels));

        OrderManager om = new MockOrderManager();
        om.loadOrderHistory(getApplicationContext());
        //HACK Investingate why it duplicates
        om.getOrders().remove(1);
        om.saveOrders(getApplicationContext());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 8000);
    }
}
