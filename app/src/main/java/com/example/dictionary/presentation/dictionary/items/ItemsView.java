package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.dictionary.Item;

import java.util.List;

public interface ItemsView extends MvpView {
    void onGetItems(List<Item> data);
    void onError(Throwable e);
    void onInsertComplete();
    void onUpdateComplete();
    void onDeleteComplete();
}
