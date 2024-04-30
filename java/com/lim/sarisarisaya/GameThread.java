package com.lim.sarisarisaya;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    SurfaceHolder surfaceHolder; //object reference

    boolean isRunning; //Flag to detect whether the thread is running or not
    long startTime, loopTime; //Loop start time and loop duration
    long DELAY = 39; //Delay in milliseconds between screen refreshes


    public GameThread (SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    //looping until the boolean turns false
    public void run() {
        while (isRunning) {
            startTime = SystemClock.uptimeMillis();
            //Locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas != null) {
                try {
                    synchronized (surfaceHolder) {
                        AppConstants.getGameEngine().updateAndDrawableBackgroundImage(canvas);
                        AppConstants.getGameEngine().updateAndDrawManok(canvas);
                        AppConstants.getGameEngine().updateAndDrawTorches(canvas);
                        AppConstants.getGameEngine().updateAndDrawWallet(canvas);
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            //LOOP TIME
            loopTime = SystemClock.uptimeMillis() - startTime;
            //pausing to make sure the right amount per second
            if (loopTime < DELAY) {
                try {
                    Thread.sleep(DELAY - loopTime);
                } catch (InterruptedException e) {
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }
    }


    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean state){
        isRunning = state;
    }
}
