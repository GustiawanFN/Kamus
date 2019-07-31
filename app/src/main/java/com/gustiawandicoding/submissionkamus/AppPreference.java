package com.gustiawandicoding.submissionkamus;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Gustiawan on 10/1/2018.
 */

class AppPreference {

    private SharedPreferences sharedPreferences;

    AppPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    void setFirstRun(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AppFirstRun", false);
        editor.apply();
    }

    Boolean getFirstRun(){
        return sharedPreferences.getBoolean("AppFirstRun", true);
    }
}
