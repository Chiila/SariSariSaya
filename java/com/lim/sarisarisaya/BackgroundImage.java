package com.lim.sarisarisaya;

public class BackgroundImage {
    private int backgroundImageX, backgroundImageY, getBackgroundVelocity;

    public BackgroundImage(){
        backgroundImageX = 0;
        backgroundImageY = 0;
        getBackgroundVelocity = 10; //adjust here the background moving speed
    }

    public int getX(){
        return backgroundImageX;
    }

    public int getY(){
        return backgroundImageY;
    }

    public void setX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    public void setY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    public void setVelocity(int velocity) {
        this.getBackgroundVelocity = velocity;
    }

    public int getVelocity(){
        return getBackgroundVelocity;

    }
}
