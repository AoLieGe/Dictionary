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

    private int findItemIndex(DictionaryItemDB itemToFind) {
        int index = 0;
        for (DictionaryItemDB item : data) {
            if (item.getWord().equals(itemToFind.getWord()) &&
                item.getTranslation().equals(itemToFind.getTranslation())) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public void add(DictionaryItemDB item) {
        item.setId(data.size());
        data.add(item);
    }

    @Override
    public void edit(DictionaryItemDB itemToEdit, DictionaryItemDB newState) {
        int itemIndex = findItemIndex(itemToEdit);

        if(itemIndex == -1) {
            //TODO some error?
            return;
        }
        newState.setId(itemIndex);
        data.set(itemIndex, newState);
    }

    @Override
    public void delete(DictionaryItemDB item) {
        int itemIndex = findItemIndex(item);

        if(itemIndex == -1) {
            //TODO some error?
            return;
        }

        data.remove(itemIndex);
    }

    @Override
    public List<DictionaryItemDB> getAll() {
        return data;
    }
}
