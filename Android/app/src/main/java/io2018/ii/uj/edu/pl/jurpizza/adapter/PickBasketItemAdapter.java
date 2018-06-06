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

        if(mBasketEntryList.get(position) instanceof Pizza) {

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

/*




    private static class ViewHolder {
        TextView name;
        TextView ingredeints;
        TextView price;
    }

    public PickBasketItemAdapter(Context context, int textViewResourceId, ArrayList<Pizza> items) {
        super(context, textViewResourceId, items);
        this.addAll(items);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.pick_pizza_item, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView.findViewById(R.id.pick_pizza_name);
            viewHolder.ingredeints = (TextView) convertView.findViewById(R.id.pick_pizza_ingredients);
            viewHolder.price = (TextView) convertView.findViewById(R.id.pick_pizza_price);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pizza item = getItem(position);
        if (item!= null) {
            Pizza pm = data.get(position);
            viewHolder.name.setText(pm.getName());
            viewHolder.ingredeints.setText(pm.getIngredients());
            viewHolder.price.setText(Util.formatMoney(pm.getPrice()));
        }

        return convertView;
    }
*/

}
