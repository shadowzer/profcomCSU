package ru.csu.profcom;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoStorage {
    public static final String PREF_NAME = "profcomUser";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public UserInfoStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setUserData(String userID, String username, String about) {
        editor = sharedPreferences.edit();
        editor
                .putBoolean("Login", true)
                .putString("userID", userID)
                .putString("username", username)
                .putString("about", about)
                .commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean("Login", false);
    }

    public String getUsedID() {
        return sharedPreferences.getString("userID", null);
    }

    public String getUsername() {
        return sharedPreferences.getString("username", null);
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor
                .clear()
                .commit();
    }
}
