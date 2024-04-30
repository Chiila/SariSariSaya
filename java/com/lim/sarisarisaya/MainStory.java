package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainStory extends AppCompatActivity {
    private Button btnMain;
    private MediaPlayer btnSound, voiceOver;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_story);

        btnMain = findViewById(R.id.story_btnMain);

        backGroundMusic = new BackGroundMusic(voiceOver);
        backGroundMusic.playMusic(MainStory.this, R.raw.story_welcome);

        buttonClickSound = new ButtonClickSound(btnSound);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickSound.playSound(MainStory.this, R.raw.buttonclicked);
                ActivityHelper.openNewActivity(MainStory.this, Map.class);
            }
        });
    }

    public void onBackPressed() {
        ActivityHelper.openNewActivity(MainStory.this, MainActivity.class);
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