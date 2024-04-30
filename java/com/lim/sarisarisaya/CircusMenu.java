package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CircusMenu extends AppCompatActivity {
    private MediaPlayer backgroundMusic;
    private ImageView imageView;
    private ImageView logoImageView;
    private ImageButton playButton;
    private ImageButton howToPlayButton;
    private ImageButton returnToMapButton;
    private ImageButton gotItButton;
    private TextView howToPlayText;
    private ImageView htpBackgroundImageView;
    private ImageView ticketyes, ticketno;
    private ConstraintLayout ticketlayout;
    int totalS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circus_menu);
        AppConstants.setContext(getApplicationContext());
        htpBackgroundImageView = findViewById(R.id.htpBackgroundImageView);
        AppConstants.initialization(getApplicationContext());
        int initialHighscore = AppConstants.getGameEngine().loadHighScore();
        AppConstants.getGameEngine().highscore = initialHighscore;
        totalS = AppConstants.getGameEngine().loadTotalScores();
// Initialize and start background music
        backgroundMusic = MediaPlayer.create(this, R.raw.chickensong);
        backgroundMusic.setLooping(true); // Set the music to loop
        backgroundMusic.start();
// Initialize views
        logoImageView = findViewById(R.id.logoImageView);
        imageView = findViewById(R.id.imageView);
// Initialize ImageButtons
        playButton = findViewById(R.id.playButton);
        howToPlayButton = findViewById(R.id.howToPlayButton);
        returnToMapButton = findViewById(R.id.returnToMapButton);
        gotItButton = findViewById(R.id.gotItButton);
        howToPlayText = findViewById(R.id.howToPlayText);
        ticketyes = findViewById(R.id.ticketyes);
        ticketno = findViewById(R.id.ticketno);
        ticketlayout = findViewById(R.id.ticketlayout);
        setHowToPlayText();
        ticketyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// DINAGDAG KO PRES. CHECK MO NA LANG YUNG TOTALPOINTSMANAGER CLASS NANDUN YUNG SHARED PREFERENCES
                TotalPointsManager.updateTotalPoints(getApplicationContext());
                stopMusicAndStartGame();
                Intent intent = new Intent(CircusMenu.this, CircusGame.class);
                startActivity(intent);
            }
        });
        ticketno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.GONE);
                logoImageView.setVisibility(View.VISIBLE);
                playButton.setVisibility(View.VISIBLE);
                howToPlayButton.setVisibility(View.VISIBLE);
                returnToMapButton.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
            }
        });
// Set up click listeners for ImageButtons
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.VISIBLE);
                logoImageView.setVisibility(View.GONE);
                playButton.setVisibility(View.GONE);
                howToPlayButton.setVisibility(View.GONE);
                returnToMapButton.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
            }
        });
        howToPlayButton.setOnClickListener(v -> showHowToPlay());
        gotItButton.setOnClickListener(v -> hideHowToPlay());
        returnToMapButton.setOnClickListener(v -> {
// DINAGDAG KO PRES. CHECK MO NA LANG YUNG TOTALPOINTSMANAGER CLASS NANDUN YUNG SHARED PREFERENCES
            TotalPointsManager.updateTotalPoints(getApplicationContext(), totalS);
            Intent intent = new Intent(CircusMenu.this, Map.class);
            startActivity(intent);
            finish();
            AppConstants.getGameEngine().resetTotalScores();
        });
    }

    private void showHowToPlay() {
// Hide all components except 'Got It' button
        logoImageView.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.GONE);
        howToPlayButton.setVisibility(View.GONE);
        returnToMapButton.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);
        howToPlayText.setVisibility(View.GONE); // Hide the text as well
// Show the full-screen How to Play background and 'Got It' button
        htpBackgroundImageView.setVisibility(View.VISIBLE);
        gotItButton.setVisibility(View.VISIBLE);
    }

    private void hideHowToPlay() {
// Show all main menu components
        logoImageView.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        howToPlayButton.setVisibility(View.VISIBLE);
        returnToMapButton.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
// Hide the full-screen How to Play background and 'Got It' button
        htpBackgroundImageView.setVisibility(View.GONE);
        gotItButton.setVisibility(View.GONE);
    }

    private void setHowToPlayText() {
    }

    private void startBackgroundMusic() {
        if (backgroundMusic != null && !backgroundMusic.isPlaying()) {
            backgroundMusic.start();
        }
    }

    private void stopMusicAndStartGame() {
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
            backgroundMusic.release(); // Release the MediaPlayer resource
            backgroundMusic = null;
        }
// Start the game activity
        AppConstants.getGameEngine().restartGame();
        Intent intent = new Intent(CircusMenu.this, CircusGame.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundMusic != null && backgroundMusic.isPlaying()) {
            backgroundMusic.pause(); // Pause the music when the activity is paused
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundMusic == null) {
            backgroundMusic = MediaPlayer.create(this, R.raw.chickensong);
            backgroundMusic.setLooping(true);
            backgroundMusic.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (backgroundMusic != null) {
            backgroundMusic.release();
        }
    }

    public void onBackPressed() {
        ActivityHelper.openNewActivity(CircusMenu.this, Map.class);
    }
}
