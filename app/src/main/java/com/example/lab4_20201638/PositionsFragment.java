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

import com.example.lab4_20201638.adapters.PositionsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PositionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PositionsFragment extends Fragment {

    private RecyclerView recyclerView;
    private PositionsAdapter adapter;
    private List<String> positionsList;

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

        // Inicializar la lista de posiciones
        positionsList = new ArrayList<>();
        // Aquí puedes agregar datos de ejemplo o cargarlos desde una API
        positionsList.add("Equipo A - 1°");
        positionsList.add("Equipo B - 2°");
        positionsList.add("Equipo C - 3°");

        adapter = new PositionsAdapter(positionsList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}