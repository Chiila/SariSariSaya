package com.lim.sarisarisaya;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ActivityHelper {

    public static void openNewActivity(Activity currentActivity, Class<?> targetActivity) {
        Intent intent = new Intent(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }

    public static void openUriInBrowser(Activity activity, String urlString) {
        // Parse the URL string into a Uri object
        Uri uri = Uri.parse(urlString);

        // Create an intent to view the URI in the browser
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Start the browser activity
        activity.startActivity(intent);
    }
}
