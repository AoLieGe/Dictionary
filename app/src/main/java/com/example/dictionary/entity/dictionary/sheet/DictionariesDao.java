package com.example.dictionary.entity.dictionary.sheet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DictionariesDao {
    @Query("SELECT * FROM dictionarydb")
    List<DictionaryDB> getAll();
    @Insert
    void insert(DictionaryDB item);
    @Delete
    void delete(DictionaryDB item);
}
