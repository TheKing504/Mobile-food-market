package vega.solutions.mobilefoodmarket2.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vega.solutions.mobilefoodmarket2.R;
import vega.solutions.mobilefoodmarket2.object.Item;

public class FarmItemsRecyclerAdapter extends RecyclerView.Adapter<FarmItemsRecyclerAdapter.ViewHolder> {

    public static final String TAG = "FarmItemsRecyclerAdapter";

    public List<Item> list;

    public FarmItemsRecyclerAdapter(List<Item> list) {
        this.list = list;
    }

    @Override
    public FarmItemsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item item = list.get(position);

        if (item.getId() == -2) {
            holder.tvCategory.setVisibility(View.VISIBLE);
            holder.itemContainer.setVisibility(View.GONE);

            holder.tvCategory.setText(item.getName());
        } else {
            holder.tvCategory.setVisibility(View.GONE);
            holder.itemContainer.setVisibility(View.VISIBLE);

            holder.tvItemName.setText(" • " + item.getName());
            holder.tvPrice.setText(item.getPrice() + "€ / " + item.getUnit());
        }
    }

    @Override
    public int getItemCount() {

        if (list == null)
            return 0;

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvItemName;
        TextView tvPrice;

        RelativeLayout itemContainer;

        public ViewHolder(View v) {
            super(v);
            tvCategory = v.findViewById(R.id.category);
            tvItemName = v.findViewById(R.id.item_name);
            tvPrice = v.findViewById(R.id.item_price);

            itemContainer = v.findViewById(R.id.item_container);
        }
    }

}
