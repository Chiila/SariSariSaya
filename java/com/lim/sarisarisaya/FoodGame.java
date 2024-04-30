package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FoodGame extends AppCompatActivity {
    private ImageView serveButton;
    private ImageView img1, img2, img3;
    private ImageView imgbtn1, imgbtn2, imgbtn3;
    private ImageButton option1, option2, option3;
    private ImageButton trash;
    private ImageButton order1, order2, order3;
    private TextView scoreText, timer;
    private ImageView customer;
    private ImageButton Home, Restart, Resume, Menu;
    private ConstraintLayout MenuDisplay, sureask, sureaskreset, orderlayout, conclick;
    private ImageView oms, ihh, omsr, ihhr;
    int count = 0;
    int score = 0;
    private final int[] imageValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private List<Integer> shuffledValues;
    private int optionValue1, optionValue2, optionValue3;
    private int[] orderValues = new int[3];
    private int nextOrderIndex;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private static final long START_TIME_IN_MILLIS = 120000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private MediaPlayer music, buttonclicked, pauseaudio, kaching, trashm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_game);

        img1 = findViewById(R.id.popcornimg);
        img2 = findViewById(R.id.ccimg);
        img3 = findViewById(R.id.drinksimg);
        randomizeImageViews();

        imgbtn1 = findViewById(R.id.popcornbtn);
        imgbtn2 = findViewById(R.id.ccbtn);
        imgbtn3 = findViewById(R.id.drinksbtn);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);

        order1 = findViewById(R.id.order1);
        order2 = findViewById(R.id.order2);
        order3 = findViewById(R.id.order3);

        serveButton = findViewById(R.id.customer);
        trash = findViewById(R.id.basura);
        scoreText = findViewById(R.id.scoreboard);
        timer = findViewById(R.id.timer);

        MenuDisplay = findViewById(R.id.MenuDisplay);
        Menu = findViewById(R.id.Menu);
        Home = findViewById(R.id.Home);
        Restart = findViewById(R.id.Restart);
        Resume = findViewById(R.id.Resume);
        conclick = findViewById(R.id.conclick);

        sureask = findViewById(R.id.sureask);
        oms = findViewById(R.id.oms);
        ihh = findViewById(R.id.ihh);

        sureaskreset = findViewById(R.id.sureaskreset);
        omsr = findViewById(R.id.omsr);
        ihhr = findViewById(R.id.ihhr);

        orderlayout = findViewById(R.id.orderlayout);

        customer = findViewById(R.id.customer);
        randomizeCustomer();
        startTimer();

        order1.setVisibility(View.INVISIBLE);
        order2.setVisibility(View.INVISIBLE);
        order3.setVisibility(View.INVISIBLE);

        pauseaudio = MediaPlayer.create(this, R.raw.pauseaudio);
        music = MediaPlayer.create(this, R.raw.snaxmusic);
        buttonclicked = MediaPlayer.create(this, R.raw.buttonclicked);
        kaching = MediaPlayer.create(this, R.raw.kaching);
        trashm = MediaPlayer.create(this, R.raw.trash);

        if (mTimeLeftInMillis <= 120000) {
            music.start();
        }

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseaudio.start();
                toPause();
            }
        });

        Restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                sureaskreset.setVisibility(View.VISIBLE);
            }
        });

        omsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                ActivityHelper.openNewActivity(FoodGame.this, FoodGame.class);
            }
        });

        ihhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                sureaskreset.setVisibility(View.GONE);
            }
        });

        Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                startTimer();
                music.start();
                MenuDisplay.setVisibility(View.GONE);
                conclick.setVisibility(View.GONE);
            }
        });

        conclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                startTimer();
                music.start();
                MenuDisplay.setVisibility(View.GONE);
                conclick.setVisibility(View.GONE);
                sureaskreset.setVisibility(View.GONE);
                sureask.setVisibility(View.GONE);
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                sureask.setVisibility(View.VISIBLE);
            }
        });

        oms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                ActivityHelper.openNewActivity(FoodGame.this, FoodMenu.class);
            }
        });

        ihh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                sureask.setVisibility(View.GONE);
            }
        });

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                optionValue1 = 1;
                optionValue2 = 2;
                optionValue3 = 3;
                updateOptions();
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                optionValue1 = 4;
                optionValue2 = 5;
                optionValue3 = 6;
                updateOptions();
            }
        });

        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                optionValue1 = 7;
                optionValue2 = 8;
                optionValue3 = 9;
                updateOptions();
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                if (optionValue1 != 0 && optionValue2 != 0 && optionValue3 != 0) {
                    updateOrder(optionValue1);
                    count++;
                } else {
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                if (optionValue1 != 0 && optionValue2 != 0 && optionValue3 != 0) {
                    updateOrder(optionValue2);
                    count++;
                } else {
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound();
                if (optionValue1 != 0 && optionValue2 != 0 && optionValue3 != 0) {
                    updateOrder(optionValue3);
                    count++;
                } else {
                }
            }
        });


        serveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < orderValues.length; i++) {
                    if (orderValues[i] == shuffledValues.get(0) || orderValues[i] == shuffledValues.get(1) || orderValues[i] == shuffledValues.get(2)) {
                        if (img1.getVisibility() == View.VISIBLE && orderValues[i] == shuffledValues.get(0)) {
                            score += 30;
                        } else if (img2.getVisibility() == View.VISIBLE && orderValues[i] == shuffledValues.get(1)) {
                            score += 30;
                        } else if (img3.getVisibility() == View.VISIBLE && orderValues[i] == shuffledValues.get(2)) {
                            score += 30;
                        }
                    }
                }
                scoreText.setText("Score: " + score);
                reset();
                kaching.start();

                if(mTimeLeftInMillis >= 60000 && mTimeLeftInMillis < 90000){
                    img2.setImageResource(imageResources[shuffledValues.get(1) - 1]);
                    img1.setVisibility(View.VISIBLE);
                    img1.setImageResource(imageResources[shuffledValues.get(0) - 1]);
                    img3.setVisibility(View.INVISIBLE);
                }

                if(mTimeLeftInMillis < 60000){
                    img2.setImageResource(imageResources[shuffledValues.get(1) - 1]);
                    img1.setVisibility(View.VISIBLE);
                    img1.setImageResource(imageResources[shuffledValues.get(0) - 1]);
                    img3.setVisibility(View.VISIBLE);
                    img3.setImageResource(imageResources[shuffledValues.get(2) - 1]);
                }
            }
        });

        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderValues = new int[3];
                order1.setVisibility(View.INVISIBLE);
                order2.setVisibility(View.INVISIBLE);
                order3.setVisibility(View.INVISIBLE);
                count = 0;
                trashm.start();
            }
        });

        orderlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDragAndDrop(null, shadowBuilder, v, 0);
                    }
                }, 0);
                return true;
            }
        });

        serveButton.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:

                        for (int i = 0; i < orderValues.length; i++) {
                            if (orderValues[i] == shuffledValues.get(0) || orderValues[i] == shuffledValues.get(1) || orderValues[i] == shuffledValues.get(2)) {
                                if (img1.getVisibility() == View.VISIBLE && orderValues[i] == shuffledValues.get(0)) {
                                    score += 30;
                                } else if (img2.getVisibility() == View.VISIBLE && orderValues[i] == shuffledValues.get(1)) {
                                    score += 30;
                                } else if (img3.getVisibility() == View.VISIBLE && orderValues[i] == shuffledValues.get(2)) {
                                    score += 30;
                                }
                            }
                        }
                        scoreText.setText("Score: " + score);
                        reset();
                        kaching.start();


                        if(mTimeLeftInMillis >= 60000 && mTimeLeftInMillis < 90000){
                            img2.setImageResource(imageResources[shuffledValues.get(1) - 1]);
                            img1.setVisibility(View.VISIBLE);
                            img1.setImageResource(imageResources[shuffledValues.get(0) - 1]);
                            img3.setVisibility(View.INVISIBLE);
                        }

                        if(mTimeLeftInMillis < 60000){
                            img2.setImageResource(imageResources[shuffledValues.get(1) - 1]);
                            img1.setVisibility(View.VISIBLE);
                            img1.setImageResource(imageResources[shuffledValues.get(0) - 1]);
                            img3.setVisibility(View.VISIBLE);
                            img3.setImageResource(imageResources[shuffledValues.get(2) - 1]);
                        }
                }
                return true;
            }
        });

        trash.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DROP:
                        orderValues = new int[3];
                        order1.setVisibility(View.INVISIBLE);
                        order2.setVisibility(View.INVISIBLE);
                        order3.setVisibility(View.INVISIBLE);
                        count = 0;
                        trashm.start();
                }
                return true;
            }
        });
    }

    private int[] imageResources = {
            R.drawable.popcornbbq, R.drawable.popcorncheese, R.drawable.popcornsourcream,
            R.drawable.ccblue, R.drawable.ccpink, R.drawable.ccpurple,
            R.drawable.drinkcherry, R.drawable.drinkgrapes, R.drawable.drinkorange
    };

    private int[] imageResourcesChoice = {
            R.drawable.fpcbbq, R.drawable.fpccheese, R.drawable.fpcsourcream,
            R.drawable.fccblue, R.drawable.fccpink, R.drawable.fccpurple,
            R.drawable.fdrcherry, R.drawable.fdrgrapes, R.drawable.fdrorange
    };

    private int[] imageResourcesCustomers = {
            R.drawable.customerirish, R.drawable.customerkarl,R.drawable.customercilla,R.drawable.customerduday,
            R.drawable.customerkurt,R.drawable.customergab,R.drawable.customercelestine,R.drawable.customerelle,
            R.drawable.customerchok
    };

    private void randomizeImageViews() {
        shuffledValues = new ArrayList<>();
        for (int value : imageValues) {
            shuffledValues.add(value);
        }
        Collections.shuffle(shuffledValues);
        img2.setImageResource(imageResources[shuffledValues.get(1) - 1]);
        img1.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
    }

    private void randomizeCustomer() {
        Random random = new Random();
        int randomIndex = random.nextInt(imageResourcesCustomers.length);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.animation);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                customer.setImageResource(imageResourcesCustomers[randomIndex]);
                customer.startAnimation(AnimationUtils.loadAnimation(FoodGame.this, android.R.anim.fade_in));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        customer.startAnimation(fadeOut);
    }



    private void updateOptions() {
        option1.setImageResource(imageResourcesChoice[optionValue1 - 1]);
        option2.setImageResource(imageResourcesChoice[optionValue2 - 1]);
        option3.setImageResource(imageResourcesChoice[optionValue3 - 1]);
    }

    private void updateOrder(int value) {
        nextOrderIndex = getNextOrderIndex();
        if (nextOrderIndex != -1) {
            if (mTimeLeftInMillis < 60000 && count < 3) {
                orderValues[nextOrderIndex] = value;
                switch (nextOrderIndex) {
                    case 0:
                        order1.setImageResource(imageResources[value - 1]);
                        order1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        order2.setImageResource(imageResources[value - 1]);
                        order2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        order3.setImageResource(imageResources[value - 1]);
                        order3.setVisibility(View.VISIBLE);
                        break;
                }
            } else if (mTimeLeftInMillis < 90000 && count < 2) {
                orderValues[nextOrderIndex] = value;
                switch (nextOrderIndex) {
                    case 0:
                        order1.setImageResource(imageResources[value - 1]);
                        order1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        order2.setImageResource(imageResources[value - 1]);
                        order2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        order3.setImageResource(imageResources[value - 1]);
                        order3.setVisibility(View.VISIBLE);
                        break;
                }
            } else if (mTimeLeftInMillis < 120000 && count < 1) {
                orderValues[nextOrderIndex] = value;
                switch (nextOrderIndex) {
                    case 0:
                        order1.setImageResource(imageResources[value - 1]);
                        order1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        order2.setImageResource(imageResources[value - 1]);
                        order2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        order3.setImageResource(imageResources[value - 1]);
                        order3.setVisibility(View.VISIBLE);
                        break;
                }
            } else {
                Toast.makeText(FoodGame.this, "No available slot for order.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private int getNextOrderIndex() {
        for (int i = 0; i < orderValues.length; i++) {
            if (orderValues[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private void reset() {
        orderValues = new int[3];
        randomizeImageViews();
        order1.setVisibility(View.INVISIBLE);
        order2.setVisibility(View.INVISIBLE);
        order3.setVisibility(View.INVISIBLE);
        randomizeCustomer();
        count = 0;
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("LastScore", score);
                editor.apply();

                updateTotalPoints(score);

                ActivityHelper.openNewActivity(FoodGame.this, FoodEnd.class);

            }


        }.start();
        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();

    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        if (mTimeLeftInMillis <= 10000) {
            timer.setTextColor(Color.parseColor("#AD3726"));
        }

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }
    private void toPause(){
        MenuDisplay.setVisibility(View.VISIBLE);
        conclick.setVisibility(View.VISIBLE);
        pauseTimer();
        if (music != null && music.isPlaying()) {
            music.pause();
        }
    }

    private void buttonsound(){

        buttonclicked.start();
    }

    private void stopMusic() {
        if (music != null) {
            // Release the resources associated with the MediaPlayer
            music.release();
            // Set MediaPlayer to null to indicate it's no longer in use
            music = null;
        }
    }

    private void updateTotalPoints(int pointsEarned) {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        int totalPoints = sharedPreferences.getInt("TotalPoints", 40);

        totalPoints += pointsEarned;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TotalPoints", totalPoints);
        editor.apply();
    }


    @Override
    protected void onStop() {
        super.onStop();
        toPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMusic();
    }
    int j = 1;
    public void onBackPressed() {
        if (j == 1) {
            j++;
            Toast.makeText(this, "Press the back button again to exit.", Toast.LENGTH_SHORT).show();
        }
        else if (j == 2) {
            ActivityHelper.openNewActivity(FoodGame.this, FoodMenu.class);
        }
    }
}