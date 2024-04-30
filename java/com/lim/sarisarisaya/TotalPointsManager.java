package com.lim.sarisarisaya;

import android.content.Context;
import android.content.SharedPreferences;

public class TotalPointsManager {

    // Method to update total points by adding pointsEarned
    public static void updateTotalPoints(Context context, int pointsEarned) {
        // Get SharedPreferences instance
       SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);

        // Retrieve current total points, defaulting to 40 if not present
       int totalPoints = sharedPreferences.getInt("TotalPoints", 300);

        // Update total points by adding pointsEarned
       totalPoints += pointsEarned;

        // Store the updated total points back to SharedPreferences
       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putInt("TotalPoints", totalPoints);
       editor.apply();
    }

    // Method to update total points by subtracting 10
    public static void updateTotalPoints(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        int totalPoints = sharedPreferences.getInt("TotalPoints", 300);

        totalPoints -= 50;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("TotalPoints", totalPoints);
        editor.apply();
    }
}
