package com.example.lab4_20201638;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab4_20201638.adapters.PositionsAdapter;
import com.example.lab4_20201638.model.TeamPosition;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PositionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PositionsFragment extends Fragment {

    private RecyclerView recyclerView;
    private PositionsAdapter adapter;
    private EditText leagueIdInput, seasonInput;
    private Button searchButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PositionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PositionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PositionsFragment newInstance(String param1, String param2) {
        PositionsFragment fragment = new PositionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_positions, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_positions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        leagueIdInput = view.findViewById(R.id.league_id);
        seasonInput = view.findViewById(R.id.season);
        searchButton = view.findViewById(R.id.btn_search_positions);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leagueId = leagueIdInput.getText().toString();
                String season = seasonInput.getText().toString();

                if (leagueId.isEmpty() || season.isEmpty()) {
                    Toast.makeText(getContext(), "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    fetchLeagueTable(leagueId, season);
                }
            }
        });

        return view;
    }

    private void fetchLeagueTable(String leagueId, String season) {
        // Utilizamos RetrofitClient para obtener la instancia de Retrofit
        PositionsApi positionsApi = RetrofitClient.getRetrofitInstance().create(PositionsApi.class);

        // Realizamos la llamada a la API
        Call<PositionsApi.LeagueTableResponse> call = positionsApi.getLeagueTable(leagueId, season);
        call.enqueue(new Callback<PositionsApi.LeagueTableResponse>() {
            @Override
            public void onResponse(Call<PositionsApi.LeagueTableResponse> call, Response<PositionsApi.LeagueTableResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TeamPosition> teamPositions = response.body().table;
                    adapter = new PositionsAdapter(teamPositions);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PositionsApi.LeagueTableResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}