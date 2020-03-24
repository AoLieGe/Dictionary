package com.example.dictionary.entity.dictionary.sheet.provider;

import android.content.Context;

import androidx.room.Room;

import com.example.dictionary.entity.dictionary.sheet.SheetDB;
import com.example.dictionary.entity.dictionary.sheet.SheetItemEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class RoomSheetProviderImpl implements SheetProvider {
    private final SheetDB db;

    public RoomSheetProviderImpl(Context context) {
        db = Room.databaseBuilder(context,
                SheetDB.class,
                "DictionarySheet")
                .build();
    }

    private Single<SheetItemEntity> getId(SheetItemEntity item) {
        return db.dao().getId(item.getName(), item.getLangFrom(), item.getLangTo())
                .map(idList -> {
                    if(!idList.isEmpty()) {
                        item.setId(idList.get(0));
                    }
                    return item;
                });
    }

    @Override
    public Completable insert(SheetItemEntity item) {
        return getId(item).flatMapCompletable(db.dao()::insert);
    }

    @Override
    public Completable delete(SheetItemEntity item) {
        return getId(item).flatMapCompletable(db.dao()::delete);
    }

    @Override
    public Observable<List<SheetItemEntity>> getAll() {
        return db.dao().getAll();
    }
}
