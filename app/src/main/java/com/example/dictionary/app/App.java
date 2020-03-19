package com.example.dictionary.app;

import android.app.Application;

import com.example.dictionary.entity.dictionary.items.provider.DictionaryItemProvider;
import com.example.dictionary.entity.dictionary.items.provider.MockDictionaryItemProviderImpl;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;
import com.example.dictionary.entity.dictionary.sheet.provider.DictionarySheetProvider;
import com.example.dictionary.entity.dictionary.sheet.provider.MockDictionarySheetProviderImpl;

import java.util.Arrays;
import java.util.List;

public class App extends Application {
    private static App instance;
    private static List<Language> languageList;
    private static DictionarySheetProvider dictionariesProvider;
    private static DictionaryItemProvider dictionaryItemProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLangList();
        initProviders();
    }

    private void initLangList() {
        languageList = Arrays.asList(Language.values());
    }

    private void initProviders() {
        dictionariesProvider = new MockDictionarySheetProviderImpl();
        dictionaryItemProvider = new MockDictionaryItemProviderImpl();
    }

    public static App getInstance() {
        return instance;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public DictionarySheetProvider getDictionarySheetProvider() {
        return dictionariesProvider;
    }

    public DictionaryItemProvider getDictionaryItemProvider() {
        return dictionaryItemProvider;
    }
}
