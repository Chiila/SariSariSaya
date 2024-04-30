package com.lim.sarisarisaya;

import android.graphics.Bitmap;

public class Torch {
    private int torchX, torchY;
    private Bitmap torchBitmap;

    public Torch(Bitmap bitmap, int x, int y) {
        this.torchBitmap = bitmap;
        this.torchX = x;
        this.torchY = y;
    }

    public int getX() {
        return torchX;
    }

    public void setX(int torchX) {
        this.torchX = torchX;
    }

    public int getY() {
        return torchY;
    }

    public void setY(int torchY) {
        this.torchY = torchY;
    }

    public Bitmap getTorchBitmap() {
        return torchBitmap;
    }

    private boolean passed = false;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
