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

public class FoodMenu extends AppCompatActivity {
    private ImageView howtoplay, tutorialimage, next, starttuts;
    private ConstraintLayout tutorial;
    private ImageButton start, map;
    private MediaPlayer mainmusic, buttonclicked;
    private ConstraintLayout ticketlayout;
    private ImageView ticketyes, ticketno;

    private int[] howtoplayImages = {
            R.drawable.htp1, R.drawable.htp2, R.drawable.htp3, R.drawable.htp4, R.drawable.htp5,
            R.drawable.htp6, R.drawable.htp7, R.drawable.htp8, R.drawable.htp9, R.drawable.htp10
    };
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        // Initialize UI elements
        initViews();

        // Create MediaPlayer instances
        buttonclicked = MediaPlayer.create(this, R.raw.buttonclicked);
        mainmusic = mainmusic.create(this, R.raw.mainmusic);

        // Set looping and start main music
        mainmusic.setLooping(true);
        mainmusic.start();

        // Set click listeners for UI elements
        setClickListeners();

    }

    // Method to stop main music when activity is destroyed
    private void stopMusic() {
        if (mainmusic != null) {
            // Release the resources associated with the MediaPlayer
            mainmusic.release();
            // Set MediaPlayer to null to indicate it's no longer in use
            mainmusic = null;
        }
    }

    // Method to initialize UI elements
    private void initViews() {
        howtoplay = findViewById(R.id.howtoplay);
        tutorialimage = findViewById(R.id.tutorialimage);
        next = findViewById(R.id.next);
        tutorial = findViewById(R.id.tutorial);
        start = findViewById(R.id.start);
        map = findViewById(R.id.map);
        starttuts = findViewById(R.id.starttuts);

        ticketyes = findViewById(R.id.ticketyes);
        ticketno = findViewById(R.id.ticketno);
        ticketlayout = findViewById(R.id.ticketlayout);
    }

    // Method to set click listeners for UI elements
    private void setClickListeners() {

        // Click listener for "How to Play" button
        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show tutorial and hide other buttons
                buttonclicked.start();
                tutorial.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.GONE);
                map.setVisibility(View.GONE);
                start.setVisibility(View.GONE);
            }
        });

        // Click listener for "Next" button in tutorial
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonclicked.start();

                // Display next tutorial image
                currentImageIndex = (currentImageIndex + 1) % howtoplayImages.length;
                tutorialimage.setImageResource(howtoplayImages[currentImageIndex]);

                // Show "Start Tutorial" button when last image is reached
                if (currentImageIndex == howtoplayImages.length - 1) {
                    starttuts.setVisibility(View.VISIBLE);
                    next.setVisibility(View.GONE);
                }
            }
        });

        // Click listener for "Start" button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start FoodGame activity
                ActivityHelper.openNewActivity(FoodMenu.this, FoodGame.class);

                // Stop main music
                mainmusic.stop();
            }
        });

        // Click listener for "Map" button
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Map activity
                ActivityHelper.openNewActivity(FoodMenu.this, Map.class);
            }
        });

        // Click listener for "Start Tutorial" button
        starttuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Restart FoodMenu activity to restart tutorial
                ActivityHelper.openNewActivity(FoodMenu.this, FoodMenu.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Stop main music when activity is destroyed
        stopMusic();
    }

    // Handle back button press
    public void onBackPressed() {
        ActivityHelper.openNewActivity(FoodMenu.this, Map.class);
    }

}