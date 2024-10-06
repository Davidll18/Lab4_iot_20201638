package com.example.lab4_20201638;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        // Configurar los botones para navegar entre fragmentos
        findViewById(R.id.button_leagues).setOnClickListener(v -> loadFragment(new LeaguesFragment()));
        findViewById(R.id.button_positions).setOnClickListener(v -> loadFragment(new PositionsFragment()));
        findViewById(R.id.button_results).setOnClickListener(v -> loadFragment(new ResultsFragment()));

        // Cargar fragmento por defecto (Ligas)
        if (savedInstanceState == null) {
            loadFragment(new LeaguesFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // Elimina el BackStack
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Si el BackStack está vacío, regresa al MainActivity
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed(); // Regresa al MainActivity
        }
    }
}
