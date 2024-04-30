package com.lim.sarisarisaya;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DuckPrizes extends AppCompatActivity {
    private ImageView imgPrize1, imgPrize2, imgPrize3;
    private int duck1Value, duck2Value, duck3Value;
    private Duck duck1, duck2, duck3;
    private ImageView[] prizeImages;
    private Duck[] ducks;
    private ObjectAnimator animator;
    private ImageView imgBtnPlayAgain, imgBtnExitGame;
    private TextView txtScore, txt_duckPrizes, cashadd;
    private int score;
    private MediaPlayer bgMusic, voiceOver;
    private BackGroundMusic backGroundMusic, bgVoiceOver;
    private ImageView ticketyes, ticketno, streakimg;
    private ConstraintLayout ticketlayout, streak, addcash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_prizes);

        // Plays Background Music
        playMusic();

        // Initialize views and retrieve intent extras
        initViews();
        getIntents();

        // Initialize ducks and set up shake animations for prize images
        initDucks();
        applyShakeAnimationForEachDuck();

        initScoreAndSetText();

        setClickListenerForPrizeImages();

        // Set click listeners for buttons
        buttonsListener();
    }

    // Initialize views
    private void initViews() {
        imgPrize1 = findViewById(R.id.img_duckPrize1);
        imgPrize2 = findViewById(R.id.img_duckPrize2);
        imgPrize3 = findViewById(R.id.img_duckPrize3);

        txtScore = findViewById(R.id.txt_duckScore);
        txt_duckPrizes = findViewById(R.id.txt_duckPrizes);

        imgBtnPlayAgain = findViewById(R.id.imgBtn_duckPlayAgain);
        imgBtnExitGame = findViewById(R.id.imgBtn_duckExitGame);

        ticketyes = findViewById(R.id.ticketyes);
        ticketno = findViewById(R.id.ticketno);
        ticketlayout = findViewById(R.id.ticketlayout);

        streak = findViewById(R.id.streak);
        streakimg = findViewById(R.id.streakimg);

        addcash = findViewById(R.id.addcash);
        cashadd = findViewById(R.id.cashadd);
    }

    private void playMusic() {
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(DuckPrizes.this, R.raw.duck_backgroundmusic);
    }

    // Retrieve intent extras to get duck values
    private void getIntents() {
        Intent intent = getIntent();
        String value1 = intent.getStringExtra("duck1");
        String value2 = intent.getStringExtra("duck2");
        String value3 = intent.getStringExtra("duck3");

        duck1Value = (value1 != null && !value1.isEmpty()) ? Integer.parseInt(value1) : 2;
        duck2Value = (value2 != null && !value2.isEmpty()) ? Integer.parseInt(value2) : 2;
        duck3Value = (value3 != null && !value3.isEmpty()) ? Integer.parseInt(value3) : 2;
    }

    // Apply shake animation to a prize image
    private void applyShakeAnimation(ImageView duckImage) {
        animator = ObjectAnimator.ofFloat(duckImage, "translationX", -10f, 10f);
        animator.setDuration(100);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);

        animator.start();
        duckImage.setTag(animator);
    }

    // Initialize ducks and set prize images based on duck values
    private void initDucks() {
        duck1 = new Duck(R.drawable.duck_moving, duck1Value);
        duck2 = new Duck(R.drawable.duck_moving, duck2Value);
        duck3 = new Duck(R.drawable.duck_moving, duck3Value);

        // Sets ImageView src
        imgPrize1.setImageResource(duck1.getDuckImage());
        imgPrize2.setImageResource(duck2.getDuckImage());
        imgPrize3.setImageResource(duck3.getDuckImage());

        prizeImages = new ImageView[] {imgPrize1, imgPrize2, imgPrize3};
        ducks = new Duck[] {duck1, duck2, duck3};
    }

    // Apply shake animation for each duck
    private void applyShakeAnimationForEachDuck() {
        for (ImageView prizeImage : prizeImages) {
            applyShakeAnimation(prizeImage);
        }
    }

    // Initialize score and set the text for score TextView
    private void initScoreAndSetText() {
        score = 0;
        txtScore.setText("Score: " + String.valueOf(score));
    }

    // Set click listeners for prize images
    private int buttonsClicked = 0;
    private int noPrizeCount = 0;
    private int prizeCount = 0;

    private void setClickListenerForPrizeImages() {
        for (int i = 0; i < prizeImages.length; i++) {
            int duckValue = ducks[i].getDuckValue();
            int index = i;

            prizeImages[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Remove the click listener to prevent further clicks
                    prizeImages[index].setOnClickListener(null);

                    // Cancel shake animation when the image is clicked
                    ObjectAnimator objectAnimator = (ObjectAnimator) prizeImages[index].getTag();
                    if (objectAnimator != null) {
                        objectAnimator.cancel();
                    }

                    // Set the appropriate prize image based on the duck value
                    if (duckValue == 0) {
                        prizeImages[index].setImageResource(R.drawable.normal_prize);
                        score += 1000;
                        prizeCount++; // Increment prize count
                    } else if (duckValue == 1) {
                        prizeImages[index].setImageResource(R.drawable.large_prize);
                        score += 500;
                        prizeCount++; // Increment prize count
                    } else if (duckValue == 2) {
                        prizeImages[index].setImageResource(R.drawable.no_prize);
                        noPrizeCount++; // Increment no prize count
                    }

                    txtScore.setText("Score: " + String.valueOf(score));
                    cashadd.setText(" " + String.valueOf(score) + " ");

                    // Increment the count of buttons clicked
                    buttonsClicked++;

                    // Check if all buttons are clicked
                    if (buttonsClicked == 3) {
                        addcash.setVisibility(View.VISIBLE);
                        imgPrize1.setVisibility(View.GONE);
                        imgPrize2.setVisibility(View.GONE);
                        imgPrize3.setVisibility(View.GONE);
                        txtScore.setVisibility(View.GONE);
                        imgBtnPlayAgain.setVisibility(View.GONE);
                        imgBtnExitGame.setVisibility(View.GONE);
                        txt_duckPrizes.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    // Set click listeners for buttons
    private void buttonsListener() {
        bgVoiceOver = new BackGroundMusic(voiceOver);

        addcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((prizeCount == 3) || (noPrizeCount == 3)) {
                    if (prizeCount == 3) {
                        addcash.setVisibility(View.GONE);

                        streak.setVisibility(View.VISIBLE);
                        backGroundMusic.pauseMusic();
                        bgVoiceOver.playMusic(DuckPrizes.this, R.raw.duck_threeprizes);

                        imgPrize1.setVisibility(View.GONE);
                        imgPrize2.setVisibility(View.GONE);
                        imgPrize3.setVisibility(View.GONE);
                        txtScore.setVisibility(View.GONE);
                        imgBtnPlayAgain.setVisibility(View.GONE);
                        imgBtnExitGame.setVisibility(View.GONE);
                        txt_duckPrizes.setVisibility(View.GONE);
                    } else {
                        addcash.setVisibility(View.GONE);

                        streak.setVisibility(View.VISIBLE);
                        streakimg.setImageResource(R.drawable.egged);
                        backGroundMusic.stopMusic();
                        bgVoiceOver.playMusic(DuckPrizes.this, R.raw.duck_egged);

                        imgPrize1.setVisibility(View.GONE);
                        imgPrize2.setVisibility(View.GONE);
                        imgPrize3.setVisibility(View.GONE);
                        txtScore.setVisibility(View.GONE);
                        imgBtnPlayAgain.setVisibility(View.GONE);
                        imgBtnExitGame.setVisibility(View.GONE);
                        txt_duckPrizes.setVisibility(View.GONE);
                    }
                }
                else {
                    addcash.setVisibility(View.GONE);
                    streak.setVisibility(View.GONE);
                    imgPrize1.setVisibility(View.VISIBLE);
                    imgPrize2.setVisibility(View.VISIBLE);
                    imgPrize3.setVisibility(View.VISIBLE);
                    txtScore.setVisibility(View.VISIBLE);
                    imgBtnPlayAgain.setVisibility(View.VISIBLE);
                    imgBtnExitGame.setVisibility(View.VISIBLE);
                    txt_duckPrizes.setVisibility(View.VISIBLE);
                }

            }
        });

        imgBtnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.VISIBLE);
                imgPrize1.setVisibility(View.GONE);
                imgPrize2.setVisibility(View.GONE);
                imgPrize3.setVisibility(View.GONE);
                txtScore.setVisibility(View.GONE);
                imgBtnPlayAgain.setVisibility(View.GONE);
                imgBtnExitGame.setVisibility(View.GONE);
                txt_duckPrizes.setVisibility(View.GONE);
            }
        });

        imgBtnExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalPointsManager.updateTotalPoints(getApplicationContext(), score);

                // Goes back to MapActivity when Exit button is clicked
                ActivityHelper.openNewActivity(DuckPrizes.this, Map.class);
            }
        });

        ticketyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalPointsManager.updateTotalPoints(getApplicationContext(), score - 50);

                // Open GameActivity when "Play Again" button is clicked
                ActivityHelper.openNewActivity(DuckPrizes.this, DuckGame.class);
            }
        });

        streak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgVoiceOver.stopMusic();
                backGroundMusic.resumeMusic();

                streak.setVisibility(View.GONE);
                imgPrize1.setVisibility(View.VISIBLE);
                imgPrize2.setVisibility(View.VISIBLE);
                imgPrize3.setVisibility(View.VISIBLE);
                txtScore.setVisibility(View.VISIBLE);
                imgBtnPlayAgain.setVisibility(View.VISIBLE);
                imgBtnExitGame.setVisibility(View.VISIBLE);
                txt_duckPrizes.setVisibility(View.VISIBLE);
            }
        });

        ticketno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.GONE);
                imgPrize1.setVisibility(View.VISIBLE);
                imgPrize2.setVisibility(View.VISIBLE);
                imgPrize3.setVisibility(View.VISIBLE);
                txtScore.setVisibility(View.VISIBLE);
                imgBtnPlayAgain.setVisibility(View.VISIBLE);
                imgBtnExitGame.setVisibility(View.VISIBLE);
                txt_duckPrizes.setVisibility(View.VISIBLE);
            }
        });
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

        // Cancel shake animation for each prize image to prevent memory leaks
        for (ImageView duckImage : prizeImages) {
            ObjectAnimator objectAnimator = (ObjectAnimator) duckImage.getTag();

            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
        }
    }

    public void onBackPressed() {
        Toast.makeText(DuckPrizes.this, "Open your Prizes!", Toast.LENGTH_SHORT).show();
    }
}