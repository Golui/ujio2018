package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class DetailsPizza extends Activity {

    private Pizza customPizza;
    private TextView name;
    private TextView ingredeints;
    private TextView price;
    private ArrayList<BasketEntry> basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_pizza);

        name = (TextView) findViewById(R.id.details_pizza_name);
        ingredeints = (TextView) findViewById(R.id.details_pizza_ingredients);
        price = (TextView) findViewById(R.id.details_pizza_price);

        setFromIntent();
        configureSizeSpinner();
        configureSauceSpinner();
        configureOrderButton();
    }

    private void setFromIntent() {
        basket = (ArrayList<BasketEntry>)getIntent().getSerializableExtra("basket");

        customPizza = new Pizza((Pizza)getIntent().getSerializableExtra("pizza"));

        name.setText(customPizza.getName());
        ingredeints.setText(customPizza.getIngredients());
        price.setText(Util.formatMoney(customPizza.getPrice()));
    }

    private void configureSizeSpinner() {
        Spinner sizeSpinner = (Spinner) findViewById(R.id.details_pizza_spinner1);
        ArrayAdapter sizeAdapter = ArrayAdapter.createFromResource(this, R.array.pizza_size, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String string = parent.getItemAtPosition(position).toString();
                    String priceString = string.substring(string.indexOf('+') + 2, string.indexOf(')'));
                    String sizeString = string.substring(0, string.indexOf(' '));

                    customPizza.setSize(sizeString);
                    customPizza.setAditionalPrice(Integer.parseInt(priceString) * 100);

                    Toast.makeText(getBaseContext(), "Rozciągniemy placka do " + sizeString, Toast.LENGTH_LONG).show();
                } else {
                    customPizza.setSize("");
                    customPizza.setAditionalPrice(0);
                }
                price.setText(Util.formatMoney(customPizza.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configureSauceSpinner() {
        Spinner sauceSpinner = (Spinner) findViewById(R.id.details_pizza_spinner2);
        ArrayAdapter sauceAdapter = ArrayAdapter.createFromResource(this, R.array.sauces, android.R.layout.simple_spinner_item);
        sauceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sauceSpinner.setAdapter(sauceAdapter);

        sauceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customPizza.setSauce(parent.getItemAtPosition(position).toString());
                if (position != 0) {
                    Toast.makeText(getBaseContext(), "Dodamy smarowidło przypominające " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configureOrderButton() {
        Button orderButton = (Button) findViewById(R.id.details_pizza_add);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket.add(new Pizza(customPizza));
                String message = "Dodano " + customPizza.getName() + " do Twojego koszyka";
                Toast.makeText(DetailsPizza.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
