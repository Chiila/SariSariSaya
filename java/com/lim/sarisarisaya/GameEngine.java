package com.lim.sarisarisaya;

import static com.lim.sarisarisaya.AppConstants.getContext;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import androidx.core.content.res.ResourcesCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class GameEngine {
    BackgroundImage backgroundImage;
    Manok manok;
    int groundLevel;
    public Rect playAgainButtonArea;
    public Rect returnToMenuButtonArea;
    static int gameState;
    private List<Torch> torchList;
    private int torchSpawnInterval;
    private long lastTorchSpawn = 0;
    public int score;
    private Paint scorePaint, scorePaint2;
    public Rect playAgainButtonRect;
    public Rect returnToMenuButtonRect;
    private Paint buttonPaint;
    private Paint buttonTextPaint;
    public int bgvelocityHolder;
    public int highscore;
    public boolean touchDetected = false;
    public MediaPlayer whilePlayingMusic;
    public MediaPlayer deadMusic;
    public int totalScores = 0;
    public GameEngine() {
        backgroundImage = new BackgroundImage();
        manok = new Manok();
// Set initial position of the bird to the left side of the screen
        manok.setX(AppConstants.SCREEN_WIDTH / 10);

// 0 = not started | 1 = playing | 2 = gameover | 3 = pause
        gameState = 0;
        groundLevel = AppConstants.SCREEN_HEIGHT - 265; // Adjust ground level here
        manok.setY(groundLevel - AppConstants.getBitmapBank().getBirdHeight()); // Set bird's initial position to ground level
        torchList = new ArrayList<>();
        scorePaint = new Paint();
        scorePaint.setColor(Color.parseColor("#FFFFFF"));
        scorePaint.setTextSize(40);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        score = 0;
        highscore = 0;
        int buttonWidth = 300;
        int buttonHeight = 100;
        int buttonY = AppConstants.SCREEN_HEIGHT / 2 + 200;
        playAgainButtonRect = new Rect(AppConstants.SCREEN_WIDTH / 2 - buttonWidth, buttonY, AppConstants.SCREEN_WIDTH / 2, buttonY + buttonHeight);
        returnToMenuButtonRect = new Rect(AppConstants.SCREEN_WIDTH / 2, buttonY, AppConstants.SCREEN_WIDTH / 2 + buttonWidth, buttonY + buttonHeight);
        buttonPaint = new Paint();
        buttonPaint.setColor(Color.LTGRAY);
        buttonPaint.setStyle(Paint.Style.FILL);
        buttonTextPaint = new Paint();
        buttonTextPaint.setColor(Color.BLACK);
        buttonTextPaint.setTextSize(30);
        buttonTextPaint.setTextAlign(Paint.Align.CENTER);
        whilePlayingMusic = MediaPlayer.create(AppConstants.getContext(), R.raw.while_playing);
        whilePlayingMusic.setLooping(true);
        deadMusic = MediaPlayer.create(AppConstants.getContext(), R.raw.dead);
    }

    public boolean isCollision(Manok manok, Torch torch) {
// Get bounding boxes of the rooster and torch
        Rect roosterRect = new Rect(
                manok.getX(),
                manok.getY(),
                manok.getX() + AppConstants.getBitmapBank().getBirdWidth(),
                manok.getY() + AppConstants.getBitmapBank().getBirdHeight()
        );
        Rect torchRect = new Rect(
                torch.getX(),
                torch.getY(),
                torch.getX() + AppConstants.getBitmapBank().getTorch().getWidth(),
                torch.getY() + AppConstants.getBitmapBank().getTorch().getHeight()
        );
// Check if the bounding boxes intersect
        return Rect.intersects(roosterRect, torchRect);
    }
    public void updateAndDrawableBackgroundImage(Canvas canvas) {
        Typeface customTypeface = ResourcesCompat.getFont(getContext(), R.font.mario);
        scorePaint.setTypeface(customTypeface);
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground_game(), backgroundImage.getX(),
                backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground_game(), backgroundImage.getX() +
                    AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
        if (gameState == 2) {
//gameover
            int gameOverX = AppConstants.SCREEN_WIDTH / 2 - AppConstants.getBitmapBank().gameOverImage.getWidth() / 2;
            int gameOverY = AppConstants.SCREEN_HEIGHT / 5 - AppConstants.getBitmapBank().gameOverImage.getHeight() / 2;
            int playAgainX = AppConstants.SCREEN_WIDTH / 2 - AppConstants.getBitmapBank().playAgainImage.getWidth() / 2;
            int returnToMenuX = AppConstants.SCREEN_WIDTH / 2 - AppConstants.getBitmapBank().returnToMenuImage.getWidth() / 2;
            int buttonsY = AppConstants.SCREEN_HEIGHT / 3; // Adjust this value as needed
            playAgainButtonArea = new Rect(playAgainX, buttonsY, playAgainX + AppConstants.getBitmapBank().playAgainImage.getWidth(), buttonsY + AppConstants.getBitmapBank().playAgainImage.getHeight());
            returnToMenuButtonArea = new Rect(returnToMenuX, buttonsY + AppConstants.getBitmapBank().playAgainImage.getHeight() + 20, returnToMenuX + AppConstants.getBitmapBank().returnToMenuImage.getWidth(), buttonsY + AppConstants.getBitmapBank().playAgainImage.getHeight() + 20 + AppConstants.getBitmapBank().returnToMenuImage.getHeight());
            canvas.drawBitmap(AppConstants.getBitmapBank().playAgainImage, playAgainX, buttonsY, null);
            canvas.drawBitmap(AppConstants.getBitmapBank().returnToMenuImage, returnToMenuX, buttonsY + AppConstants.getBitmapBank().playAgainImage.getHeight() + 20, null);
            canvas.drawBitmap(AppConstants.getBitmapBank().gameOverImage, gameOverX, gameOverY, null);
        }
        if (gameState != 2) {
            if (gameState != 3) {
                updateGameSpeed();
            }
// Move the background only if the game is not over or paused
            backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());
            if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
                backgroundImage.setX(0);
            }
// Draw the background image
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground_game(), backgroundImage.getX(), backgroundImage.getY(), null);
            if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
                canvas.drawBitmap(AppConstants.getBitmapBank().getBackground_game(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
            }
// Draw ground
            Paint paint = new Paint();
            paint.setColor(Color.TRANSPARENT); // Set the color to transparent (invisible ground)
            canvas.drawRect(0, groundLevel, AppConstants.SCREEN_WIDTH, AppConstants.SCREEN_HEIGHT, paint);
            spawnTorch();
            updateAndDrawTorches(canvas);
            Bitmap scoreBoxBitmap = AppConstants.getBitmapBank().getScoreBox();
            Bitmap highestScoreBoxBitmap = AppConstants.getBitmapBank().getHighestScoreBox();
//adjust score img positions here
            float scoreX = 20;
            float scoreY = 60;
            float highestScoreX = canvas.getWidth() - 390;
            float highestScoreY = 60;
//adjust score text positions here
            float scoreTextX;
            if (score >= 99) {
                scoreTextX = scoreX + 198;
            } else {
                scoreTextX = scoreX + 220;
            }
            float scoreTextY = scoreY + 50;
            float highestScoreTextX;
            if(highscore >= 99){
                highestScoreTextX = highestScoreX + 265;
            }else{
                highestScoreTextX = highestScoreX + 290;
            }
            float highestScoreTextY = highestScoreY + 50;
            canvas.drawBitmap(scoreBoxBitmap, scoreX, scoreY, null);
            canvas.drawBitmap(highestScoreBoxBitmap, highestScoreX, highestScoreY, null);
            canvas.drawText("" + score, scoreTextX, scoreTextY, scorePaint);
            canvas.drawText("" + highscore, highestScoreTextX, highestScoreTextY, scorePaint);
            if (gameState == 3) {
                bgvelocityHolder = backgroundImage.getVelocity();
                int canvasWidth = canvas.getWidth();
                int canvasHeight = canvas.getHeight();
                float textWidth = scorePaint.measureText("GAME PAUSED");
                float textHeight = scorePaint.getTextSize();
                float density = Resources.getSystem().getDisplayMetrics().density;
                float centerX = (canvasWidth - textWidth) / 2;
                float centerY = (canvasHeight + textHeight) / 2 - 30 * density;
                canvas.drawText("GAME PAUSED", centerX, centerY, scorePaint);
                backgroundImage.setVelocity(0);
                manok.setVelocityX(0);
            }
        }
    }
    public boolean isWalletVisible = false;
    public int walletState = 0; //0 = not visible pa 1 = visible na
    public void updateAndDrawWallet(Canvas canvas) {
        if (isWalletVisible) {
// Draw Wallet image to center
            Bitmap walletBitmap = AppConstants.getBitmapBank().getWallet();
            int walletWidth = walletBitmap.getWidth();
            int walletHeight = walletBitmap.getHeight();
            int walletX = (AppConstants.SCREEN_WIDTH - walletWidth) / 2; // Center horizontally
            int walletY = (AppConstants.SCREEN_HEIGHT - walletHeight) / 3; // Center vertically, adjust as needed
            canvas.drawBitmap(walletBitmap, walletX, walletY, null);
// Draw Text Score
            int pera = score;
            String totalPoints = Integer.toString(pera);
            Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setColor(Color.parseColor("#000000"));
            textPaint.setTextSize(70);
            textPaint.setTextAlign(Paint.Align.CENTER);
            Typeface customTypeface = ResourcesCompat.getFont(getContext(), R.font.mario);
            textPaint.setTypeface(customTypeface);
// Calculate the center of the canvas
            int canvasCenterX = canvas.getWidth() / 2; // Center horizontally
            int canvasCenterY = canvas.getHeight() / 2; // Center vertically
// Determine the text to draw and calculate its bounds
            String totalPointsText = "PHP: " + totalPoints;
            Rect textBounds = new Rect();
            textPaint.getTextBounds(totalPointsText, 0, totalPointsText.length(), textBounds);
// Pang minor Adjustment para di sobra
            float pangAdjust = 1.1f; // Ito yung pang adjust mo ng PHP Text
            int adjustedY = canvasCenterY - (int)(textBounds.height() * pangAdjust);
            int textX = canvasCenterX; // This centers it horizontally
            int textY = adjustedY + textBounds.height() / 2; // This centers it vertically within the bounds and applies the adjustment

            canvas.drawText(totalPointsText, textX, textY, textPaint);
        }
    }
    public void updateAndDrawManok(Canvas canvas) {
        if (gameState == 1) {
// Update vertical position
            if (manok.getY() < (groundLevel - AppConstants.getBitmapBank().getBirdHeight()) || manok.getVelocityY() < 0) {
                if (manok.getY() + manok.getVelocityY() > 0 && manok.getY() + manok.getVelocityY() < groundLevel) {
                    manok.setVelocityY(manok.getVelocityY() + AppConstants.gravity);
                    manok.setY(manok.getY() + manok.getVelocityY());
                } else {
                    manok.setY(groundLevel - AppConstants.getBitmapBank().getBirdHeight());
                    manok.setVelocityY(0);
                }
            } else {
                manok.setY(groundLevel - AppConstants.getBitmapBank().getBirdHeight());
                manok.setVelocityY(0);
            }
// Check if bird reaches edge of screen
            if (manok.getX() < 0 || manok.getX() > AppConstants.SCREEN_WIDTH - AppConstants.getBitmapBank().getBirdWidth()) {
// Reset horizontal velocity to zero
                manok.setVelocityX(0);
            }
        }
        if (touchDetected) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBird(0), manok.getX(), manok.getY(), null);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500); // Sleep for 2 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    touchDetected = false;
                }
            }).start();
        }
        int currentFrame = manok.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), manok.getX(), manok.getY(), null);
        currentFrame++;
        if (currentFrame > manok.maxFrame) {
            currentFrame = 0;
        }
        manok.setCurrentFrame(currentFrame);
    }
    private boolean isSpawningTorch = false;
    private void spawnTorch() {
        if (gameState != 3) { // Check if the game is not paused
            long currentTime = System.currentTimeMillis();
            if (!isSpawningTorch && currentTime - lastTorchSpawn > torchSpawnInterval) {
                isSpawningTorch = true;
// Generate a random interval for the next torch spawn
                Random random = new Random();
                torchSpawnInterval = 1300 + random.nextInt(3000); // Random interval between 1 to 5 seconds
// Calculate the minimum X position for the new torch to avoid overlap
                int minTorchX = AppConstants.SCREEN_WIDTH;
                if (!torchList.isEmpty()) {
                    Torch lastTorch = torchList.get(torchList.size() - 1);
                    minTorchX = lastTorch.getX() + lastTorch.getTorchBitmap().getWidth() + 100; // 100 is the buffer space
                }
                int nextTorchX = Math.max(minTorchX, AppConstants.SCREEN_WIDTH + 400); // Ensure new torch is off-screen
// Set the Y-coordinate of the torch to ground level
                int torchGroundY = groundLevel - AppConstants.getBitmapBank().getTorch().getHeight();
// Determine whether to spawn a regular torch or the second torch
                boolean spawnSecondTorch = random.nextBoolean();
                if (spawnSecondTorch) {
                    torchList.add(new Torch(AppConstants.getBitmapBank().getTorch2(), nextTorchX, torchGroundY));
                } else {
                    torchList.add(new Torch(AppConstants.getBitmapBank().getTorch(), nextTorchX, torchGroundY));
                }
                lastTorchSpawn = currentTime;
                isSpawningTorch = false;
            }
        }
    }
    public void updateGameSpeed() {
        if (score >= 400) {
            backgroundImage.setVelocity(15);
        } else if (score >= 300) {
            backgroundImage.setVelocity(13);
        } else if (score >= 150) {
            backgroundImage.setVelocity(11);
        }else{
            backgroundImage.setVelocity(10);
        }
    }
    public void updateAndDrawTorches(Canvas canvas) {
        List<Torch> copyList = new ArrayList<>(torchList);
        for (Torch torch : copyList) {
            torch.setX(torch.getX() - AppConstants.getGameEngine().backgroundImage.getVelocity());
            canvas.drawBitmap(torch.getTorchBitmap(), torch.getX(), torch.getY(), null);
            if (isCollision(manok, torch)) {
                gameOver();
                if(walletState != 1){
                    isWalletVisible = true;
                    walletState = 1;
                }
            } else if (manok.getX() > torch.getX() && !torch.isPassed()) {
                score+=30;
                totalScores+=30;
                torch.setPassed(true);
                if (score >= highscore) {
                    highscore = score;
                }
            }
// Remove torches that are off-screen
            if (torch.getX() < -torch.getTorchBitmap().getWidth()) {
                torchList.remove(torch);
            }
        }
    }
    private boolean deadMusicPlayed = false; // Flag to track whether dead music has been played
    public void gameOver() {
        saveTotalScores(totalScores);
        gameState = 2;
        whilePlayingMusic.pause();
        if (!deadMusicPlayed && deadMusic != null) {
            deadMusic.start();
            deadMusicPlayed = true;
        }
        if (score > loadHighScore()) {
            saveHighScore(score); // Save as new highscore if it's higher
        }
        backgroundImage.setVelocity(0);
        manok.setVelocityX(0);
    }
    public void restartGame() {
// Reset everything
        walletState = 0;
        whilePlayingMusic.start();
        deadMusicPlayed = false;
        gameState = 1;
        manok.setX(AppConstants.SCREEN_WIDTH / 10);
        manok.setY(groundLevel - AppConstants.getBitmapBank().getBirdHeight());
        score = 0;
        totalScores = loadTotalScores();
        highscore = loadHighScore();
        backgroundImage.setVelocity(10);
        torchList.clear();
    }
    public void saveHighScore(int highscore) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("highscore", highscore);
        editor.apply();
    }
    public int loadHighScore() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getInt("highscore", 0);
    }
    public void updateTotalPoints() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int totalPoints = sharedPreferences.getInt("TotalPoints", 40);
        totalPoints -= 10;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TotalPoints", totalPoints);
        editor.apply();
    }
    //CALL THIS METHOD IF YOU WANT TO RESET THE HIGHSCORE
    public void resetHighScore() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("highscore");
        editor.apply();
    }
    public void saveTotalScores(int totalScores) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("totalScores", totalScores);
        editor.apply();
    }
    public int loadTotalScores() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return preferences.getInt("totalScores", 0);
    }
    //CALL THIS METHOD IF YOU WANT TO RESET THE HIGHSCORE
    public void resetTotalScores() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("totalScores");
        editor.apply();
        totalScores = 0;
    }
}
