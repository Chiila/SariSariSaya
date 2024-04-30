package com.lim.sarisarisaya;

public class Manok {
    private int manokX, manokY, currentFrame, velocityX, velocityY;
    public static int maxFrame;

    public Manok(){
        manokX = AppConstants.SCREEN_WIDTH / 4 - AppConstants.getBitmapBank().getBirdWidth() / 2;
        manokY = AppConstants.SCREEN_HEIGHT/2 - AppConstants.getBitmapBank().getBirdHeight()/2;
        currentFrame = 0;
        maxFrame = 3;
        velocityX = 0;
        velocityY =0;
    }

    public int getVelocityX(){
        return velocityX;
    }

    public void setVelocityX(int velocityX){
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getCurrentFrame(){
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    public int getX(){
        return manokX;
    }

    public int getY() {
        return manokY;
    }

    public void setX(int manokX) {
        this.manokX = manokX;
    }

    public void setY(int manokY) {
        this.manokY = manokY;
    }
}
