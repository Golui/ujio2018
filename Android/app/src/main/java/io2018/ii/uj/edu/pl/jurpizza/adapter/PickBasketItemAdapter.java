package io2018.ii.uj.edu.pl.jurpizza.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Beverage;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;


public class PickBasketItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<BasketEntry> mBasketEntryList;

    public PickBasketItemAdapter(Context mContext, List<BasketEntry> mBasketEntryList) {
        this.mContext = mContext;
        this.mBasketEntryList = mBasketEntryList;
    }

    @Override
    public int getCount() {
        return mBasketEntryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBasketEntryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    //instanceOf
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mBasketEntryList.get(position) instanceof Pizza) {

            convertView = View.inflate(mContext, R.layout.pick_pizza_item, null);
            TextView name = (TextView) convertView.findViewById(R.id.pick_pizza_name);
            TextView ingredeints = (TextView) convertView.findViewById(R.id.pick_pizza_ingredients);
            TextView price = (TextView) convertView.findViewById(R.id.pick_pizza_price);

            Pizza pizza = (Pizza) mBasketEntryList.get(position);

            name.setText(pizza.getName());
            ingredeints.setText(pizza.getIngredients());
            price.setText(Util.formatMoney(pizza.getPrice()));

        } else {
            convertView = View.inflate(mContext, R.layout.pick_beverage_item, null);
            TextView name = (TextView) convertView.findViewById(R.id.pick_beverage_name);
            TextView price = (TextView) convertView.findViewById(R.id.pick_beverage_price);

            Beverage beverage = (Beverage) mBasketEntryList.get(position);

            name.setText(beverage.getName());
            price.setText(Util.formatMoney(beverage.getPrice()));
        }

        return convertView;
    }
}
