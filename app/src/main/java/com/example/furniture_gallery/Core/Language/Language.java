package com.example.furniture_gallery.Core.Language;

import android.content.Context;
import android.content.res.Configuration;

import com.example.furniture_gallery.Core.SharedPrefrance.PreferenceHelperChoseLanguage;

import java.util.Locale;

public class Language {

    public static void changeLanguage(Context context, String languageToLoad) {
        PreferenceHelperChoseLanguage preferenceHelper = PreferenceHelperChoseLanguage.getInstans(context);
        if(preferenceHelper.getLang()==null){

            languageToLoad = "ar";

        }else {

            preferenceHelper.putLang(languageToLoad);
        }

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

}
