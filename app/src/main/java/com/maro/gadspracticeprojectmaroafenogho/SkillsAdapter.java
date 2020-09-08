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

public class SkillsAdapter extends  RecyclerView.Adapter<SkillsAdapter.SkillsViewHolder>{
    Context mContext;
    private List<SkillIq> skillIqList;

    public static class SkillsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameText, country, score;
        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.skill_badge);
            nameText = itemView.findViewById(R.id.skill_name);
            country = itemView.findViewById(R.id.skill_country);
            score = itemView.findViewById(R.id.score);
        }
    }


    public SkillsAdapter(Context context, List<SkillIq> skillIqList) {
        this.skillIqList = skillIqList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_skill_iq, parent, false);
        return new SkillsViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        SkillIq skillIq = skillIqList.get(position);
        holder.nameText.setText(skillIq.getName());
        holder.country.setText(skillIq.getCountry());
        holder.score.setText(skillIq.getScore() + " " + mContext.getString(R.string.score) + " ");
        Picasso.get().load(skillIq.getBadgeUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (skillIqList!=null) {
            return skillIqList.size();
        }
        return 0;
    }

    public void setSkillIqList (List<SkillIq> skillIqs){
        this.skillIqList = skillIqs;
    }
}
