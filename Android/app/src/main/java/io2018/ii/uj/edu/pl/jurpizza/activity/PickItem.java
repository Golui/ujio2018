package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PickBasketItemAdapter;
import io2018.ii.uj.edu.pl.jurpizza.io.impl.MockOfferGetter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class PickItem extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_pizza);

        ListView list = findViewById(R.id.pizza_selection_list);

        List<String> l = new ArrayList<String>(Arrays.asList("Kanapka", "Pomidor", "aaaaaaaaaaaaaa", "asdasdasdasdasdas", "ASDASDASDASDASDASDASDASDASD"));

        final ArrayList<BasketEntry> myItems = new ArrayList<>();
        myItems.addAll(new MockOfferGetter().downloadAvailableBeverages());
        myItems.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myItems.add(new Pizza( "Fungi", l, new BasketEntry.Variant("32 cm", 10000)));
        myItems.add(new Pizza( "Cap", l, new BasketEntry.Variant("32 cm", 8000)));
        myItems.add(new Pizza( "Meksicana", l, new BasketEntry.Variant("32 cm", 9000)));
        myItems.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myItems.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myItems.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myItems.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));


        PickBasketItemAdapter adapter = new PickBasketItemAdapter(getApplicationContext(), myItems);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "You check " + myItems.get(position).getName() + "";
                Toast.makeText(PickItem.this, message, Toast.LENGTH_LONG).show();
                Intent intent;
                if(myItems.get(position) instanceof Beverage) {
                    intent = new Intent(PickItem.this, DetailsBeverage.class);
                    intent.putExtra("beverage", myItems.get(position));
                } else {
                    intent = new Intent(PickItem.this, DetailsPizza.class);
                    intent.putExtra("pizza", myItems.get(position));
                }
                startActivity(intent);
            }
        });

    }

}
