package com.example.dictionary.domain.translate;

import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.app.Language;

import io.reactivex.Observable;

public interface TranslateUseCase {
    Observable<DictionaryViewItem> translate(String word, Language langFrom, Language langTo);
}
