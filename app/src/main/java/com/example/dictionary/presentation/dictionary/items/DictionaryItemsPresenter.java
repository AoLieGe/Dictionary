package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.domain.dictionary.items.DictionaryItemUseCase;

import java.util.List;

@InjectViewState
public class DictionaryItemsPresenter extends MvpPresenter<DictionaryItemsView> {
    private DictionaryItemUseCase dictionaryItemUseCase;

    public DictionaryItemsPresenter(DictionaryItemUseCase dictionaryItemUseCase) {
        this.dictionaryItemUseCase = dictionaryItemUseCase;
    }

    public void getAll() {
        List<DictionaryViewItem> data = dictionaryItemUseCase.getAll();
        getViewState().onUpdateSheet(data);
    }

    public void add(DictionaryViewItem item) {
        dictionaryItemUseCase.add(item);
        getAll();
    }

    public void edit(DictionaryViewItem itemToEdit, DictionaryViewItem newState) {
        dictionaryItemUseCase.edit(itemToEdit, newState);
        getAll();
    }

    public void delete(DictionaryViewItem item) {
        dictionaryItemUseCase.delete(item);
        getAll();
    }
}
