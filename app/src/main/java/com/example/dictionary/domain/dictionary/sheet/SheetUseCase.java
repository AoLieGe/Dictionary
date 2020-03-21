package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.dictionary.SheetItem;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface SheetUseCase {
    Completable insert(SheetItem item);
    Completable delete(SheetItem item);
    Observable<List<SheetItem>> getAll();
}
