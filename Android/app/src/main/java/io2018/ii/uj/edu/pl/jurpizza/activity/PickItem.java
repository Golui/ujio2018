package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PickBasketItemAdapter;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;

public class PickItem extends Activity {
    private Intent basketIntent = new Intent();

    private ArrayList<BasketEntry> basket;
    private ArrayList<BasketEntry> itemsList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_pizza);
        listView = findViewById(R.id.pizza_selection_list);

        basketIntent = getIntent();
        basketIntent.putExtra("basket", new ArrayList<BasketEntry>());

        createItemsList();
        configureListView();
        configureBasketButton();
    }

    private void createItemsList() {
        itemsList = new ArrayList<>();
        itemsList.addAll(new MockOfferGetter().downloadAvailablePizzas());
        itemsList.addAll(new MockOfferGetter().downloadAvailableBeverages());
    }

    private void configureListView() {
        PickBasketItemAdapter adapter = new PickBasketItemAdapter(getApplicationContext(), itemsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(itemsList.get(position) instanceof Beverage) {
                    basketIntent.setClass(PickItem.this, DetailsBeverage.class);
                    basketIntent.putExtra("beverage", itemsList.get(position));
                } else {
                    basketIntent.setClass(PickItem.this, DetailsPizza.class);
                    basketIntent.putExtra("pizza", itemsList.get(position));
                }
                startActivityForResult(basketIntent,1);
            }
        });
    }

    private void configureBasketButton() {
        ImageButton basketButton = (ImageButton) findViewById(R.id.basket_image);
        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basketIntent.setClass(PickItem.this, BasketPreview.class);
                startActivityForResult(basketIntent,1);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bundle b = data.getExtras();
                basket = (ArrayList<BasketEntry>) b.get("basket");
                basketIntent.putExtra("basket", basket);
            }
        }
    }

}
