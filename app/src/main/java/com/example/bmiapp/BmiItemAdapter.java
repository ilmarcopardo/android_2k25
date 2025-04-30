package com.example.bmiapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BmiItemAdapter extends RecyclerView.Adapter<BmiItemAdapter.BmiViewHolder> {
    private List<BmiCategory> bmiList;
    private List<BmiSuggestion> suggestionList;
    LayoutInflater inflater;
    OnBmiClickListener listener;

    public BmiItemAdapter(Context context, List<BmiCategory> bmiList, List<BmiSuggestion> suggestionList, OnBmiClickListener onBmiClickListener){
        this.bmiList=bmiList;
        this.suggestionList = suggestionList;
        this.listener = onBmiClickListener;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BmiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.bmi_item, parent, false);
        return new BmiViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BmiViewHolder holder, int position) {
        BmiCategory item = bmiList.get(position);
        holder.textCategoria.setText(item.getCategoria());
        holder.textRange.setText(item.getRange());
        holder.imagePerson.setImageResource(item.getImmagineResId());
    }

    @Override
    public int getItemCount() {
        return bmiList.size();
    }

    public class BmiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textCategoria;
        TextView textRange;
        ImageView imagePerson;
        BmiItemAdapter adapter;

        public BmiViewHolder(@NonNull View itemView, BmiItemAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            textCategoria = itemView.findViewById(R.id.categoria_item);
            textRange = itemView.findViewById(R.id.range_item);
            imagePerson = itemView.findViewById(R.id.image_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){
                listener.onBmiClick(adapter.suggestionList.get(position));
            }
        }
    }
}
