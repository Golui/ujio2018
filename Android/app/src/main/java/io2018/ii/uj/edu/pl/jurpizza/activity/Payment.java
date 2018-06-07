package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
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

        customOrder =  new Order(NO_ORDER, (ArrayList<BasketEntry>)getIntent().getExtras().get("basket"), new Date());

        priceSum.setText(Util.formatMoney(customOrder.getTotalPrice()));

        intent = new Intent();

        configureCashButton();
        configureWylewyButton();

    }

    private void configureCashButton() {


    }

    private void configureWylewyButton() {
        Button button = (Button)findViewById(R.id.order3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Payment.this, PaymentPopUpWylewy48.class);
                startActivity(intent);
            }
        });
    }
}
