package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.ItemEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class MockItemsProviderImpl implements ItemsProvider {
    private List<ItemEntity> data;

    public MockItemsProviderImpl() {
        data = new ArrayList<>();
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
    public Completable insert(ItemEntity item) {
        item.setId(data.size());
        data.add(item);
        return Completable.complete();
    }

    @Override
    public Completable update(ItemEntity itemToEdit, ItemEntity newState) {
        int itemIndex = findItemIndex(itemToEdit);

        if(itemIndex == -1) {
            return Completable.error(new IllegalArgumentException());
        }
        newState.setId(itemIndex);
        data.set(itemIndex, newState);
        return Completable.complete();
    }

    @Override
    public Completable delete(ItemEntity item) {
        int itemIndex = findItemIndex(item);

        if(itemIndex == -1) {
            return Completable.error(new IllegalArgumentException());
        }

        data.remove(itemIndex);
        return Completable.complete();
    }

    @Override
    public Observable<List<ItemEntity>> getAll() {
        return Observable.just(data);
    }

    @Override
    public void openDb(String dictionaryTableName) {
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

    @Override
    public void closeDb() {
        data.clear();
    }
}
