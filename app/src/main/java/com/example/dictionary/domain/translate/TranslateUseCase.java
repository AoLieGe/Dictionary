package com.example.dictionary.domain.translate;

import com.example.dictionary.app.Language;
import com.example.dictionary.app.dictionary.Item;

import io.reactivex.Observable;

public interface TranslateUseCase {
    Observable<Item> translate(String word, Language langFrom, Language langTo);
}
