package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DuckMenu extends AppCompatActivity {
    private ImageButton imgBtnPlay, imgBtnInstructions, imgBtnMap;
    private MediaPlayer bgMusic;
    private ConstraintLayout ticketlayout;
    private ImageView ticketyes, ticketno;
    private BackGroundMusic backGroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_menu);

        playMusic();
        initViews();
        btnOnClick();
    }

    // Initialize Views (Buttons)
    private void initViews() {
        imgBtnPlay = findViewById(R.id.imgBtn_duckPlay);
        imgBtnInstructions = findViewById(R.id.imgBtn_duckInstructions);
        imgBtnMap = findViewById(R.id.imgBtn_duckMap);

        ticketyes = findViewById(R.id.ticketyes);
        ticketno = findViewById(R.id.ticketno);
        ticketlayout = findViewById(R.id.ticketlayout);
    }


    private void playMusic() {
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(DuckMenu.this, R.raw.duck_backgroundmusic);
    }

    // Set click listeners for buttons
    private void btnOnClick() {
        // Start button click listener
        imgBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.VISIBLE);
            }
        });

        ticketyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalPointsManager.updateTotalPoints(getApplicationContext());

                // Open DuckGameActivity when the Start button is clicked
                ActivityHelper.openNewActivity(DuckMenu.this, DuckGame.class);
            }
        });

        ticketno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.GONE);
            }
        });

        // Instructions button click listener
        imgBtnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Plays sound when clicked
                // Open DuckInstructionsActivity when the Instructions button is clicked
                ActivityHelper.openNewActivity(DuckMenu.this, DuckInstructions.class);
            }
        });

        // Exit button click listener
        imgBtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goes back to MapActivity when Exit button is clicked
                ActivityHelper.openNewActivity(DuckMenu.this, Map.class);
            }
        });
    }

    // Handle back button press to navigate to the MapActivity
    public void onBackPressed() {
        ActivityHelper.openNewActivity(DuckMenu.this, Map.class);
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
    }

}