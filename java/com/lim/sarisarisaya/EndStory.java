package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EndStory extends AppCompatActivity {
    private ImageButton btnEnd;
    private MediaPlayer btnSound, voiceOver;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_story);

        // Initialize views
        btnEnd = findViewById(R.id.btn_storyEnd);

        // Play Voice Over for the Story
        backGroundMusic = new BackGroundMusic(voiceOver);
        backGroundMusic.playMusic(EndStory.this, R.raw.story_end);

        buttonClickSound = new ButtonClickSound(btnSound);

        // Set OnClickListener for the button
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play button click sound and finish the activity
                buttonClickSound.playSound(EndStory.this, R.raw.buttonclicked);
                finish();
            }
        });
    }

    // Handle back button press
    public void onBackPressed() {
        finish();
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