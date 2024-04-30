package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CircusStory extends AppCompatActivity {
    private ImageButton btnCircus;
    private MediaPlayer btnSound, voiceOver;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circus_story);

        btnCircus = findViewById(R.id.btn_storyCircus);

        backGroundMusic = new BackGroundMusic(voiceOver);
        backGroundMusic.playMusic(CircusStory.this, R.raw.story_circus);

        buttonClickSound = new ButtonClickSound(btnSound);

        btnCircus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickSound.playSound(CircusStory.this, R.raw.buttonclicked);
                ActivityHelper.openNewActivity(CircusStory.this, CircusMenu.class);
            }
        });
    }

    public void onBackPressed() {
        ActivityHelper.openNewActivity(CircusStory.this, Map.class);
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