package com.example.lab4_20201638.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20201638.R;
import com.example.lab4_20201638.model.TeamPosition;

import java.util.List;

public class PositionsAdapter extends RecyclerView.Adapter<PositionsAdapter.ViewHolder> {
    private final List<TeamPosition> positionsList;

    public PositionsAdapter(List<TeamPosition> positionsList) {
        this.positionsList = positionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla tu layout personalizado para los items
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_position, parent, false); // Asegúrate de que la ruta sea correcta
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeamPosition teamPosition = positionsList.get(position); // Obtener el objeto en la posición actual

        // Actualizar los campos del ViewHolder con los datos del equipo
        holder.positionText.setText(String.valueOf(teamPosition.getRank()));
        holder.teamNameText.setText(teamPosition.getTeamName());
        holder.recordText.setText(
                String.format("V: %d | E: %d | D: %d",
                        teamPosition.getWins(), teamPosition.getDraws(), teamPosition.getLosses())
        );
        holder.goalsText.setText(
                String.format("GF: %d | GC: %d | GD: %d",
                        teamPosition.getGoalsFor(), teamPosition.getGoalsAgainst(), teamPosition.getGoalDifference())
        );
    }

    @Override
    public int getItemCount() {
        return positionsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Declarar las vistas del layout personalizado
        TextView positionText;
        TextView teamNameText;
        TextView recordText;
        TextView goalsText;

        ViewHolder(View itemView) {
            super(itemView);
            // Enlazar las vistas con sus respectivos IDs en el layout item_position.xml
            positionText = itemView.findViewById(R.id.text_position);
            teamNameText = itemView.findViewById(R.id.text_team_name);
            recordText = itemView.findViewById(R.id.text_record);
            goalsText = itemView.findViewById(R.id.text_goals);
        }
    }
}
