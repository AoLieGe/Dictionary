package com.example.dictionary.app;

import android.app.Application;

import androidx.room.Room;

import com.example.dictionary.entity.dictionary.items.provider.DictionaryItemProvider;
import com.example.dictionary.entity.dictionary.items.provider.MockDictionaryItemProviderImpl;
import com.example.dictionary.entity.dictionary.sheet.DictionariesDataBase;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;
import com.example.dictionary.entity.dictionary.sheet.provider.DictionarySheetProvider;
import com.example.dictionary.entity.dictionary.sheet.provider.MockDictionarySheetProviderImpl;
import com.example.dictionary.entity.dictionary.sheet.provider.RoomDictionarySheetProviderImpl;
import com.example.dictionary.entity.translate.provider.TranslateProvider;
import com.example.dictionary.entity.translate.provider.YandexTranslateProviderImpl;

import java.util.Arrays;
import java.util.List;

public class App extends Application {
    private static App instance;
    private List<Language> languageList;
    private DictionarySheetProvider dictionariesProvider;
    private DictionaryItemProvider dictionaryItemProvider;
    private TranslateProvider translateProvider;
    private DictionariesDataBase dictionariesDB;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initLangList();
        initDB();
        initProviders();
    }

    private void initLangList() {
        languageList = Arrays.asList(Language.values());
    }

    private void initDB() {
        dictionariesDB = Room.databaseBuilder(this,
                DictionariesDataBase.class,
                "dictionaries")
                .build();
    }

    private void initProviders() {
        dictionariesProvider = new RoomDictionarySheetProviderImpl();
        dictionaryItemProvider = new MockDictionaryItemProviderImpl();
        translateProvider = new YandexTranslateProviderImpl();
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

    public TranslateProvider getTranslateProvider() {
        return translateProvider;
    }

    public DictionariesDataBase getDictionariesDB() {
        return dictionariesDB;
    }
}
