package com.example.dictionary.presentation.dictionary.add;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.DictionaryView;

public interface DictionaryAddView extends MvpView {
    void createDictionary(DictionaryView dictionary);
    void onShowErrorMsg(int msgResource);
}
