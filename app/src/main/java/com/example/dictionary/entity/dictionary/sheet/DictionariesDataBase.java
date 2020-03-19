package com.example.dictionary.entity.dictionary.sheet;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DictionaryDB.class}, version = 1, exportSchema = false)
public abstract class DictionariesDataBase extends RoomDatabase {
    public abstract DictionariesDao dictionariesDao();
}
