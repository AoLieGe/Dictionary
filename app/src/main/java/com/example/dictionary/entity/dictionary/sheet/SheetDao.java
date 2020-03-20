package com.example.dictionary.entity.dictionary.sheet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface SheetDao {
    @Query("SELECT * FROM SheetItemEntity")
    Observable<List<SheetItemEntity>> getAll();

    @Query("SELECT id FROM SheetItemEntity WHERE name=:name AND langFrom=:langFrom AND langTo=:langTo LIMIT 1")
    Single<List<Integer>> getId(String name, String langFrom, String langTo);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(SheetItemEntity item);

    @Delete
    Completable delete(SheetItemEntity item);
}
