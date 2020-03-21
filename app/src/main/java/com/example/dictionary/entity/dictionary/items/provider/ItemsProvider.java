package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.ItemEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ItemsProvider {
    Completable insert(ItemEntity item);
    Completable update(ItemEntity itemToEdit, ItemEntity newState);
    Completable delete(ItemEntity item);
    Observable<List<ItemEntity>> getAll();
    void openDb(String dictionaryTableName);
    void closeDb();
}
