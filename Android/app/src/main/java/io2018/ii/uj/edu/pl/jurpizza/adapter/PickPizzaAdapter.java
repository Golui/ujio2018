package io2018.ii.uj.edu.pl.jurpizza.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io2018.ii.uj.edu.pl.jurpizza.R;
import io2018.ii.uj.edu.pl.jurpizza.Util;
import io2018.ii.uj.edu.pl.jurpizza.model.Pizza;


public class PickPizzaAdapter extends BaseAdapter {
    private Context mContext;
    private List<Pizza> mPizzaList;

    public PickPizzaAdapter(Context mContext, List<Pizza> mPizzaList) {
        this.mContext = mContext;
        this.mPizzaList = mPizzaList;
    }

    @Override
    public int getCount() {
        return mPizzaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPizzaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.pick_pizza_item, null );
        TextView name = (TextView) v.findViewById(R.id.pick_pizza_name);
        TextView ingredeints = (TextView) v.findViewById(R.id.pick_pizza_ingredients);
        TextView price = (TextView) v.findViewById(R.id.pick_pizza_price);

        name.setText(mPizzaList.get(position).getName());
        ingredeints.setText(mPizzaList.get(position).getIngredients());
        price.setText(Util.formatMoney(mPizzaList.get(position).getPrice()));

        v.setTag(mPizzaList.get(position).getId());

        return v;
    }

/*




    private static class ViewHolder {
        TextView name;
        TextView ingredeints;
        TextView price;
    }

    public PickPizzaAdapter(Context context, int textViewResourceId, ArrayList<Pizza> items) {
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
