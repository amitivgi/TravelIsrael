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

import java.util.ArrayList;
import java.util.List;

public class AllTravelAdapter extends RecyclerView.Adapter<AllTravelAdapterViewHolder> {

    private final ArrayList<TravelModel> list_data;
    private final LayoutInflater mInflater;

    public AllTravelAdapter(ArrayList<TravelModel> list_data, Context context) {
        this.list_data = list_data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AllTravelAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_all_travel, parent, false);
        return new AllTravelAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTravelAdapterViewHolder holder, int position) {
        TravelModel listData = list_data.get(position);
        holder.tvName.setText(listData.getLocationName());
        Picasso.get().load(listData.getImageUrl()).into(holder.iv);

        holder.linear1.setOnClickListener(v -> {
            Intent intent = new Intent(mInflater.getContext(), DataAllTravelActivity.class);
            intent.putExtra(mInflater.getContext().getString(R.string.dataFirestore), listData);
            mInflater.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

}
