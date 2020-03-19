package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;

import java.util.List;

public interface DictionaryItemProvider {
    void add(DictionaryItemDB item);
    void edit(DictionaryItemDB itemToEdit, DictionaryItemDB newState);
    void delete(DictionaryItemDB item);
    List<DictionaryItemDB> getAll();
}
