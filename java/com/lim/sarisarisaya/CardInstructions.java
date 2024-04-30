package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CardInstructions extends AppCompatActivity {
    private ImageButton MainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_instructions);

        // Initialize views and set text for each instruction section
        initViews();

        // Set click listener for the main menu button
        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open MainActivity when "Main Menu" button is clicked
                ActivityHelper.openNewActivity(CardInstructions.this, CardMenu.class);
            }
        });
    }

    // Handle back button press to navigate to the main menu
    public void onBackPressed() {
        ActivityHelper.openNewActivity(CardInstructions.this, CardMenu.class);
    }

    // Initialize views by finding their respective IDs
    private void initViews() {

        MainMenu = findViewById(R.id.menu);
    }
}