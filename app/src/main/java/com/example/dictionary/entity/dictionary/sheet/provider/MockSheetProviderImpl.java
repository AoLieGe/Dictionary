package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.app.Language;
import com.example.dictionary.entity.dictionary.sheet.SheetItemEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class MockSheetProviderImpl implements SheetProvider {
    private List<SheetItemEntity> data;

    public MockSheetProviderImpl() {
        data = new ArrayList<>();
        data.add(new SheetItemEntity(0, "Dic1", Language.ENGLISH.toString(), Language.RUSSIAN.toString()));
    }

    private int findItemIndex(SheetItemEntity itemToFind) {
        int index = 0;
        for (SheetItemEntity item : data) {
            if (item.getName().equals(itemToFind.getName()) &&
                    item.getLangFrom().equals(itemToFind.getLangFrom()) &&
                    item.getLangTo().equals(itemToFind.getLangTo()) ) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public Completable insert(SheetItemEntity item) {
        item.setId(data.size());
        data.add(item);

        return Completable.complete();
    }

    @Override
    public Completable delete(SheetItemEntity item) {
        int itemIndex = findItemIndex(item);

        if(itemIndex == -1) {
            //TODO some error?
            return Completable.error(new IllegalArgumentException());
        }

        data.remove(itemIndex);
        return Completable.complete();
    }

    @Override
    public Observable<List<SheetItemEntity>> getAll() {
        return Observable.just(data);
    }
}
