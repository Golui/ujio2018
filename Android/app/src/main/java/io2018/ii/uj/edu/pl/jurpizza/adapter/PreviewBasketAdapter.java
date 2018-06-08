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


public class PreviewBasketAdapter extends BaseAdapter {
    private Context mContext;
    private List<BasketEntry> mBasketEntryList;

    public PreviewBasketAdapter(Context mContext, List<BasketEntry> mBasketEntryList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = View.inflate(mContext, R.layout.basket_preview_item, null);
        TextView name = (TextView) convertView.findViewById(R.id.preview_basket_item_name);
        TextView price = (TextView) convertView.findViewById(R.id.preview_basket_item_price);

        BasketEntry basketEntry = (BasketEntry) mBasketEntryList.get(position);

        name.setText(basketEntry.getName());
        price.setText(Util.formatMoney(basketEntry.getPrice()));

        return convertView;
    }
}


