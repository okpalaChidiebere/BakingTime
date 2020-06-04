package com.example.android.baking;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.example.android.baking.Model.BakingFood;
import com.example.android.baking.Model.Ingredients;
import com.example.android.baking.Utils.ApiClient;
import com.example.android.baking.Utils.ApiInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<List<BakingFood>> call = apiInterface.getBakingFoods();
        call.enqueue(new Callback<List<BakingFood>>() {
            @Override
            public void onResponse(Call<List<BakingFood>> call, Response<List<BakingFood>> response) {
                Log.e(TAG, "onResponse "+ response.body());

                /*Log.v("RESPONSE_CALLED", "ON_RESPONSE_CALLED");
                String didItWork = String.valueOf(response.isSuccessful());
                Log.v("SUCCESS?", didItWork);
                Log.v("RESPONSE_CODE", String.valueOf(response.code()));
                BakingFood food = response.body().;
                Log.v("RESPONSE_BODY", "response:" + food);
                String total = response.body().getName().toString();
                Log.v("Total", total);
                List<Ingredients> ingredientsResults = response.body().get(1).setIngredients();
                for (Ingredients ingredients : ingredientsResults) {
                    Log.v("PHOTO_URL:", ingredients.getIngredient()
                    );
                }*/
            }

            @Override
            public void onFailure(Call<List<BakingFood>> call, Throwable t) {
                Log.e(TAG, "onFailure "+ t.getLocalizedMessage());
            }
        });
    }
}
