package com.example.lab4_20201638;

import com.example.lab4_20201638.model.TeamPosition;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PositionsApi {

    // Método para obtener las posiciones de la liga según la temporada
    @GET("lookuptable.php")
    Call<LeagueTableResponse> getLeagueTable(
            @Query("l") String leagueId,
            @Query("s") String season
    );

    // Clase contenedora de la respuesta de la API
    class LeagueTableResponse {
        public List<TeamPosition> table; // Cambia 'table' por el nombre del campo en tu API
    }
}

