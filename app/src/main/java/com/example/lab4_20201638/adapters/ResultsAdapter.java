package com.example.lab4_20201638.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    private final List<String> resultsList; // Cambiar por tu modelo de datos

    public ResultsAdapter(List<String> resultsList) {
        this.resultsList = resultsList;
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
        holder.resultText.setText(resultsList.get(position)); // Cambiar según el modelo
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView resultText;

        ViewHolder(View itemView) {
            super(itemView);
            resultText = itemView.findViewById(android.R.id.text1);
        }
    }
}

