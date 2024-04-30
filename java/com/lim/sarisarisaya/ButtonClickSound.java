package com.lim.sarisarisaya;

import android.content.Context;
import android.media.MediaPlayer;

public class ButtonClickSound {
    private MediaPlayer mediaPlayer;

    public ButtonClickSound(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void playSound(Context context, int soundResourceID) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, soundResourceID);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopSound();
                }
            });
        }
        else {
            mediaPlayer.seekTo(0);
        }

        mediaPlayer.start();
    }

    public void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
