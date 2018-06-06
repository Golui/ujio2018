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
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class PickPizza extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pick_pizza);

        ListView list = findViewById(R.id.pizza_selection_list);

        List<String> l = new ArrayList<String>(Arrays.asList("Kanapka", "Pomidor"));

        ArrayList<Pizza> myPizzas = new ArrayList<>();
        myPizzas.add(new Pizza(0, "Martgharita", 20, l));
        myPizzas.add(new Pizza(1, "Martgharita", 20, l));
        myPizzas.add(new Pizza(2, "Martgharita", 20, l));
        myPizzas.add(new Pizza(3, "Martgharita", 20, l));

        PickPizzaAdapter adapter = new PickPizzaAdapter(getApplicationContext(), myPizzas);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.pick_pizza_name);
                String message = "You clicked # " + position
                        + ", which is string: " + textView.getText().toString();
                Toast.makeText(PickPizza.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

}