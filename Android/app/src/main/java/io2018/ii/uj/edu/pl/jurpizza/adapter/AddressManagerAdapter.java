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
import io2018.ii.uj.edu.pl.jurpizza.model.DeliveryAddress;

public class AddressManagerAdapter extends ArrayAdapter<DeliveryAddress> implements View.OnClickListener {

    Context ctx;
    private DecimalFormat money = new DecimalFormat("0.00");
    private List<DeliveryAddress> data;

    public AddressManagerAdapter(List<DeliveryAddress> model, @NonNull Context context) {
        super(context, android.R.layout.simple_list_item_1, model);
        this.data = model;
        this.ctx = context;
        if (model.isEmpty()) {
            this.add(null);
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
        DeliveryAddress dataModel = getItem(position);

        ViewHolder viewHolder = new ViewHolder();

        if (dataModel == null) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.address_manager_item_empty, parent, false);
            }
        } else {
            if (convertView == null || convertView.findViewById(R.id.address_manager_name) == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.address_manager_item, parent, false);
                viewHolder.name = convertView.findViewById(R.id.address_manager_name);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(dataModel.getIdentifier());
        }


        return convertView;
    }

    private static class ViewHolder {
        TextView name;
    }
}
