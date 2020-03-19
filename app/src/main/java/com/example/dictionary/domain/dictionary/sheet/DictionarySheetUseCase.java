package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.DictionaryView;

import java.util.List;

public interface DictionarySheetUseCase {
    void add(DictionaryView item);
    void delete(DictionaryView item);
    List<DictionaryView> getAll();
}
