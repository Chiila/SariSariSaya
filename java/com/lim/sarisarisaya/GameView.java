package com.lim.sarisarisaya;
import static com.lim.sarisarisaya.AppConstants.getContext;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    GameThread gameThread;
    private MediaPlayer jumpMusic;
    public GameView(Context context) {
        super(context);
        InitView();
        jumpMusic = MediaPlayer.create(this.getContext(), R.raw.jump);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (!gameThread.isRunning()) {
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (gameThread.isRunning()) {
            gameThread.setRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }
    }
    void InitView() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float touchX = event.getX();
        float touchY = event.getY();
        AppConstants.getGameEngine().isWalletVisible = false;
        if (action == MotionEvent.ACTION_DOWN) {
            if (AppConstants.getGameEngine().gameState == 0) {
                AppConstants.getGameEngine().gameState = 1;
                AppConstants.getGameEngine().manok.setVelocityY(AppConstants.VELOCITY_WHEN_JUMPED);
                jumpMusic.start();
                AppConstants.getGameEngine().touchDetected = true;
            } else if (AppConstants.getGameEngine().gameState == 1) {
// Handle bird jump if it's on the ground
                if (AppConstants.getGameEngine().manok.getY() >= (AppConstants.getGameEngine().groundLevel - AppConstants.getBitmapBank().getBirdHeight())) {
                    AppConstants.getGameEngine().manok.setVelocityY(AppConstants.VELOCITY_WHEN_JUMPED);
                    jumpMusic.start();
                    AppConstants.getGameEngine().touchDetected = true;
                }
            } else if (AppConstants.getGameEngine().gameState == 2) {
                if (AppConstants.getGameEngine().playAgainButtonArea.contains((int) touchX, (int) touchY)) {
                    AppConstants.getGameEngine().saveTotalScores(AppConstants.getGameEngine().totalScores - 50); // DINAGDAG KO PRES
                    AppConstants.getGameEngine().restartGame();
                }
                if (AppConstants.getGameEngine().returnToMenuButtonArea.contains((int) touchX, (int) touchY)) {
                    AppConstants.getGameEngine().whilePlayingMusic.stop();
                    AppConstants.getGameEngine().gameState = 0;
                    Intent intent = new Intent(getContext(), CircusMenu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    getContext().startActivity(intent);
                }
            } else if (AppConstants.getGameEngine().gameState == 3) {
                AppConstants.getGameEngine().gameState = 1;
                AppConstants.getGameEngine().whilePlayingMusic.start();
                AppConstants.getGameEngine().updateGameSpeed();
            }
        }
        return true;
    }
}
