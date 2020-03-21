package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.dictionary.Item;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface ItemsUseCase {
    void openDb(String dictionaryTableName);
    void closeDb();
    Completable insert(Item item);
    Completable update(Item itemToEdit, Item newState);
    Completable delete(Item item);
    Observable<List<Item>> getAll();
}
