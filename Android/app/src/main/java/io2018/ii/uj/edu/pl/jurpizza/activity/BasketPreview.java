package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PreviewBasketAdapter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;

public class BasketPreview extends Activity {

    ListView listView;
    TextView priceSum;
    int sum;
    private ArrayList<BasketEntry> basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basket_preview);

        listView = findViewById(R.id.basket_preview_scroll);
        priceSum = findViewById(R.id.basket_preview_total_price_number);

        Intent receivedIntent = getIntent();

        basket = (ArrayList) getIntent().getExtras().get("basket");

        configureItemsList();
        generateSum();
        configureSummaryButton();
    }

    private void configureItemsList() {
        PreviewBasketAdapter adapter = new PreviewBasketAdapter(getApplicationContext(), basket);
        listView.setAdapter(adapter);

        if (!getIntent().getBooleanExtra("DELETE", false)) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(), "Usunięto " + basket.get(position).getName(), Toast.LENGTH_LONG).show();
                    Intent data = new Intent();
                    basket.remove(position);
                    data.putExtra("basket", basket);
                    setResult(RESULT_OK, data);
                    listView.setAdapter(new PreviewBasketAdapter(getApplicationContext(), basket));
                    generateSum();
                }
            });
        }
    }

    private void generateSum() {
        sum = 0;
        for (int i = 0; i < basket.size(); i++) {
            sum = sum + basket.get(i).getPrice();
        }
        priceSum.setText(Util.formatMoney(sum));
    }

    private void configureSummaryButton() {
        Button orderButton = (Button) findViewById(R.id.basket_preview_finalize);
        if (getIntent().getBooleanExtra("DELETE", false)) {
            orderButton.setVisibility(View.INVISIBLE);
        } else {
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sum != 0) {
                        Intent newIntent = null;

                        newIntent = new Intent(BasketPreview.this, ManageAddresses.class);

                        newIntent.putExtra("basket", (ArrayList<BasketEntry>) getIntent().getExtras().get("basket"));
                        newIntent.putExtra("isOrdering", true);

                        startActivity(newIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Grzesiek, weź coś pierwsze kup...", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
