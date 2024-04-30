package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Map extends AppCompatActivity implements View.OnClickListener {
    private Button btnDuckGame, btnCircusGame, btnCardGame, btnFoodGame;
    private MediaPlayer btnSound, bgMusic;
    private TextView txtTotalPoints;
    private int totalPoints;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Start playing background music
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(Map.this, R.raw.map_bgmusic);

        // Initialize views
        initViews();

        // Load total points from SharedPreferences
        loadTotalPoints();

        buttonClickSound = new ButtonClickSound(btnSound);

        // Set click listeners for buttons
        setOnClick();
    }

    // Method to initialize views
    private void initViews() {
        btnDuckGame = findViewById(R.id.map_duckGame);
        btnCircusGame = findViewById(R.id.map_circusGame);
        btnCardGame = findViewById(R.id.map_cardGame);
        btnFoodGame = findViewById(R.id.map_foodGame);

        txtTotalPoints = findViewById(R.id.map_totalPoints);
    }

    // Method to load total points from SharedPreferences and update TextView
    private void loadTotalPoints() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        totalPoints = sharedPreferences.getInt("TotalPoints", 300);

        if (totalPoints < 0) {
            totalPoints = 0;
        }

        txtTotalPoints.setText("PHP " + totalPoints);
    }

    // Method to set click listeners for buttons
    private void setOnClick() {
        btnDuckGame.setOnClickListener(this);
        btnCircusGame.setOnClickListener(this);
        btnCardGame.setOnClickListener(this);
        btnFoodGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.map_duckGame) {
            playBtnSound();
            ActivityHelper.openNewActivity(Map.this, DuckStory.class);
        }

        if (v.getId() == R.id.map_circusGame) {
            playBtnSound();
            ActivityHelper.openNewActivity(Map.this, CircusStory.class);
        }

        if (v.getId() == R.id.map_cardGame) {
            playBtnSound();
            ActivityHelper.openNewActivity(Map.this, CardStory.class);
        }

        if (v.getId() == R.id.map_foodGame) {
            playBtnSound();
            ActivityHelper.openNewActivity(Map.this, FoodStory.class);
        }
    }

    private void playBtnSound() {
        buttonClickSound.playSound(Map.this, R.raw.buttonclicked);
    }

    // Handle back button press
    public void onBackPressed() {
        ActivityHelper.openNewActivity(Map.this, MainActivity.class);
    }


    @Override
    protected void onPause() {
        super.onPause();
        backGroundMusic.pauseMusic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        backGroundMusic.resumeMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop button click sound and background music
        backGroundMusic.stopMusic();
        buttonClickSound.stopSound();
    }
}