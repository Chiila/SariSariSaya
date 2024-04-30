package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imgBtnStart, imgBtnExit, imgBtnSocial, imgBtnAbout;
    private MediaPlayer btnSound, bgMusic;
    private BackGroundMusic backGroundMusic;
    private ButtonClickSound buttonClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(MainActivity.this, R.raw.map_bgmusic);

        // Initialize views
        initViews();

        // Create MediaPlayer with button click sound
        buttonClickSound = new ButtonClickSound(btnSound);

        // Set click listeners for buttons
        setOnClick();
    }

    // Method to initialize views by finding their respective IDs
    private void initViews() {
        imgBtnStart = findViewById(R.id.main_btnStart);
        imgBtnExit = findViewById(R.id.main_btnExit);

        imgBtnSocial = findViewById(R.id.main_socials);
        imgBtnAbout = findViewById(R.id.main_about);
    }

    // Method to set click listeners for buttons
    private void setOnClick() {
        imgBtnStart.setOnClickListener(this);
        imgBtnExit.setOnClickListener(this);

        imgBtnSocial.setOnClickListener(this);
        imgBtnAbout.setOnClickListener(this);
    }

    // OnClickListener implementation for button clicks
    @Override
    public void onClick(View v) {
        // Handle button clicks
        if (v.getId() == R.id.main_btnStart) {
            playBtnSound();
            ActivityHelper.openNewActivity(MainActivity.this, MainStory.class);
        }

        if (v.getId() == R.id.main_btnExit) {
            playBtnSound();
            ActivityHelper.openNewActivity(MainActivity.this, EndStory.class);
        }

        if (v.getId() == R.id.main_socials) {
            playBtnSound();
            ActivityHelper.openNewActivity(MainActivity.this, Socials.class);
        }

        if (v.getId() == R.id.main_about) {
            playBtnSound();
            ActivityHelper.openNewActivity(MainActivity.this, AboutUs.class);
        }
    }

    // Method to play button click sound
    private void playBtnSound() {
        buttonClickSound.playSound(MainActivity.this, R.raw.buttonclicked);
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

    // Clean up when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        backGroundMusic.stopMusic();
        buttonClickSound.stopSound();
    }
}