package com.example.lab4_20201638.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PositionsAdapter extends RecyclerView.Adapter<PositionsAdapter.ViewHolder> {
    private final List<String> positionsList; // Cambiar por tu modelo de datos

    public PositionsAdapter(List<String> positionsList) {
        this.positionsList = positionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.positionText.setText(positionsList.get(position)); // Cambiar según el modelo
    }

    @Override
    public int getItemCount() {
        return positionsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView positionText;

        ViewHolder(View itemView) {
            super(itemView);
            positionText = itemView.findViewById(android.R.id.text1);
        }
    }
}
