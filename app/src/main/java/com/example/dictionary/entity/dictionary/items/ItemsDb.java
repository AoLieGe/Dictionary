package com.example.dictionary.entity.dictionary.items;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemEntity.class}, version = 1, exportSchema = false)
public abstract class ItemsDb extends RoomDatabase {
    public abstract ItemDao dao();
}
