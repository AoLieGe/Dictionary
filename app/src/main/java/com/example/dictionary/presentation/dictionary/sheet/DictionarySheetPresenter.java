package com.example.dictionary.presentation.dictionary.sheet;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.domain.dictionary.sheet.DictionarySheetUseCase;
import com.example.dictionary.domain.dictionary.sheet.MockDictionarySheetUseCaseImpl;

import java.util.List;

@InjectViewState
public class DictionarySheetPresenter extends MvpPresenter<DictionarySheetView> {
    private DictionarySheetUseCase dictionarySheetUseCase;

    public DictionarySheetPresenter(DictionarySheetUseCase dictionarySheetUseCase) {
        this.dictionarySheetUseCase = dictionarySheetUseCase;
    }

    public void getAll() {
        List<DictionaryView> data = dictionarySheetUseCase.getAll();
        getViewState().onUpdateSheet(data);
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
