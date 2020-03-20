package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.dictionary.items.ItemUseCase;

import java.util.List;

@InjectViewState
public class DictionaryItemsPresenter extends MvpPresenter<DictionaryItemsView> {
    private ItemUseCase dictionaryItemUseCase;

    public DictionaryItemsPresenter(ItemUseCase dictionaryItemUseCase) {
        this.dictionaryItemUseCase = dictionaryItemUseCase;
    }

    public void getAll() {
        List<Item> data = dictionaryItemUseCase.getAll();
        getViewState().onUpdateSheet(data);
    }

    public void add(Item item) {
        dictionaryItemUseCase.add(item);
        getAll();
    }

    public void edit(Item itemToEdit, Item newState) {
        dictionaryItemUseCase.edit(itemToEdit, newState);
        getAll();
    }

    public void delete(Item item) {
        dictionaryItemUseCase.delete(item);
        getAll();
    }
}
