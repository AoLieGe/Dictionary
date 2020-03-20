package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.entity.dictionary.sheet.SheetItemEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface SheetProvider {
    Completable insert(SheetItemEntity item);
    Completable delete(SheetItemEntity item);
    Observable<List<SheetItemEntity>> getAll();
}
