package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PickPizzaAdapter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class PickPizza extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_pizza);

        ListView list = findViewById(R.id.pizza_selection_list);

        List<String> l = new ArrayList<String>(Arrays.asList("Kanapka", "Pomidor", "aaaaaaaaaaaaaa", "asdasdasdasdasdas", "ASDASDASDASDASDASDASDASDASD"));

        final ArrayList<Pizza> myPizzas = new ArrayList<>();
        myPizzas.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myPizzas.add(new Pizza( "Fungi", l, new BasketEntry.Variant("32 cm", 10000)));
        myPizzas.add(new Pizza( "Cap", l, new BasketEntry.Variant("32 cm", 8000)));
        myPizzas.add(new Pizza( "meksicana", l, new BasketEntry.Variant("32 cm", 9000)));
        myPizzas.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myPizzas.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myPizzas.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));
        myPizzas.add(new Pizza( "Martgharita", l, new BasketEntry.Variant("32 cm", 8000)));


        PickPizzaAdapter adapter = new PickPizzaAdapter(getApplicationContext(), myPizzas);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView textView = (TextView) view.findViewById(R.id.pick_pizza_name);
                //String message = "You add " + textView.getText().toString() + " to your basket_raster";
                //Toast.makeText(PickPizza.this, message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(PickPizza.this, DetailsPizza.class);
                intent.putExtra("pizza", myPizzas.get(position));
                startActivity(intent);
            }
        });

    }

}
