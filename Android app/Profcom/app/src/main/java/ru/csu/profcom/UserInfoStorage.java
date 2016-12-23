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

    public void setUserData(String userID, String username) {
        editor = sharedPreferences.edit();
        editor
                .putBoolean("Login", true)
                .putString("userID", userID)
                .putString("username", username)
                .commit();
    }

    public void setUserAvatar(String avatar) {
        editor = sharedPreferences.edit();
        editor.putString("avatar", avatar)
                .commit();
    }

    public String getUserAvatar() {
        return sharedPreferences.getString("avatar", null);
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean("Login", false);
    }

    public String getUserID() {
        return sharedPreferences.getString("userID", null);
    }

    public String getUsername() {
        return sharedPreferences.getString("username", null);
    }

    public void setNames(String names) {
        editor = sharedPreferences.edit();
        editor.putString("names", names)
                .commit();
    }

    public String getNames() { return sharedPreferences.getString("names", null); }

    public void setRetrofitServer(String HTTPurl) {
        editor = sharedPreferences.edit();
        editor.putString("HTTPurl", HTTPurl)
                .commit();
    }

    public String getRetrofitServer() {
        return sharedPreferences.getString("HTTPurl", null);
    }

    public void clear() {
        String IP = getRetrofitServer();
        editor = sharedPreferences.edit();
        editor
                .clear()
                .commit();

        setRetrofitServer(IP);
    }
}
