package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

import java.util.List;

public interface DictionarySheetProvider {
    List<DictionaryDB> getAll();
}
