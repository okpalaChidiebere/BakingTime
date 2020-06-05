package com.example.android.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.android.baking.Model.BakingFood;
import com.example.android.baking.Model.Ingredients;
import com.example.android.baking.Utils.ApiClient;
import com.example.android.baking.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BakingFoodTagAdapter.BakingFoodTagAdapterOnClickHandler{

    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;
    private RecyclerView mRecyclerView;
    private BakingFoodTagAdapter foodTagAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Context context;
    List<BakingFood> list = new ArrayList<>();
    List<Ingredients> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_bakingFoodTags);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        context = this;

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        foodTagAdapter = new BakingFoodTagAdapter(list, context, context);

        Call<List<BakingFood>> call = apiInterface.getBakingFoods();
        call.enqueue(new Callback<List<BakingFood>>() {
            @Override
            public void onResponse(Call<List<BakingFood>> call, Response<List<BakingFood>> response) {

                list = response.body();
                // specify an adapter (see also next example)
                //foodTagAdapter = new BakingFoodTagAdapter(list, getApplicationContext());
                foodTagAdapter = new BakingFoodTagAdapter(list, context, context);
                mRecyclerView.setAdapter(foodTagAdapter);
            }

            @Override
            public void onFailure(Call<List<BakingFood>> call, Throwable t) {
                Log.e(TAG, "onFailure "+ t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onClick(BakingFood foodTagName) {

    }
}
