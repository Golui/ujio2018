package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PickBasketItemAdapter;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PreviewBasketAdapter;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class BasketPreview extends Activity {

    private ArrayList<BasketEntry> basket;
    ListView listView;
    TextView priceSum;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_preview);

        listView = findViewById(R.id.basket_preview_scroll);
        priceSum = findViewById(R.id.basket_preview_total_price_number);

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            basket = (ArrayList<BasketEntry>) getIntent().getSerializableExtra("basket");
        } else {
            Toast.makeText(getBaseContext(), "basket creation", Toast.LENGTH_LONG).show();
            basket = new ArrayList<>();
        }

        configureItemsList();
        generateSum();
        configureSummaryButton();
    }

    private void configureItemsList() {
        PreviewBasketAdapter adapter = new PreviewBasketAdapter(getApplicationContext(), basket);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Usunięto " + basket.get(position).getName(), Toast.LENGTH_LONG).show();
                basket.remove(position);
                listView.setAdapter(new PreviewBasketAdapter(getApplicationContext(), basket));
            }
        });
    }

    private void generateSum() {
        sum = 0;
        for (int i = 0; i < basket.size(); i++) {
            sum = sum + basket.get(i).getPrice();
        }
        priceSum.setText(Util.formatMoney(sum));
    }

    private void configureSummaryButton() {
        Button orderButton = (Button) findViewById(R.id.basket_preview_finalize);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sum != 0) {
                    Intent newIntent = null;

                    newIntent = new Intent(BasketPreview.this, Payment.class); //            !!!!!!!!!!!!!!!

                    //przekazuje dalej listę produktów ( KOSZYK )
                    newIntent.putExtra( "basket", (ArrayList)getIntent().getExtras().get("basket"));

                    startActivity(newIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Grzesiek, weź coś pierwsze kup...", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
