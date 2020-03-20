package com.example.dictionary.presentation.dictionary.add;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.dictionary.SheetItem;

public interface DictionaryAddView extends MvpView {
    void createDictionary(SheetItem dictionary);
    void onShowErrorMsg(int msgResource);
}
