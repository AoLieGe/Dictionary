package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.DictionaryViewItem;

import java.util.List;

public interface DictionaryItemsView extends MvpView {
    void onUpdateSheet(List<DictionaryViewItem> data);
}
