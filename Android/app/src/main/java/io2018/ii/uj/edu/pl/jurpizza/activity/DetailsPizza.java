package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class DetailsPizza extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_pizza);
        Pizza intentPizza = (Pizza)getIntent().getSerializableExtra("pizza");

        TextView name = (TextView) findViewById(R.id.details_pizza_name);
        TextView ingredeints = (TextView) findViewById(R.id.details_pizza_ingredients);
        //TextView price = (TextView) findViewById(R.id.pick_pizza_price);

        name.setText(intentPizza.getName());
        ingredeints.setText(intentPizza.getIngredients());
        //price.setText(Util.formatMoney(mPizzaList.get(position).getPrice()));

        Button orderButton = (Button) findViewById(R.id.details_pizza_add);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Pizza intentPizzaOnClick = (Pizza)getIntent().getSerializableExtra("pizza");
                String message = "You add " + intentPizzaOnClick.getName() + " to your basket";
                Toast.makeText(DetailsPizza.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }
}
