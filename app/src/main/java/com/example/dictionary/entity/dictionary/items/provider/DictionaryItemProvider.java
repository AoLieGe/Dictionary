package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;

import java.util.List;

public interface DictionaryItemProvider {
    List<DictionaryItemDB> getAll();
}
