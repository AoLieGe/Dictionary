package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.app.Language;
import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MockDictionarySheetProviderImpl implements DictionarySheetProvider {
    private List<DictionaryDB> data;

    public MockDictionarySheetProviderImpl() {
        data = new ArrayList<>();
        data.add(new DictionaryDB(0, "Dic1", Language.ENGLISH.toString(), Language.RUSSIAN.toString()));
    }

    private int findItemIndex(DictionaryDB itemToFind) {
        int index = 0;
        for (DictionaryDB item : data) {
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
    public void add(DictionaryDB item) {
        item.setId(data.size());
        data.add(item);
    }

    @Override
    public void delete(DictionaryDB item) {
        int itemIndex = findItemIndex(item);

        if(itemIndex == -1) {
            //TODO some error?
            return;
        }

        data.remove(itemIndex);
    }

    @Override
    public Observable<List<DictionaryDB>> getAll() {
        return Observable.just(data);
    }
}
