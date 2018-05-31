package io2018.ii.uj.edu.pl.jurpizza.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.BasketEntry;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;

public class PreviewBasketAdapter extends ArrayAdapter<Pizza> implements View.OnClickListener {

    Context ctx;
    private List<Pizza> data;

    public PreviewBasketAdapter(List<Pizza> model, @NonNull Context context) {
        super(context, R.layout.pick_pizza_item, model);
        this.data = model;
        this.ctx = context;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        BasketEntry dataModel = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        View result;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.basket_preview_item, parent, false);
            viewHolder.name = convertView.findViewById(R.id.preview_basket_item_name);
            viewHolder.price = convertView.findViewById(R.id.preview_basket_item_price);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        BasketEntry pm = data.get(position);
        viewHolder.name.setText(pm.getBasketString());
        viewHolder.price.setText(Util.formatMoney(pm.getPrice()));
        // Return the completed view to render on screen
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView price;
    }
}
