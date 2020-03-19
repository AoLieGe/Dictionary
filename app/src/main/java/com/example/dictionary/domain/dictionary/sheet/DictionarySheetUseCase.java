package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.DictionaryView;

import java.util.List;

import io.reactivex.Observable;

public interface DictionarySheetUseCase {
    void add(DictionaryView item);
    void delete(DictionaryView item);
    Observable<List<DictionaryView>> getAll();
}
