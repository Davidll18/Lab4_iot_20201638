package com.example.lab4_20201638.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20201638.R;
import com.example.lab4_20201638.model.League;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeagueViewHolder> {
    private List<League> leaguesList;
    private Context context;

    public LeaguesAdapter(List<League> leaguesList, Context context) {
        this.leaguesList = leaguesList;
        this.context = context;
    }


    @NonNull
    @Override
    public LeagueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_league, parent, false);
        return new LeagueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeagueViewHolder holder, int position) {
        League league = leaguesList.get(position);
        holder.textViewLeagueName.setText(league.getStrLeague());
        holder.textViewCountry.setText(league.getIdLeague());
    }

    @Override
    public int getItemCount() {
        return leaguesList.size();
    }

    public class LeagueViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLeagueName, textViewCountry;

        public LeagueViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLeagueName = itemView.findViewById(R.id.league_name);
            textViewCountry = itemView.findViewById(R.id.league_country);
        }
    }
}

