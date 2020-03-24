package com.example.dictionary.entity.dictionary.items.provider;

import android.content.Context;

import androidx.room.Room;

import com.example.dictionary.entity.dictionary.items.ItemEntity;
import com.example.dictionary.entity.dictionary.items.ItemsDb;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RoomItemsProviderImpl implements ItemsProvider {
    private ItemsDb db;
    private Context context;

    public RoomItemsProviderImpl(Context context) {
        this.context = context;
    }

    public void openDb(String dictionaryTableName) {
        db = Room.databaseBuilder(context,
                ItemsDb.class,
                dictionaryTableName)
                .build();
    }

    public void clearAll() {
        db.clearAllTables();
    }

    @Override
    public void closeDb() {
        if(db != null && db.isOpen()) {
            db.close();
            db = null;
        }
    }

    private Single<Integer> getId(ItemEntity item) {
        return db.dao().getId(item.getWord(), item.getTranslation())
                .map(idList -> {
                    Integer result = 0;
                    if(!idList.isEmpty()) {
                        result = idList.get(0);
                    }
                    return result;
                });
    }

    @Override
    public Completable insert(ItemEntity item) {
        return getId(item)
                .map(id -> {
                    item.setId(id);
                    return item;
                })
                .flatMapCompletable(db.dao()::insert);
    }

    @Override
    public Completable update(ItemEntity itemToEdit, ItemEntity newState) {
        return getId(itemToEdit)
                .map(id -> {
                    newState.setId(id);
                    return newState;
                })
                .flatMapCompletable(db.dao()::update);
    }

    @Override
    public Completable delete(ItemEntity item) {
        return getId(item)
                .map(id -> {
                    item.setId(id);
                    return item;
                })
                .flatMapCompletable(db.dao()::delete);
    }

    @Override
    public Observable<List<ItemEntity>> getAll() {
        return db.dao().getAll();
    }


}
