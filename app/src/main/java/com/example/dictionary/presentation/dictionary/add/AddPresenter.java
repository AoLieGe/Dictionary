package com.example.dictionary.presentation.dictionary.add;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.Utils;
import com.example.dictionary.app.dictionary.SheetItem;

@InjectViewState
public class AddPresenter extends MvpPresenter<AddView> {

    void add(String name, Language fromLang, Language toLang) {
        Integer validateRes = Utils.validateAddDictionary(name, fromLang, toLang);
        if (null != validateRes) {
            getViewState().onShowErrorMsg(validateRes);
            return;
        }

        getViewState().createDictionary(new SheetItem(name, fromLang, toLang));
    }
}
