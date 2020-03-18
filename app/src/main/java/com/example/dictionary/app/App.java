package com.example.dictionary.app;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

public class App extends Application {
    private static App instance;
    private static List<Language> languageList;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLangList();
    }

    private void initLangList() {
        languageList = Arrays.asList(Language.values());
    }

    public static App getInstance() {
        return instance;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }
}
