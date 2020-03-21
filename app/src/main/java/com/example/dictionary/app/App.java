package com.example.dictionary.app;

import android.app.Application;

import com.example.dictionary.entity.dictionary.items.provider.ItemsProvider;
import com.example.dictionary.entity.dictionary.items.provider.MockItemsProviderImpl;
import com.example.dictionary.entity.dictionary.items.provider.RoomItemsProviderImpl;
import com.example.dictionary.entity.dictionary.sheet.provider.SheetProvider;
import com.example.dictionary.entity.dictionary.sheet.provider.RoomSheetProviderImpl;
import com.example.dictionary.entity.translate.provider.TranslateProvider;
import com.example.dictionary.entity.translate.provider.YandexTranslateProviderImpl;

import java.util.Arrays;
import java.util.List;

public class App extends Application {
    private static App instance;
    private List<Language> languageList;
    private SheetProvider sheetProvider;
    private ItemsProvider itemsProvider;
    private TranslateProvider translateProvider;

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
        sheetProvider = new RoomSheetProviderImpl(getApplicationContext());
        itemsProvider = new RoomItemsProviderImpl(getApplicationContext());
        translateProvider = new YandexTranslateProviderImpl();
    }

    public static App getInstance() {
        return instance;
    }

    public List<Language> getLanguageList() {
        return languageList;
    }

    public SheetProvider getSheetProvider() {
        return sheetProvider;
    }

    public ItemsProvider getItemsProvider() {
        return itemsProvider;
    }

    public TranslateProvider getTranslateProvider() {
        return translateProvider;
    }
}
