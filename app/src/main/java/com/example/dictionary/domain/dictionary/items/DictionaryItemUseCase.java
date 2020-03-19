package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.DictionaryViewItem;

import java.util.List;

public interface DictionaryItemUseCase {
    List<DictionaryViewItem> getAll();
}
