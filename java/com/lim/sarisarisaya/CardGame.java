package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class CardGame extends AppCompatActivity {
    ConstraintLayout presser, fortunelayout, gomenu;
    ImageView restartgo, homego;
    TextView fscore1, fscore2;
    TextView fortunetext;
    TextView player1, player2, cashadd;
    ImageView CL_11, CL_12, CL_13, CL_14, CL_21, CL_22, CL_23, CL_24, CL_31, CL_32, CL_33, CL_34;

    //For Pause
    ConstraintLayout pausemenu;
    ImageView pause, resume, reset, home;
    ImageView ticketyes, ticketno;
    ConstraintLayout ticketlayout, addcash, scoreLayout;
    //For sounds
    ImageView sounds;
    View includer;


    //array for the images
    Integer[] Deck = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    private MediaPlayer bgMusic, voiceOver;
    private BackGroundMusic backGroundMusic;

    //actual images
    int Oracle_101, Destiny_102, Phoenix_103, Celestial_104, Enigma_105, Radiance_106,
            Oracle_201, Destiny_202, Phoenix_203, Celestial_204, Enigma_205, Radiance_206;

    int Card1, Card2;
    int clicked_1, clicked_2;
    int cardNumber = 1;

    int turn = 1;
    int P1_points = 0, P2_points = 0;

    boolean endGame = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        playMusic();

        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);
        // Initialize ImageViews
        CL_11 = (ImageView) findViewById(R.id.CL_11);
        CL_12 = (ImageView) findViewById(R.id.CL_12);
        CL_13 = (ImageView) findViewById(R.id.CL_13);
        CL_14 = (ImageView) findViewById(R.id.CL_14);
        CL_21 = (ImageView) findViewById(R.id.CL_21);
        CL_22 = (ImageView) findViewById(R.id.CL_22);
        CL_23 = (ImageView) findViewById(R.id.CL_23);
        CL_24 = (ImageView) findViewById(R.id.CL_24);
        CL_31 = (ImageView) findViewById(R.id.CL_31);
        CL_32 = (ImageView) findViewById(R.id.CL_32);
        CL_33 = (ImageView) findViewById(R.id.CL_33);
        CL_34 = (ImageView) findViewById(R.id.CL_34);
        //initialize fortune thingy
        presser = findViewById(R.id.presser);
        fortunelayout = findViewById(R.id.fortunelayout);
        fortunetext = findViewById(R.id.fortunetext);

        //initialize pause
        pausemenu = findViewById(R.id.pausemenu);
        pause = findViewById(R.id.pause);
        resume = findViewById(R.id.resume);
        reset = findViewById(R.id.reset);
        home = findViewById(R.id.home);

        //initializegame over
        gomenu = findViewById(R.id.gomenu);
        restartgo = findViewById(R.id.restartgo);
        homego= findViewById(R.id.homego);
        fscore1 = findViewById(R.id.fscore1);
        fscore2 = findViewById(R.id.fscore2);

        ticketyes = findViewById(R.id.ticketyes);
        ticketno = findViewById(R.id.ticketno);
        ticketlayout = findViewById(R.id.ticketlayout);
        includer = findViewById(R.id.includer);

        cashadd = findViewById(R.id.cashadd);
        addcash = findViewById(R.id.addcash);

        scoreLayout = findViewById(R.id.scoreLayout);

        // Set tags for ImageViews
        CL_11.setTag("0");
        CL_12.setTag("1");
        CL_13.setTag("2");
        CL_14.setTag("3");
        CL_21.setTag("4");
        CL_22.setTag("5");
        CL_23.setTag("6");
        CL_24.setTag("7");
        CL_31.setTag("8");
        CL_32.setTag("9");
        CL_33.setTag("10");
        CL_34.setTag("11");

        //load the card images
        frontOfCardsResources();

        //shuffle the images
        Collections.shuffle(Arrays.asList(Deck));

        //changing the color of the second player (inactive)
        player2.setTextColor(Color.GRAY);

        CL_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_11, theCard);
            }
        });
        CL_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_12, theCard);
            }
        });
        CL_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_13, theCard);
            }
        });
        CL_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_14, theCard);
            }
        });
        CL_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_21, theCard);
            }
        });
        CL_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_22, theCard);
            }
        });
        CL_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_23, theCard);
            }
        });
        CL_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_24, theCard);
            }
        });
        CL_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_31, theCard);
            }
        });
        CL_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_32, theCard);
            }
        });
        CL_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_33, theCard);
            }
        });
        CL_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                doStuff (CL_34, theCard);
            }
        });
        //pag pinindot matatanggal
        presser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presser.setVisibility(View.GONE);
                fortunelayout.setVisibility(View.GONE);

                if (endGame && fortunelayout.getVisibility() == View.GONE) {
                    addcash.setVisibility(View.VISIBLE);
                    scoreLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
        // open the pause
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                includer.setVisibility(View.GONE);
                pausemenu.setVisibility(View.VISIBLE);
            }
        });

        //go back to game
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                includer.setVisibility(View.VISIBLE);
                pausemenu.setVisibility(View.GONE);
            }
        });

        // restart the game
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.VISIBLE);
                pausemenu.setVisibility(View.GONE);
            }
        });

        ticketyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalPointsManager.updateTotalPoints(getApplicationContext());
                ActivityHelper.openNewActivity(CardGame.this, CardGame.class);
                pausemenu.setVisibility(View.GONE);
            }
        });

        ticketno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ticketlayout.setVisibility(View.GONE);
                pausemenu.setVisibility(View.VISIBLE);
            }
        });

        //gotohomepage
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.openNewActivity(CardGame.this, CardMenu.class);
            }
        });

        restartgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalPointsManager.updateTotalPoints(getApplicationContext());
                ActivityHelper.openNewActivity(CardGame.this, CardGame.class);
            }
        });

        homego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityHelper.openNewActivity(CardGame.this, CardMenu.class);
            }
        });

        addcash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gomenu.setVisibility(View.VISIBLE);
                fscore1.setText("Player 1: " + P1_points);
                fscore2.setText("Player 2: " + P2_points);
                addcash.setVisibility(View.GONE);
            }
        });
    }

    private void doStuff (ImageView iv, int card){
        //set the correct image to the imageview
        if(Deck [card] == 101){
            iv.setImageResource(Oracle_101);
        }else if (Deck [card] == 102){
            iv.setImageResource(Destiny_102);
        }else if (Deck [card] == 103){
            iv.setImageResource(Phoenix_103);
        }else if (Deck [card] == 104){
            iv.setImageResource(Celestial_104);
        }else if (Deck [card] == 105){
            iv.setImageResource(Enigma_105);
        }else if (Deck [card] == 106){
            iv.setImageResource(Radiance_106);
        }else if (Deck [card] == 201){
            iv.setImageResource(Oracle_201);
        }else if (Deck [card] == 202){
            iv.setImageResource(Destiny_202);
        }else if (Deck [card] == 203){
            iv.setImageResource(Phoenix_203);
        }else if (Deck [card] == 204){
            iv.setImageResource(Celestial_204);
        }else if (Deck [card] == 205){
            iv.setImageResource(Enigma_205);
        }else if (Deck [card] == 206){
            iv.setImageResource(Radiance_206);
        }

        //check which image is selected and save it to temporary variable
        if(cardNumber == 1){
            Card1 = Deck[card];
            if(Card1 > 200){
                Card1 = Card1 - 100;
            }
            cardNumber = 2;
            clicked_1 = card;

            iv.setEnabled(false);
        }else if (cardNumber == 2){
            Card2 = Deck[card];
            if(Card2 > 200){
                Card2 = Card2 - 100;
            }
            cardNumber = 1;
            clicked_2 = card;

            CL_11.setEnabled(false);
            CL_12.setEnabled(false);
            CL_13.setEnabled(false);
            CL_14.setEnabled(false);
            CL_21.setEnabled(false);
            CL_22.setEnabled(false);
            CL_23.setEnabled(false);
            CL_24.setEnabled(false);
            CL_31.setEnabled(false);
            CL_32.setEnabled(false);
            CL_33.setEnabled(false);
            CL_34.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if the selected images are equal
                    calculate();
                }
            }, 1000);
        }
    }

    private String calculate() {
        //if images are equal remove them and add point
        if (Card1 == Card2) {
            generateRandomMessage();
            presser.setVisibility(View.VISIBLE);
            fortunelayout.setVisibility(View.VISIBLE);

            if (clicked_1 == 0) {
                CL_11.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 1) {
                CL_12.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 2) {
                CL_13.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 3) {
                CL_14.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 4) {
                CL_21.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 5) {
                CL_22.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 6) {
                CL_23.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 7) {
                CL_24.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 8) {
                CL_31.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 9) {
                CL_32.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 10) {
                CL_33.setVisibility(View.INVISIBLE);
            } else if (clicked_1 == 11) {
                CL_34.setVisibility(View.INVISIBLE);
            }

            if (clicked_2 == 0) {
                CL_11.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 1) {
                CL_12.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 2) {
                CL_13.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 3) {
                CL_14.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 4) {
                CL_21.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 5) {
                CL_22.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 6) {
                CL_23.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 7) {
                CL_24.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 8) {
                CL_31.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 9) {
                CL_32.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 10) {
                CL_33.setVisibility(View.INVISIBLE);
            } else if (clicked_2 == 11) {
                CL_34.setVisibility(View.INVISIBLE);
            }

            //add points to the correct player
            if (turn == 1) {
                P1_points++;
                player1.setText("Player 1: " + P1_points);
            } else if (turn == 2) {
                P2_points++;
                player2.setText("Player 2: " + P2_points);
            }

        } else {
            CL_11.setImageResource(R.drawable.card_back);
            CL_12.setImageResource(R.drawable.card_back);
            CL_13.setImageResource(R.drawable.card_back);
            CL_14.setImageResource(R.drawable.card_back);
            CL_21.setImageResource(R.drawable.card_back);
            CL_22.setImageResource(R.drawable.card_back);
            CL_23.setImageResource(R.drawable.card_back);
            CL_24.setImageResource(R.drawable.card_back);
            CL_31.setImageResource(R.drawable.card_back);
            CL_32.setImageResource(R.drawable.card_back);
            CL_33.setImageResource(R.drawable.card_back);
            CL_34.setImageResource(R.drawable.card_back);

            //change the player turn
            if (turn == 1) {
                turn = 2;
                player1.setTextColor(Color.GRAY);
                player2.setTextColor(Color.parseColor("#673AB7"));
            } else if (turn == 2) {
                turn = 1;
                player2.setTextColor(Color.GRAY);
                player1.setTextColor(Color.parseColor("#673AB7"));
            }
        }

        CL_11.setEnabled(true);
        CL_12.setEnabled(true);
        CL_13.setEnabled(true);
        CL_14.setEnabled(true);
        CL_21.setEnabled(true);
        CL_22.setEnabled(true);
        CL_23.setEnabled(true);
        CL_24.setEnabled(true);
        CL_31.setEnabled(true);
        CL_32.setEnabled(true);
        CL_33.setEnabled(true);
        CL_34.setEnabled(true);

        //check if the game is over
        checkEnd();

        return null;
    }

    private void generateRandomMessage() {
        String[] messages = {
                "Your path is full of happiness and good things!",
                "Believe in your dreams, they're like magic guiding you!",
                "Guess what? Lucky surprises are coming your way!",
                "Love is all around you, like a big hug from the universe!",
                "You're surrounded by lots of good stuff and cozy feelings!",
                "You're super brave, like a superhero facing challenges!",
                "Your imagination is a treasure chest full of wonderful ideas!",
        };
        int[] audio = {R.raw.audio1, R.raw.audio2, R.raw.audio3, R.raw.audio4, R.raw.audio5, R.raw.audio6, R.raw.audio7};
        int randomIndex = (int) (Math.random() * messages.length);

        fortunetext.setText(messages[randomIndex]);

        voiceOver = MediaPlayer.create(CardGame.this, audio[randomIndex]);
        voiceOver.start();
    }

    private void checkEnd(){
        if(CL_11.getVisibility() == View.INVISIBLE &&
                CL_12.getVisibility() == View.INVISIBLE &&
                CL_13.getVisibility() == View.INVISIBLE &&
                CL_14.getVisibility() == View.INVISIBLE &&
                CL_21.getVisibility() == View.INVISIBLE &&
                CL_22.getVisibility() == View.INVISIBLE &&
                CL_23.getVisibility() == View.INVISIBLE &&
                CL_24.getVisibility() == View.INVISIBLE &&
                CL_31.getVisibility() == View.INVISIBLE &&
                CL_32.getVisibility() == View.INVISIBLE &&
                CL_33.getVisibility() == View.INVISIBLE &&
                CL_34.getVisibility() == View.INVISIBLE) {

            endGame = true;
            fscore1.setText("Player 1: " + P1_points);
            fscore2.setText("Player 2: " + P2_points);

            if (P1_points > P2_points) {
                TotalPointsManager.updateTotalPoints(getApplicationContext(), 60);
                cashadd.setText(" " + 60 + " ");
            }
            else if (P1_points < P2_points) {
                TotalPointsManager.updateTotalPoints(getApplicationContext(), 60);
                cashadd.setText(" " + 60 + " ");
            }
            else {
                TotalPointsManager.updateTotalPoints(getApplicationContext(), 60);
                cashadd.setText(" " + 60 + " ");
            }
        }
    }
    private void frontOfCardsResources(){
        Oracle_101 = R.drawable.oracle101;
        Destiny_102 = R.drawable.destiny102;
        Phoenix_103 = R.drawable.phoenix103;
        Celestial_104 = R.drawable.celestial104;
        Enigma_105 = R.drawable.enigma105;
        Radiance_106 = R.drawable.radiance106;
        Oracle_201 = R.drawable.oracle201;
        Destiny_202 = R.drawable.destiny202;
        Phoenix_203 = R.drawable.phoenix203;
        Celestial_204 = R.drawable.celestial204;
        Enigma_205 = R.drawable.enigma205;
        Radiance_206 = R.drawable.radiance206;
    }

    private void playMusic() {
        backGroundMusic = new BackGroundMusic(bgMusic);
        backGroundMusic.playMusic(CardGame.this, R.raw.mystic_bgm);
    }

    int j = 1;
    public void onBackPressed() {
        if (j == 1) {
            j++;
            Toast.makeText(this, "Press the back button again to exit.", Toast.LENGTH_SHORT).show();
        }
        else if (j == 2) {
            ActivityHelper.openNewActivity(CardGame.this, CardMenu.class);
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
        backGroundMusic.stopMusic();
    }
}