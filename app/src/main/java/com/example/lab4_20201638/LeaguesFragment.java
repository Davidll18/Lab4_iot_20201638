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

import com.example.lab4_20201638.adapters.LeaguesAdapter;
import com.example.lab4_20201638.model.League;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LeaguesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaguesFragment extends Fragment {

    private RecyclerView recyclerView;
    private LeaguesAdapter leaguesAdapter;
    private List<League> leaguesList;
    private EditText editTextCountry;
    private Button buttonSearch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LeaguesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaguesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeaguesFragment newInstance(String param1, String param2) {
        LeaguesFragment fragment = new LeaguesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leaguesList = new ArrayList<>(); // Inicialización de la lista

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leagues, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_leagues);
        editTextCountry = view.findViewById(R.id.search_by_country);
        buttonSearch = view.findViewById(R.id.btn_search_leagues);

        // Inicializa el adaptador aquí
        leaguesAdapter = new LeaguesAdapter(leaguesList, getContext()); // Asigna a leaguesAdapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(leaguesAdapter); // Ahora usa leaguesAdapter

        // Cargar datos de prueba
        loadLeaguesData();

        // Buscar ligas por país
        buttonSearch.setOnClickListener(v -> searchLeaguesByCountry());

        return view;
    }

    private void loadLeaguesData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SportsApi api = retrofit.create(SportsApi.class);
        Call<LigasResponse> call = api.getAllLeagues();

        call.enqueue(new Callback<LigasResponse>() {
            @Override
            public void onResponse(Call<LigasResponse> call, Response<LigasResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    leaguesList.clear(); // Esto no debería causar NullPointerException ahora
                    leaguesList.addAll(response.body().getLeagues());
                    // Notifica al adaptador sobre el cambio
                    leaguesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<LigasResponse> call, Throwable t) {
                // Manejar errores
            }
        });
    }

    private void searchLeaguesByCountry() {
        String country = editTextCountry.getText().toString().trim();
        if (country.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a country", Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SportsApi api = retrofit.create(SportsApi.class);
        Call<LigasResponse> call = api.getLeaguesByCountry(country);

        call.enqueue(new Callback<LigasResponse>() {
            @Override
            public void onResponse(Call<LigasResponse> call, Response<LigasResponse> response) {
                if (response.isSuccessful()) {
                    leaguesList.clear();
                    leaguesList.addAll(response.body().getLeagues());
                    leaguesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<LigasResponse> call, Throwable t) {
                // Manejar errores
            }
        });
    }



}