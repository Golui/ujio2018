package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
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
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;

public class DetailsBeverage extends Activity {

    private Beverage customBeverege;
    private TextView name;
    private TextView price;
    private ArrayList<BasketEntry> basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_beverage);

        name = (TextView) findViewById(R.id.details_beverage_name);
        price = (TextView) findViewById(R.id.details_beverage_price);

        setFromIntent();
        configureContentSpinner();
        configureQuantitySpinner();
        configureOrderButton();
    }

    private void setFromIntent() {
        customBeverege = new Beverage((Beverage) getIntent().getExtras().get("beverage"));
        basket = (ArrayList) getIntent().getExtras().get("basket");

        name.setText(customBeverege.getName() + " (" + Util.formatMoney(customBeverege.getPrice()) + ")");
        price.setText(Util.formatMoney(customBeverege.getPrice()));
    }

    private void configureContentSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.details_beverage_spinner2);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.beverage_size, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String string = parent.getItemAtPosition(position).toString();
                    String priceString = string.substring(string.indexOf('+') + 2, string.indexOf(')'));
                    String sizeString = string.substring(0, string.indexOf(' '));

                    customBeverege.setSize(sizeString);
                    customBeverege.setAditionalPrice(Integer.parseInt(priceString) * 100);

                    Toast.makeText(getBaseContext(), "Nadmuchamy butelkę do " + sizeString, Toast.LENGTH_LONG).show();
                } else {
                    customBeverege.setSize("");
                    customBeverege.setAditionalPrice(0);
                }
                price.setText(Util.formatMoney(customBeverege.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configureQuantitySpinner() {
        Spinner quantitySpinner = (Spinner) findViewById(R.id.details_beverage_spinner1);
        ArrayAdapter sauceAdapter = ArrayAdapter.createFromResource(this, R.array.quantity, android.R.layout.simple_spinner_item);
        sauceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(sauceAdapter);

        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String string = parent.getItemAtPosition(position).toString();
                String quantityString = string.substring(0, string.indexOf(' '));
                customBeverege.setQuantity(Integer.parseInt(quantityString));
                if (position != 0) {
                    Toast.makeText(getBaseContext(), "Zapakujemy " + quantityString + " te same butelki", Toast.LENGTH_LONG).show();
                }
                price.setText(Util.formatMoney(customBeverege.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void configureOrderButton() {
        Button orderButton = (Button) findViewById(R.id.details_beverage_add);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Dodano " + customBeverege.getName() + " do koszyka";
                Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();

                Intent data = new Intent();
                basket.add(new Beverage(customBeverege));
                data.putExtra("basket", basket);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
