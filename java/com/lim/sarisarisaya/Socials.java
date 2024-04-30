package com.lim.sarisarisaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Socials extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imgBtnFb, imgBtnTwitter, imgBtnIg, imgBtnGmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);

        // Initialize views
        initViews();

        // Set click listeners for buttons
        setOnClick();
    }

    // Method to initialize views by finding their respective IDs
    private void initViews() {
        imgBtnFb = findViewById(R.id.socials_fb);
        imgBtnTwitter = findViewById(R.id.socials_twitter);
        imgBtnIg = findViewById(R.id.socials_ig);
        imgBtnGmail = findViewById(R.id.socials_gmail);
    }

    // Method to set click listeners for buttons
    private void setOnClick() {
        imgBtnFb.setOnClickListener(this);
        imgBtnTwitter.setOnClickListener(this);
        imgBtnIg.setOnClickListener(this);
        imgBtnGmail.setOnClickListener(this);
    }

    // OnClickListener implementation for button clicks
    @Override
    public void onClick(View v) {
        // Handle button clicks
        if (v.getId() == R.id.socials_fb) {
            ActivityHelper.openUriInBrowser(Socials.this, "https://www.facebook.com/profile.php?id=61556633904112&mibextid=ZbWKwL");
        }

        if (v.getId() == R.id.socials_twitter) {
            ActivityHelper.openUriInBrowser(Socials.this, "https://x.com/SariSari_Saya?t=6g4A4RNVI4EiQSmR0DtWvg&s=09");
        }

        if (v.getId() == R.id.socials_ig) {
            ActivityHelper.openUriInBrowser(Socials.this, "https://www.instagram.com/sarisarisaya?igsh=eTYybnV0Y2drbXRz");
        }

        if (v.getId() == R.id.socials_gmail) {
            Uri uri = Uri.parse("mailto:sarisarisaya69@gmail.com");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            startActivity(intent);
        }
    }

    // Handle back button press
    public void onBackPressed() {
        ActivityHelper.openNewActivity(Socials.this, MainActivity.class);
    }
}