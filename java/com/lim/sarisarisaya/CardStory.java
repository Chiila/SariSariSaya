package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CardStory extends AppCompatActivity {
    private ImageButton btnCard;
    private MediaPlayer btnSound, voiceOver;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_story);

        btnCard = findViewById(R.id.btn_storyCard);

        backGroundMusic = new BackGroundMusic(voiceOver);
        backGroundMusic.playMusic(CardStory.this, R.raw.story_mystic);

        buttonClickSound = new ButtonClickSound(btnSound);

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickSound.playSound(CardStory.this, R.raw.buttonclicked);
                ActivityHelper.openNewActivity(CardStory.this, CardMenu.class);
            }
        });
    }

    public void onBackPressed() {
        ActivityHelper.openNewActivity(CardStory.this, Map.class);
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