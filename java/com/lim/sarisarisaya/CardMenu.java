package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CardMenu extends AppCompatActivity {
    private ImageButton Start, Instructions, Exit;
    private ImageView logo, ticketyes, ticketno;
    private ConstraintLayout ticketlayout;
    private MediaPlayer bgMusic;
    private BackGroundMusic backGroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_menu);

        playMusic();
        initViews();
        btnOnClick();

    }

    // Initialize Views (Buttons)
    private void initViews() {
        Start = findViewById(R.id.start);
        Instructions = findViewById(R.id.instructions);
        Exit = findViewById(R.id.exit);
        logo = findViewById(R.id.imageView);
        ticketyes = findViewById(R.id.ticketyes);
        ticketno = findViewById(R.id.ticketno);
        ticketlayout = findViewById(R.id.ticketlayout);
    }

    // Set click listeners for buttons
    private void btnOnClick() {
        // Start button click listener
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.VISIBLE);
                Start.setVisibility(View.GONE);
                Instructions.setVisibility(View.GONE);
                Exit.setVisibility(View.GONE);
                logo.setVisibility(View.GONE);
            }
        });

        // Instructions button click listener
        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open InstructionsActivity when the Instructions button is clicked
                ActivityHelper.openNewActivity(CardMenu.this, CardInstructions.class);
            }
        });

        // Exit button click listener
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the MainActivity (close the app) when the Exit button is clicked
                ActivityHelper.openNewActivity(CardMenu.this, Map.class);
            }
        });

        ticketyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalPointsManager.updateTotalPoints(getApplicationContext());

                // Open GameActivity when the Start button is clicked
                ActivityHelper.openNewActivity(CardMenu.this, CardGame.class);
            }
        });

        ticketno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.GONE);
                Start.setVisibility(View.VISIBLE);
                Instructions.setVisibility(View.VISIBLE);
                Exit.setVisibility(View.VISIBLE);
                logo.setVisibility(View.VISIBLE);
            }
        });
    }

    private void playMusic() {
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(CardMenu.this, R.raw.mystic_bgm);
    }

    public void onBackPressed() {
        ActivityHelper.openNewActivity(CardMenu.this, Map.class);
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