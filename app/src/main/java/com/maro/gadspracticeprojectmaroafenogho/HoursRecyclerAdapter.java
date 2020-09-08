package com.maro.gadspracticeprojectmaroafenogho;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HoursRecyclerAdapter extends RecyclerView.Adapter<HoursRecyclerAdapter.HoursViewHolder> {

    private List<Hours> hoursList;
    Context mContext;

    public static class HoursViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameText, country, hours;
        public HoursViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.badge);
            nameText = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
            hours = itemView.findViewById(R.id.learning_hours);
        }
    }

    public HoursRecyclerAdapter(Context context, List<Hours> hoursList) {
        this.hoursList = hoursList;
        this.mContext = context;
    }

    public void setHoursList (List<Hours> hours){
        this.hoursList = hours;
    }

    @NonNull
    @Override
    public HoursRecyclerAdapter.HoursViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hours_list_item, parent, false);
        return new HoursViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder holder, int position) {
        Hours hours = hoursList.get(position);
        Picasso.get().load(hours.getBadgeUrl()).into(holder.imageView);
        holder.nameText.setText(hours.getName());
        holder.country.setText(hours.getCountry());
        holder.hours.setText(hours.getHours() + " "  +  mContext.getString(R.string.learning_hours) + " ");
    }

    @Override
    public int getItemCount() {
        if (hoursList!=null) {
            return hoursList.size();
        }
        return 0;
        }


}
