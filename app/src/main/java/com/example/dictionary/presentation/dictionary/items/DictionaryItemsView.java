package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.dictionary.Item;

import java.util.List;

public interface DictionaryItemsView extends MvpView {
    void onUpdateSheet(List<Item> data);
}
