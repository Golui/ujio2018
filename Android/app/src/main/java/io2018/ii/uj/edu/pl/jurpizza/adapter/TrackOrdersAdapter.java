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
import io2018.ii.uj.edu.pl.jurpizza.model.Order;

public class TrackOrdersAdapter extends ArrayAdapter<Order> implements View.OnClickListener {

    Context ctx;
    private DecimalFormat money = new DecimalFormat("0.00");
    private List<Order> data;

    public TrackOrdersAdapter(List<Order> model, @NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1, model);
        this.data = model;
        this.ctx = context;
        if (model.isEmpty()) {
            this.add(Order.empty());
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Order dataModel = getItem(position);

        ViewHolder viewHolder = new ViewHolder();
        View result;

        if (this.data.get(position).getStatus() == Order.Status.NO_ORDER) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.track_orders_item_empty, parent, false);
            }
        } else {
            if (convertView == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.track_orders_item, parent, false);
                viewHolder.name = convertView.findViewById(R.id.track_orders_name);
                viewHolder.price = convertView.findViewById(R.id.track_orders_price);

                result = convertView;
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result = convertView;
            }

            Order ord = data.get(position);
            viewHolder.name.setText(ord.getName());
            viewHolder.price.setText(Util.formatMoney(ord.getTotalPrice()));
        }


        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView price;
    }
}
