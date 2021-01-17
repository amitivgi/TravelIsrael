package com.example.proj.AdaptersPackage;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proj.R;

public class AllTravelAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public ImageView iv;
    public LinearLayout linear1;

    public AllTravelAdapterViewHolder(View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tvName);
        iv = itemView.findViewById(R.id.iv);
        linear1 = itemView.findViewById(R.id.linear1);
    }

}
