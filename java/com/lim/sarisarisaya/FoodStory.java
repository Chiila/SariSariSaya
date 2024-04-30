package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FoodStory extends AppCompatActivity {
    private ImageButton btnFood;
    private MediaPlayer btnSound, voiceOver;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_story);

        // Initialize button
        btnFood = findViewById(R.id.btn_storyFood);

        // Create MediaPlayer for background and when the button is clicked
        backGroundMusic = new BackGroundMusic(voiceOver);
        backGroundMusic.playMusic(FoodStory.this, R.raw.story_snax);

        buttonClickSound = new ButtonClickSound(btnSound);

        // Set OnClickListener for the button
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play button click sound and open FoodMenu activity
                buttonClickSound.playSound(FoodStory.this, R.raw.buttonclicked);
                ActivityHelper.openNewActivity(FoodStory.this, FoodMenu.class);
            }
        });

    }

    // Handle back button press
    public void onBackPressed() {
        ActivityHelper.openNewActivity(FoodStory.this, Map.class);
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
        backGroundMusic.stopMusic();
        buttonClickSound.stopSound();
    }
}