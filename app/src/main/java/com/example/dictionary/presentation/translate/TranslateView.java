package com.example.dictionary.presentation.translate;

import com.arellomobile.mvp.MvpView;
import com.example.dictionary.app.dictionary.Item;

public interface TranslateView extends MvpView {
    void onShowTranslation(Item dictionaryItem);

    void onTranslationError(Throwable e);

    void onFinish(Item dictionaryViewItem);

    void onShowErrorMsg(int msgResource);
}
