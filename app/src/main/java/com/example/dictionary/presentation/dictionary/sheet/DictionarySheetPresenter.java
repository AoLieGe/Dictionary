package com.example.dictionary.presentation.dictionary.sheet;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.domain.dictionary.sheet.DictionarySheetUseCase;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DictionarySheetPresenter extends MvpPresenter<DictionarySheetView> {
    private DictionarySheetUseCase dictionarySheetUseCase;

    public DictionarySheetPresenter(DictionarySheetUseCase dictionarySheetUseCase) {
        this.dictionarySheetUseCase = dictionarySheetUseCase;
    }

    public void getAll() {
        dictionarySheetUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onUpdateSheet);
        //List<DictionaryView> data = dictionarySheetUseCase.getAll();
        //getViewState().onUpdateSheet(data);
    }

    public void add(DictionaryView item) {
        dictionarySheetUseCase.add(item);
        getAll();
    }

    public void delete(DictionaryView item) {
        dictionarySheetUseCase.delete(item);
        getAll();
    }
}
