package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class DetailsPizza extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_pizza);
        Pizza p = (Pizza)getIntent().getSerializableExtra("pizza");

        TextView name = (TextView) findViewById(R.id.details_pizza_name);
        //TextView ingredeints = (TextView) findViewById(R.id.details_ingrediens);
        //TextView price = (TextView) findViewById(R.id.pick_pizza_price);

        name.setText(p.getName());
        //ingredeints.setText(mPizzaList.get(position).getIngredients());
        //price.setText(Util.formatMoney(mPizzaList.get(position).getPrice()));

    }
}
