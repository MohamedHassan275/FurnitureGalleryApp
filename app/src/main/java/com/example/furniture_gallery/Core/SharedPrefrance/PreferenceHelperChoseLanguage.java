
package com.example.furniture_gallery.Core.SharedPrefrance;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelperChoseLanguage {

    private static PreferenceHelperChoseLanguage minst;

    private final String Lang = "Lang";
    private SharedPreferences app_prefs;
    private static Context mcontext;


    public static synchronized PreferenceHelperChoseLanguage getInstans(Context context) {
        if (minst == null) {
            minst = new PreferenceHelperChoseLanguage(context);
        }
        return minst;
    }

    public PreferenceHelperChoseLanguage(Context context) {
        app_prefs = context.getSharedPreferences("sharedChoseLanguage",
                Context.MODE_PRIVATE);
        this.mcontext = context;
    }


    public void putLang(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(Lang, loginorout);
        edit.commit();
    }



    public String getLang() {
        return app_prefs.getString(Lang, "");
    }

    public boolean logout() {
        app_prefs = mcontext.getSharedPreferences("sharedChoseLanguage",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = app_prefs.edit();
        editor.clear();
        editor.apply();
        return true;

    }
}
