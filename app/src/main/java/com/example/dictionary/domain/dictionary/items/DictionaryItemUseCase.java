package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.DictionaryViewItem;

import java.util.List;

public interface DictionaryItemUseCase {
    void add(DictionaryViewItem item);
    void edit(DictionaryViewItem itemToEdit, DictionaryViewItem newState);
    void delete(DictionaryViewItem item);
    List<DictionaryViewItem> getAll();
}
