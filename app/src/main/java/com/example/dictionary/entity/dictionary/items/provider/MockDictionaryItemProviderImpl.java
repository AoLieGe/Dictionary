package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;

import java.util.ArrayList;
import java.util.List;

public class MockDictionaryItemProviderImpl implements DictionaryItemProvider {
    private List<DictionaryItemDB> data;

    public MockDictionaryItemProviderImpl() {
        data = new ArrayList<>();
        data.add(new DictionaryItemDB(0, "Bird", "Птица"));
        data.add(new DictionaryItemDB(1, "Word", "Слово"));
        data.add(new DictionaryItemDB(2, "Programmer", "Программист"));
        data.add(new DictionaryItemDB(3, "Some", "Некоторый"));
        data.add(new DictionaryItemDB(4, "Window", "Окно"));
        data.add(new DictionaryItemDB(5, "Table", "Стол"));
        data.add(new DictionaryItemDB(6, "Callable", "Вызываемый"));
        data.add(new DictionaryItemDB(7, "Thread", "Поток"));
        data.add(new DictionaryItemDB(8, "Listener", "Слушатель"));
    }

    @Override
    public List<DictionaryItemDB> getAll() {
        return data;
    }
}
