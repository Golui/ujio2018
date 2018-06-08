package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.io.OrderManager;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOrderManager;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

import static io2018.ii.uj.edu.pl.jurpizza.model.Order.Status.NO_ORDER;

public class Payment extends Activity {

    Intent intent;
    TextView priceSum;
    Order customOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        priceSum = findViewById(R.id.payment_text);

        customOrder = new Order(NO_ORDER, (ArrayList<BasketEntry>) getIntent().getExtras().get("basket"), new Date());
        customOrder.setDeliveryAddress((DeliveryAddress) getIntent().getExtras().get("address"));

        priceSum.setText(Util.formatMoney(customOrder.getTotalPrice()));

        intent = new Intent();

        configureCashButton();
        configureWylewyButton();
    }

    private void configureCashButton() {
        Button button = (Button) findViewById(R.id.order2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getApplicationContext(), LaunchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                intent.setClass(getApplicationContext(), OrderPlaced.class);
                startActivity(intent);
            }
        });

        registerOrder();
    }

    private void configureWylewyButton() {
        Button button = (Button) findViewById(R.id.order3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Payment.this, PaymentPopUpWylewy48.class);

                registerOrder();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        intent.setClass(getApplicationContext(), LaunchActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        intent.setClass(getApplicationContext(), OrderPlaced.class);
                        startActivity(intent);
                    }
                }, 8000);

                startActivity(intent);

            }
        });
    }

    private void registerOrder() {
        this.customOrder.setStatus(Order.Status.PENDING);
        OrderManager om = new MockOrderManager();
        om.loadOrderHistory(getApplicationContext());
        om.getOrders().add(0, this.customOrder);
        om.saveOrders(getApplicationContext());

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                OrderManager om = new MockOrderManager();
                om.loadOrderHistory(getApplicationContext());
                om.getOrders().get(0).setStatus(Order.Status.CONFIRMED);
                om.saveOrders(getApplicationContext());
            }
        }, 30000);

        handler.postDelayed(new Runnable() {
            public void run() {
                OrderManager om = new MockOrderManager();
                om.loadOrderHistory(getApplicationContext());
                om.getOrders().get(0).setStatus(Order.Status.IN_DELIVERY);
                om.saveOrders(getApplicationContext());
            }
        }, 40000);
    }
}
