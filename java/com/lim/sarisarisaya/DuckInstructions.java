package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class DuckInstructions extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Instruction> instructionList;
    private InstructionAdapter instructionAdapter;
    private ImageButton imgBtnHome;
    private MediaPlayer bgMusic;
    private BackGroundMusic backGroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_instructions);

        playMusic();

        // Initialize views and list for each instruction section
        initViews();
        initList();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        instructionAdapter = new InstructionAdapter(instructionList);
        recyclerView.setAdapter(instructionAdapter);

        // On Click Listener to navigate to the main menu
        imgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.openNewActivity(DuckInstructions.this, DuckMenu.class);
            }
        });
    }

    // Initialize views by finding their respective IDs
    private void initViews() {
        recyclerView = findViewById(R.id.duck_instructionsRecycle);
        imgBtnHome = findViewById(R.id.imgBtn_duckHome);
    }

    private void playMusic() {
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(DuckInstructions.this, R.raw.duck_backgroundmusic);
    }

    private void initList() {
        instructionList = new ArrayList<>();

        instructionList.add(new Instruction("1. Objective:", "Your goal is to select three ducks out of the seven displayed on the screen and " +
                "uncover any potential prizes associated with them."));
        instructionList.add(new Instruction("2. Selecting Ducks:", "Tap on any of the seven ducks to make your selection. You can choose up to " +
                "three ducks."));
        instructionList.add(new Instruction("3. Revealing Prizes", "After you have selected three ducks, click on each duck to reveal its prize. " +
                "The prizes include a grand prize, normal prizes, or no prize."));
        instructionList.add(new Instruction("4. Prize Types:", "- Grand Prize: The most coveted prize. You win if you uncover the duck containing the grand prize.\n\n" +
                "- Normal Prize: There are three normal prizes available. Uncover the ducks holding these prizes to win.\n\n" +
                "- No Prize: Not every duck holds a prize. If you uncover a duck with no prize, keep trying!"));
        instructionList.add(new Instruction("5. End of Round:", "After you have revealed the prizes associated with your selected ducks, the round ends. " +
                "You can choose to play again or exit the game."));
    }

    // Handle back button press to navigate to the main menu
    public void onBackPressed() {
        ActivityHelper.openNewActivity(DuckInstructions.this, DuckMenu.class);
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