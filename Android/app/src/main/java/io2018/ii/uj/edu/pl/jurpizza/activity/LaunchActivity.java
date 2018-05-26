package io2018.ii.uj.edu.pl.jurpizza.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PickPizzaAdapter;
import io2018.ii.uj.edu.pl.jurpizza.adapter.PreviewBasketAdapter;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class LaunchActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("jurpizza-native");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.basket_preview);


        List p = Arrays.asList(
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Filmowa", 1100, Arrays.asList("Pomidory", "Ser", "Statysta", "Klisza", "Szkło")),
                new Pizza("Marysia", 1010, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1001, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser")),
                new Pizza("Marysia", 1000, Arrays.asList("Pomidory", "Ser"))
                );

        PreviewBasketAdapter ppa = new PreviewBasketAdapter(p, this.getApplicationContext());

        ListView lv = ((ListView)findViewById(R.id.basket_preview_scroll));
        lv.setAdapter(ppa);


        //setContentView(R.layout.details_pizza);

        //getActionBar().hide();
//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
