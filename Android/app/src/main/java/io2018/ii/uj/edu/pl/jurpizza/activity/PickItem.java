package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PickBasketItemAdapter;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;

public class PickItem extends Activity {
    final int xxx = 3;

    ArrayList<BasketEntry> pickedItemsInBasket;
    ArrayList<BasketEntry> itemsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_pizza);
        listView = findViewById(R.id.pizza_selection_list);

        pickedItemsInBasket = new ArrayList<>();

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
                Toast.makeText(getBaseContext(), "Size: " + pickedItemsInBasket.size(), Toast.LENGTH_LONG).show();
                Intent intent;
                if (itemsList.get(position) instanceof Beverage) {
                    intent = new Intent(PickItem.this, DetailsBeverage.class);
                    intent.putExtra("beverage", itemsList.get(position));
                } else {
                    intent = new Intent(PickItem.this, DetailsPizza.class);
                    intent.putExtra("pizza", itemsList.get(position));
                }
                intent.putExtra("basket", pickedItemsInBasket);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Size: " + pickedItemsInBasket.size(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void configureBasketButton() {
        ImageButton basketButton = (ImageButton) findViewById(R.id.basket_image);
        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PickItem.this, BasketPreview.class));
            }
        });
    }

}
