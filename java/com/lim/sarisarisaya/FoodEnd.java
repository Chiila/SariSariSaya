package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodEnd extends AppCompatActivity {
    private TextView score, hs, textView, textView2, cashadd;
    private ImageView imgv, imgv2, imageView4;
    private ConstraintLayout addcash;
    int lastScore;
    int best;
    private MediaPlayer mainmusic, buttonclicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_end);

        // Initialize views
        initViews();

        // Initialize media players
        buttonclicked = MediaPlayer.create(this, R.raw.buttonclicked);
        mainmusic = mainmusic.create(this, R.raw.mainmusic);

        // Set main music to loop and start playing
        mainmusic.setLooping(true);
        mainmusic.start();

        // Retrieve last score and best score from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        lastScore = preferences.getInt("LastScore", 0);
        best = preferences.getInt("best", 0);

        // Update best score if last score is greater
        if (lastScore > best) {
            best = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best", best);
            editor.apply();
        }

        // Update UI with scores
        score.setText(" " + lastScore + " ");
        hs.setText(" " + best + " ");
        cashadd.setText(" " + lastScore + " ");

        // Set click listeners for buttons
        setClickListener();
    }

    // Method to initialize views
    private void initViews() {
        score = findViewById(R.id.scorel);
        hs = findViewById(R.id.hs);
        imgv2 = findViewById(R.id.imgv2);
        imgv = findViewById(R.id.imgv);
        imageView4 = findViewById(R.id.imageView4);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        cashadd = findViewById(R.id.cashadd);
        addcash = findViewById(R.id.addcash);

    }

    // Method to set click listeners for image buttons
    private void setClickListener() {
        imgv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.openNewActivity(FoodEnd.this, FoodGame.class);
                buttonclicked.start();
                mainmusic.stop();
            }
        });

        imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open FoodGame activity when image button is clicked
                ActivityHelper.openNewActivity(FoodEnd.this, FoodMenu.class);

                // Play button clicked sound
                buttonclicked.start();

                // Stop main music
                mainmusic.stop();
            }
        });

        addcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score.setVisibility(View.VISIBLE);
                hs.setVisibility(View.VISIBLE);
                imgv2.setVisibility(View.VISIBLE);
                imgv.setVisibility(View.VISIBLE);
                imageView4.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                addcash.setVisibility(View.GONE);
            }
        });
    }

    // Handle back button press to navigate to FoodMenu activity
    public void onBackPressed() {

        ActivityHelper.openNewActivity(FoodEnd.this, FoodMenu.class);
    }
}