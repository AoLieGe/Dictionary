package com.example.dictionary.presentation.translate;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.DictionaryViewItem;

public interface TranslateView extends MvpView {
    void onShowTranslation(DictionaryViewItem dictionaryItem);
    void onTranslationError(Throwable e);

    void onFinish(DictionaryViewItem dictionaryViewItem);
    void onShowErrorMsg(int msgResource);
}
