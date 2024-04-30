package com.lim.sarisarisaya;

import android.content.Context;
import android.media.MediaPlayer;

public class BackGroundMusic {
    private MediaPlayer mediaPlayer;

    public BackGroundMusic(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playMusic(Context context, int musicResourceID ) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResourceID);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopMusic();
                }
            });
        }
        else {
            mediaPlayer.seekTo(0);
        }

        mediaPlayer.start();
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void resumeMusic() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
