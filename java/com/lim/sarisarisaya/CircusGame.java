package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class CircusGame extends AppCompatActivity {
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circus_game);

        AppConstants.setContext(getApplicationContext());
        gameView = new GameView(this);
        setContentView(gameView);
        AppConstants.getGameEngine().whilePlayingMusic.start();

        int score = AppConstants.getGameEngine().score;

    }

    @Override
    public void onBackPressed() {
        AppConstants.getGameEngine().whilePlayingMusic.pause();
        AppConstants.getGameEngine().gameState = 3;
    }
}