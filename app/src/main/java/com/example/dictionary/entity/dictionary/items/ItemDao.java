package com.example.dictionary.entity.dictionary.items;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM ItemEntity")
    Observable<List<ItemEntity>> getAll();

    @Query("SELECT id FROM ItemEntity WHERE word=:word AND translation=:translation LIMIT 1")
    Single<List<Integer>> getId(String word, String translation);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(ItemEntity item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable update(ItemEntity item);

    @Delete
    Completable delete(ItemEntity item);
}
