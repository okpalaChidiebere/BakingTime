package com.example.android.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.android.baking.Model.BakingFood;
import com.example.android.baking.Model.Steps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeName extends AppCompatActivity implements RecipeStepAdapter.RecipeStepAdapterOnClickHandler{

    private static final String TAG = "RecipeName";

    /*Puts extra name strings*/
    private static final String EXTRA_FOOD_TAG_ID = "BAKING_FOOD_TAG_ID";
    private static final String EXTRA_FOOD_LIST = "BAKING_FOOD_LIST";
    private static final String EXTRA_RECIPE_STEPS = "BAKING_FOOD_RECIPE_STEPS";

    private RecyclerView mRecyclerView_ingredients;
    private RecyclerView mRecyclerView_recipeSteps;
    private FoodIngredientAdapter foodIngredientAdapter;
    private RecipeStepAdapter recipeStepAdapter;
    private RecyclerView.LayoutManager layoutManager_Ingredients;
    private RecyclerView.LayoutManager layoutManager_recipeSteps;

    private int mFoodTagID;
    List<BakingFood> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_name);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back Button

        mRecyclerView_ingredients = (RecyclerView) findViewById(R.id.tv_recyclerView_step_ingredients);
        mRecyclerView_recipeSteps = (RecyclerView) findViewById(R.id.tv_recyclerView_step_description);

        mRecyclerView_ingredients.setHasFixedSize(true);
        mRecyclerView_recipeSteps.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager_Ingredients = new LinearLayoutManager(this);
        layoutManager_recipeSteps = new LinearLayoutManager(this);

        mRecyclerView_ingredients.setLayoutManager(layoutManager_Ingredients);
        mRecyclerView_recipeSteps.setLayoutManager(layoutManager_recipeSteps);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {

           /* if (intentThatStartedThisActivity.hasExtra(EXTRA_FOOD_TAG_ID)) {
                mFoodTagID = intentThatStartedThisActivity.getIntExtra(EXTRA_FOOD_TAG_ID, 0);
            }*/

            if (intentThatStartedThisActivity.hasExtra(EXTRA_FOOD_LIST)) {
                list = (List<BakingFood>) intentThatStartedThisActivity.getSerializableExtra(EXTRA_FOOD_LIST);
                setTitle(list.get(0).getName()); //page label
                //Log.e(TAG, list.get(0).getSteps().toString());
                foodIngredientAdapter = new FoodIngredientAdapter(list.get(0).getIngredients());
                recipeStepAdapter = new RecipeStepAdapter(list.get(0).getSteps(), this);

                mRecyclerView_ingredients.setAdapter(foodIngredientAdapter);
                mRecyclerView_recipeSteps.setAdapter(recipeStepAdapter);
            }
        }
    }

    @Override
    public void onClick(Steps recipeSteps) {
        Steps steps = new Steps(recipeSteps.getId(),
                recipeSteps.getShortDescription(),
                recipeSteps.getDescription(),
                recipeSteps.getVideoURL(),
                recipeSteps.getThumbnailURL());

        //Log.e("BakingAppRecipeSteps", steps.toString());
        Context context = this;
        Class destinationClass = RecipeStepDescription.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(EXTRA_RECIPE_STEPS, (Serializable) steps);
        startActivity(intentToStartDetailActivity);


    }
}
