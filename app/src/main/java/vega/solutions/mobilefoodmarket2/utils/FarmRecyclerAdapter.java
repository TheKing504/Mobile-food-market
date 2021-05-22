package vega.solutions.mobilefoodmarket2.utils;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vega.solutions.mobilefoodmarket2.FarmActivity;
import vega.solutions.mobilefoodmarket2.R;
import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.object.Item;

public class FarmRecyclerAdapter extends RecyclerView.Adapter<FarmRecyclerAdapter.ViewHolder> {

    public static final String TAG = "FarmRecyclerAdapter";

    public List<Farm> list;
    public Context context;

    @Override
    public FarmRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farm_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Farm farm = list.get(position);

        holder.farmName.setText(farm.getName());
        holder.farmDescription.setText(farm.getDescription());

        for (int i = 0; i < farm.getRating(); i++) {
            holder.stars[i].setColorFilter(ContextCompat.getColor(context,
                    R.color.yellow));
        }

        Glide.with(context).load(farm.getPhoto()).centerCrop().into(holder.farmImg);

        holder.holderContainer.setOnClickListener(view -> {
            Intent intent = new Intent(context, FarmActivity.class);
            intent.putExtra("ID", farm.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        if (list == null) {
            return 0;
        }

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         TextView farmName;
         TextView farmDescription;
         TextView farmDistance;

         ImageView farmImg;

         ImageView star1;
         ImageView star2;
         ImageView star3;
         ImageView star4;
         ImageView star5;

         RelativeLayout holderContainer;

         ImageView[] stars = new ImageView[5];

        public ViewHolder(View v) {
            super(v);

            farmName = v.findViewById(R.id.farm_name);
            farmDescription = v.findViewById(R.id.farm_description);
            farmDistance = v.findViewById(R.id.farm_distance);
            farmImg = v.findViewById(R.id.farm_img);
            holderContainer = v.findViewById(R.id.holder);
            star1 = v.findViewById(R.id.ic_star1);
            star2 = v.findViewById(R.id.ic_star2);
            star3 = v.findViewById(R.id.ic_star3);
            star4 = v.findViewById(R.id.ic_star4);
            star5 = v.findViewById(R.id.ic_star5);

            stars[0] = star1;
            stars[1] = star2;
            stars[2] = star3;
            stars[3] = star4;
            stars[4] = star5;

        }
    }

}
