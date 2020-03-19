package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.app.Language;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

import java.util.ArrayList;
import java.util.List;

public class MockDictionarySheetProviderImpl implements DictionarySheetProvider {
    private List<DictionaryDB> data;

    public MockDictionarySheetProviderImpl() {
        data = new ArrayList<>();
        data.add(new DictionaryDB(0, "Dic1", Language.ENGLISH, Language.RUSSIAN));
    }

    @Override
    public List<DictionaryDB> getAll() {
        return data;
    }
}
