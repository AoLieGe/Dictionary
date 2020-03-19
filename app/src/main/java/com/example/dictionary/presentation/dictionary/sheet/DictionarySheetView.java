package com.example.dictionary.presentation.dictionary.sheet;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.DictionaryView;

import java.util.List;

public interface DictionarySheetView extends MvpView {
    void onUpdateSheet(List<DictionaryView> data);
}
