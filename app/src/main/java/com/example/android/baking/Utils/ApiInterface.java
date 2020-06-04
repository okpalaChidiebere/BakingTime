package com.example.android.baking.Utils;

import com.example.android.baking.Model.BakingFood;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<BakingFood>> getBakingFoods();
}
