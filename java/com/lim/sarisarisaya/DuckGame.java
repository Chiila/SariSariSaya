package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class DuckGame extends AppCompatActivity {
    private ImageView imgDuck1, imgDuck2, imgDuck3, imgDuck4, imgDuck5, imgDuck6, imgDuck7;
    private Duck duck1, duck2, duck3, duck4, duck5, duck6, duck7;
    private ImageView[] duckImages;
    private Duck[] ducks;
    private ArrayList<Integer> values;
    private ArrayList<String> chosenDuckValues;
    private ObjectAnimator animator;
    private ImageButton imgBtnExitGame;
    private MediaPlayer bgMusic;
    private BackGroundMusic backGroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_game);

        playMusic();
        initViews();
        initDucks();
        initValues();
        assignRandomValuesToDucks();
        moveDuckWithAnimations();
        setClickListenersForDuckImages();
    }

    // Initialize views (duck images)
    private void initViews() {
        imgDuck1 = findViewById(R.id.img_duck1);
        imgDuck2 = findViewById(R.id.img_duck2);
        imgDuck3 = findViewById(R.id.img_duck3);
        imgDuck4 = findViewById(R.id.img_duck4);
        imgDuck5 = findViewById(R.id.img_duck5);
        imgDuck6 = findViewById(R.id.img_duck6);
        imgDuck7 = findViewById(R.id.img_duck7);

        imgBtnExitGame = findViewById(R.id.imgBtn_duckExit);
    }

    private void playMusic() {
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(DuckGame.this, R.raw.duck_backgroundmusic);
    }

    private void initDucks() {
        duck1 = new Duck(R.drawable.duck_moving);
        duck2 = new Duck(R.drawable.duck_moving);
        duck3 = new Duck(R.drawable.duck_moving);
        duck4 = new Duck(R.drawable.duck_moving);
        duck5 = new Duck(R.drawable.duck_moving);
        duck6 = new Duck(R.drawable.duck_moving);
        duck7 = new Duck(R.drawable.duck_moving);

        // Sets the ImageViews src
        imgDuck1.setImageResource(duck1.getDuckImage());
        imgDuck2.setImageResource(duck2.getDuckImage());
        imgDuck3.setImageResource(duck3.getDuckImage());
        imgDuck4.setImageResource(duck4.getDuckImage());
        imgDuck5.setImageResource(duck5.getDuckImage());
        imgDuck6.setImageResource(duck6.getDuckImage());
        imgDuck7.setImageResource(duck7.getDuckImage());

        duckImages = new ImageView[] {imgDuck1, imgDuck2, imgDuck3, imgDuck4, imgDuck5, imgDuck6, imgDuck7};
        ducks = new Duck[] {duck1, duck2, duck3, duck4, duck5, duck6, duck7};
    }

    // Initialize the possible values for ducks (Prizes)
    private void initValues() {
        values = new ArrayList<>();
        values.add(0);
        values.add(1);
        values.add(1);
        values.add(1);
        values.add(2);
        values.add(2);
        values.add(2);
    }

    // Assign random values to ducks from the available values
    private void assignRandomValuesToDucks() {
        Random random = new Random();

        for (Duck duck : ducks) {
            int randomValueIndex = random.nextInt(values.size());
            int randomValue = values.get(randomValueIndex);

            duck.setDuckValue(randomValue);
            values.remove(randomValueIndex);
        }
    }

    // Move a duck with animation
    private void moveDuck(ImageView duckImage, long delay, Duck duck) {
        duckImage.setTranslationX(-duckImage.getWidth());
        duckImage.setTranslationY(0);

        animator = ObjectAnimator.ofFloat(duckImage, "translationX", getScreenWidth());
        animator.setDuration(3000);
        animator.setStartDelay(delay);
        animator.setRepeatCount(ObjectAnimator.INFINITE);

        animator.start();
    }

    private void moveDuckWithAnimations() {
        // Initialize chosenDuckValues list
        chosenDuckValues = new ArrayList<>();

        // Move ducks with animations
        for (int i = 0; i < duckImages.length; i++) {
            Random random = new Random();
            long delay = random.nextInt(1500);
            moveDuck(duckImages[i], delay, ducks[i]);
        }
    }

    private void setClickListenersForDuckImages() {
        // Set click listeners for duck images
        for (int i = 0; i < duckImages.length; i++) {
            String duckValue = Integer.toString(ducks[i].getDuckValue());
            int index = i;
            final int choice = 2;

            duckImages[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Add clicked duck's value to chosenDuckValues and hide the image
                    chosenDuckValues.add(duckValue);
                    duckImages[index].setVisibility(View.INVISIBLE);

                    // Start the PrizesActivity when 3 ducks are chosen
                    if (chosenDuckValues.size() == 3) {
                        startPrizesActivity();
                    }
                }
            });
        }

        imgBtnExitGame.setOnClickListener(new View.OnClickListener() {
            int k = 1;
            @Override
            public void onClick(View v) {
                if (k == 1) {
                    k++;
                    Toast.makeText(DuckGame.this, "Press the back button again to exit.", Toast.LENGTH_SHORT).show();
                }
                else if (k == 2) {
                    ActivityHelper.openNewActivity(DuckGame.this, DuckMenu.class);
                }
            }
        });
    }

    // Get the width of the screen
    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    // Start the PrizesActivity with chosen duck values
    private void startPrizesActivity() {
        Intent intent = new Intent(DuckGame.this, DuckPrizes.class);
        intent.putExtra("duck1", chosenDuckValues.get(0));
        intent.putExtra("duck2", chosenDuckValues.get(1));
        intent.putExtra("duck3", chosenDuckValues.get(2));
        startActivity(intent);
        finish();
    }

    int j = 1;
    public void onBackPressed() {
        if (j == 1) {
            j++;
            Toast.makeText(this, "Press the back button again to exit.", Toast.LENGTH_SHORT).show();
        }
        else if (j == 2) {
            ActivityHelper.openNewActivity(DuckGame.this, DuckMenu.class);
        }
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
        // Releases the MediaPlayer
        backGroundMusic.stopMusic();

        // Cancel the animator if it's running to prevent memory leaks
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
    }

}