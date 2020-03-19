package com.example.dictionary.entity.dictionary.sheet.provider;

import com.example.dictionary.app.App;
import com.example.dictionary.entity.dictionary.sheet.DictionariesDataBase;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RoomDictionarySheetProviderImpl implements DictionarySheetProvider{
    private DictionariesDataBase db;

    public RoomDictionarySheetProviderImpl() {
        this.db = App.getInstance().getDictionariesDB();
    }

    @Override
    public void add(DictionaryDB item) {
        db.dictionariesDao().insert(item);
    }

    @Override
    public void delete(DictionaryDB item) {
        db.dictionariesDao().delete(item);
    }

    private void check(List<DictionaryDB> data) {
        Boolean empty = data.isEmpty();
        String dd = "asd";

        dd = "aaa";
    }

    @Override
    public Observable<List<DictionaryDB>> getAll() {
        return Observable.just("1")
                .observeOn(Schedulers.io())
                .map(s -> db.dictionariesDao().getAll())
                .doOnNext(this::check);
                //.doOnNext(db.dictionariesDao().getAll());


        //return Observable.just(db.dictionariesDao().getAll());
    }
}
