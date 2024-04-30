package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DuckStory extends AppCompatActivity {
    private ImageButton btnDuck;
    private MediaPlayer btnSound, voiceOver;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_story);

        // Initialize Button
        btnDuck = findViewById(R.id.btn_storyDuck);

        // Create MediaPlayer for background and when the button is clicked
        backGroundMusic = new BackGroundMusic(voiceOver);
        backGroundMusic.playMusic(DuckStory.this, R.raw.story_duck);

        buttonClickSound = new ButtonClickSound(btnSound);

        // Set OnClickListener for the button
        btnDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play button sound and navigate to DuckMenu activity
                buttonClickSound.playSound(DuckStory.this, R.raw.buttonclicked);
                ActivityHelper.openNewActivity(DuckStory.this, DuckMenu.class);
            }
        });
    }


    // Handle back button press to navigate to the Map activity
    public void onBackPressed() {
        ActivityHelper.openNewActivity(DuckStory.this, Map.class);
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