package vega.solutions.mobilefoodmarket2.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vega.solutions.mobilefoodmarket2.FarmActivity;
import vega.solutions.mobilefoodmarket2.R;
import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.object.Item;

public class SearchtemsRecyclerAdapter extends RecyclerView.Adapter<SearchtemsRecyclerAdapter.ViewHolder> {

    public static final String TAG = "SearchtemsRecyclerAdapter";

    public List<Farm> list;
    public Context context;

    @Override
    public SearchtemsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Farm farm = list.get(position);

        holder.farmName.setText(farm.getName());
        holder.farmDescription.setText(farm.getDescription());

        holder.farmContainer.setOnClickListener(view -> {
            Intent intent = new Intent(context, FarmActivity.class);
            intent.putExtra("ID", farm.getId());
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {

        if (list == null)
            return 0;

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView farmName;
        TextView farmDescription;

        RelativeLayout farmContainer;

        public ViewHolder(View v) {
            super(v);
            farmName = v.findViewById(R.id.farm_name);
            farmDescription = v.findViewById(R.id.farm_description);

            farmContainer = v.findViewById(R.id.item_container);
        }
    }

}
