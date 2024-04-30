package com.lim.sarisarisaya;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    Bitmap background_game;
    Bitmap [] manok;
    Bitmap torch;
    Bitmap torch2;
    Bitmap gameOverManok;
    Bitmap jumpManok;
    Bitmap gameOverImage;
    Bitmap playAgainImage;
    Bitmap returnToMenuImage;
    Bitmap scoreBox;
    Bitmap highestScoreBox;
    Bitmap wallet;

    private Context context;


    public BitmapBank (Resources resources){
        this.context = context;
        gameOverImage = BitmapFactory.decodeResource(resources, R.drawable.gameover);
        playAgainImage = BitmapFactory.decodeResource(resources, R.drawable.playagain);
        returnToMenuImage = BitmapFactory.decodeResource(resources, R.drawable.returntomenu);
        background_game = BitmapFactory.decodeResource(resources, R.drawable.background_game);
        background_game = scaleImage(background_game);
        wallet = BitmapFactory.decodeResource(resources, R.drawable.circus_wallet);
//runing manok
        manok = new Bitmap[4];
        manok [0] = BitmapFactory.decodeResource(resources, R.drawable.manok_run1);
        manok [1] = BitmapFactory.decodeResource(resources, R.drawable.manok_run2);
        manok [2] = BitmapFactory.decodeResource(resources, R.drawable.manok_run3);
        manok [3] = BitmapFactory.decodeResource(resources, R.drawable.manok_run4);
        jumpManok = BitmapFactory.decodeResource(resources, R.drawable.manok_jump);
        gameOverManok = BitmapFactory.decodeResource(resources, R.drawable.manok_gameover);
        torch = BitmapFactory.decodeResource(resources, R.drawable.torch);
        torch2 = BitmapFactory.decodeResource(resources, R.drawable.torch2);
        scoreBox = BitmapFactory.decodeResource(resources, R.drawable.circus_score);
        highestScoreBox = BitmapFactory.decodeResource(resources, R.drawable.circus_hscore);
        int scoreBoxWidth = 300;
        int highestScoreBoxWidth = 380;
        scoreBox = scaleScoreImage(scoreBox, scoreBoxWidth); // Adjust divisor for width as needed
        highestScoreBox = scaleScoreImage(highestScoreBox, highestScoreBoxWidth);
    }
    public Bitmap getWallet(){
        return wallet;
    }
    public Bitmap getBird(int frame){
        if (AppConstants.getGameEngine().touchDetected) {
            return jumpManok;
        } else if (AppConstants.getGameEngine().gameState == 2) {
            return gameOverManok;
        } else{
            return manok[frame % manok.length];
        }
    }
    public Bitmap getScoreBox() {
        return scoreBox;
    }
    public Bitmap getHighestScoreBox() {
        return highestScoreBox;
    }
    public int getBirdWidth(){
        return manok[0].getWidth();
    }
    public int getBirdHeight(){
        return manok[0].getHeight();
    }
    public Bitmap getTorch() {
        return torch;
    }
    public Bitmap getTorch2() {
        return torch2;
    }
    //return background bitmap
    public Bitmap getBackground_game(){
        return background_game;
    }
    //return background width
    public int getBackgroundWidth(){
        return background_game.getWidth();
    }
    //return background height
    public int getBackgroundHeight(){
        return background_game.getHeight();
    }
    public Context getContext() {
        return context;
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        int backgroundScaleWitdth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWitdth, AppConstants.SCREEN_HEIGHT, false);
    }
    public Bitmap scaleScoreImage(Bitmap bitmap, int targetWidth) {
        if (bitmap == null)
            return null;
// Calculate the aspect ratio of the original bitmap
        float aspectRatio = (float) bitmap.getWidth() / bitmap.getHeight();
// Calculate the target height based on the aspect ratio and the target width
        int targetHeight = Math.round(targetWidth / aspectRatio);
// Scale the bitmap to the target dimensions
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false);
    }
}
