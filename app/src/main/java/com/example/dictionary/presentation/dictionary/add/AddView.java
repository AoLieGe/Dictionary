package com.example.dictionary.presentation.dictionary.add;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.dictionary.SheetItem;

public interface AddView extends MvpView {
    void createDictionary(SheetItem dictionary);
    void onShowErrorMsg(int msgResource);
}
