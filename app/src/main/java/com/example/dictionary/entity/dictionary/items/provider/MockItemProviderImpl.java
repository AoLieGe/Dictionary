package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class MockItemProviderImpl implements ItemProvider {
    private List<ItemEntity> data;

    public MockItemProviderImpl() {
        data = new ArrayList<>();
        data.add(new ItemEntity(0, "Bird", "Птица"));
        data.add(new ItemEntity(1, "Word", "Слово"));
        data.add(new ItemEntity(2, "Programmer", "Программист"));
        data.add(new ItemEntity(3, "Some", "Некоторый"));
        data.add(new ItemEntity(4, "Window", "Окно"));
        data.add(new ItemEntity(5, "Table", "Стол"));
        data.add(new ItemEntity(6, "Callable", "Вызываемый"));
        data.add(new ItemEntity(7, "Thread", "Поток"));
        data.add(new ItemEntity(8, "Listener", "Слушатель"));
    }

    private int findItemIndex(ItemEntity itemToFind) {
        int index = 0;
        for (ItemEntity item : data) {
            if (item.getWord().equals(itemToFind.getWord()) &&
                item.getTranslation().equals(itemToFind.getTranslation())) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public void add(ItemEntity item) {
        item.setId(data.size());
        data.add(item);
    }

    @Override
    public void edit(ItemEntity itemToEdit, ItemEntity newState) {
        int itemIndex = findItemIndex(itemToEdit);

        if(itemIndex == -1) {
            //TODO some error?
            return;
        }
        newState.setId(itemIndex);
        data.set(itemIndex, newState);
    }

    @Override
    public void delete(ItemEntity item) {
        int itemIndex = findItemIndex(item);

        if(itemIndex == -1) {
            //TODO some error?
            return;
        }

        data.remove(itemIndex);
    }

    @Override
    public List<ItemEntity> getAll() {
        return data;
    }
}
