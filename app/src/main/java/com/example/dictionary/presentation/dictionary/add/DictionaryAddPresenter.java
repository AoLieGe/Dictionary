package com.example.dictionary.presentation.dictionary.add;

import android.content.Context;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.Utils;
import com.example.dictionary.presentation.dictionary.add.renderer.LanguageRenderer;

@InjectViewState
public class DictionaryAddPresenter extends MvpPresenter<DictionaryAddView> {

    void add(String name, Language fromLang, Language toLang) {
        Integer validateRes = Utils.validateAddDictionary(name, fromLang, toLang);
        if(null !=  validateRes) {
            getViewState().onShowErrorMsg(validateRes);
            return;
        }

        getViewState().createDictionary(new DictionaryView(name, fromLang, toLang));
    }
}
