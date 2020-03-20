package com.example.dictionary.entity.dictionary.sheet;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SheetItemEntity.class}, version = 1, exportSchema = false)
public abstract class SheetDB extends RoomDatabase {
    public abstract SheetDao dao();
}
