package com.example.dictionary.presentation.dictionary.sheet;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.dictionary.SheetItem;

import java.util.List;

public interface SheetView extends MvpView {
    void onGetSheet(List<SheetItem> data);

    void onError(Throwable e);

    void onInsertComplete();

    void onDeleteComplete();
}
