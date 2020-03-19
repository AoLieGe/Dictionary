package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

import java.util.List;

import io.reactivex.Observable;

public interface DictionarySheetProvider {
    void add(DictionaryDB item);
    void delete(DictionaryDB item);
    //List<DictionaryDB> getAll();
    Observable<List<DictionaryDB>> getAll();
}
