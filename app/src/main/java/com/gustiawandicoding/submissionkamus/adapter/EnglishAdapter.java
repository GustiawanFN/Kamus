package com.gustiawandicoding.submissionkamus.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gustiawandicoding.submissionkamus.CustomOnItemClickListener;
import com.gustiawandicoding.submissionkamus.DetailActivity;
import com.gustiawandicoding.submissionkamus.model.EngModel;

import java.util.ArrayList;

import static com.gustiawandicoding.submissionkamus.DetailActivity.EXTRA_DETAIL;
import static com.gustiawandicoding.submissionkamus.DetailActivity.EXTRA_WORD;

/**
 * Created by Gustiawan on 9/28/2018.
 */

public class EnglishAdapter extends RecyclerView.Adapter<EnglishAdapter.EnglishHolder>  {
    private ArrayList<EngModel> kamusEngModels = new ArrayList<>();
    private Activity activity;

    public EnglishAdapter(Activity activity) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
    }

    @Override
    public EnglishHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new EnglishHolder(view);
    }

    public void addItem(ArrayList<EngModel> engModels){
        this.kamusEngModels = engModels;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(EnglishHolder holder, int position) {
        holder.tvWord.setText(kamusEngModels.get(position).getWord());
        holder.tvWord.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra(EXTRA_WORD, kamusEngModels.get(position).getWord());
                intent.putExtra(EXTRA_DETAIL, kamusEngModels.get(position).getDescription());
                activity.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return kamusEngModels.size();
    }

     class EnglishHolder extends RecyclerView.ViewHolder {
        private TextView tvWord;
        EnglishHolder(View view) {
            super(view);
            tvWord = itemView.findViewById(android.R.id.text1);
        }
    }
}
