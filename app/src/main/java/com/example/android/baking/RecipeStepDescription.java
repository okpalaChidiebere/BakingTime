package com.example.android.baking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.android.baking.Model.BakingFood;
import com.example.android.baking.Model.Steps;

import java.util.List;

public class RecipeStepDescription extends AppCompatActivity {

    private static final String TAG = "RecipeStepDescription";
    private static final String EXTRA_RECIPE_STEPS = "BAKING_FOOD_RECIPE_STEPS";

    private Steps mSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_description);

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back Button

        //If the phone is in landscape mode, we hide the status and action bar so that the user can
        // watch the video properly
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {

            if (intentThatStartedThisActivity.hasExtra(EXTRA_RECIPE_STEPS)) {
                mSteps = (Steps) intentThatStartedThisActivity.getSerializableExtra(EXTRA_RECIPE_STEPS);
                setTitle(mSteps.getShortDescription()); //page label
            }
        }
    }
}
