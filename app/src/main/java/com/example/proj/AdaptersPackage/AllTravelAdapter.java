package com.example.proj.AdaptersPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proj.ModelsPackage.TravelModel;
import com.example.proj.PagesPackage.DataAllTravelActivity;
import com.example.proj.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllTravelAdapter extends RecyclerView.Adapter<AllTravelAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView iv, addToFavorites;
        private LinearLayout linear1;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            iv = itemView.findViewById(R.id.iv);
            addToFavorites = itemView.findViewById(R.id.addToFavorites);
            linear1 = itemView.findViewById(R.id.linear1);
        }
    }

    private List<TravelModel> list_data;
    private Context context;
    private final LayoutInflater mInflater;

    public AllTravelAdapter(List<TravelModel> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_all_travel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TravelModel listData = list_data.get(position);
        holder.tvName.setText(listData.getLocationName());
        Picasso.get().load(listData.getImageUrl()).into(holder.iv);
        holder.addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.linear1.setOnClickListener(v -> {
            Intent intent = new Intent(mInflater.getContext(), DataAllTravelActivity.class);
            intent.putExtra(mInflater.getContext().getString(R.string.dataFirestore), listData);
            mInflater.getContext().startActivity(intent);
        });
    }

    public void setData(List<TravelModel> names) {
        list_data = names;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

}
