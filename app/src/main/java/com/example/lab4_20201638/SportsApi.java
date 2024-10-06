package com.example.lab4_20201638;

import com.example.lab4_20201638.model.League;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SportsApi {
    @GET("api/v1/json/3/all_leagues.php")
    Call<LigasResponse> getAllLeagues();

    @GET("api/v1/json/3/search_all_leagues.php")
    Call<LigasResponse> getLeaguesByCountry(@Query("c") String country);
}

