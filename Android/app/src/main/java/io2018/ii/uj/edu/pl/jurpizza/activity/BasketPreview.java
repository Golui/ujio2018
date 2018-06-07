package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_preview);

        listView = findViewById(R.id.basket_preview_scroll);
        priceSum = findViewById(R.id.basket_preview_total_price_number);

        basket = new ArrayList<>();
        basket.addAll(new MockOfferGetter().downloadAvailablePizzas());
        //(ArrayList<BasketEntry>) getIntent().getSerializableExtra("basket");

        configureItemsList();
        generateSum();
    }

    private void generateSum() {
        int sum = 0;
        for (int i = 0; i < basket.size(); i++) {
            sum = sum + basket.get(i).getPrice();
        }
        priceSum.setText(Util.formatMoney(sum));
    }

    private void configureItemsList() {
        PreviewBasketAdapter adapter = new PreviewBasketAdapter(getApplicationContext(), basket);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
