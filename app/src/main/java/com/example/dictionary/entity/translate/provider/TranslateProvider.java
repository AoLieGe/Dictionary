package com.example.dictionary.entity.translate.provider;

import com.example.dictionary.app.Language;
import com.example.dictionary.entity.translate.YandexTranslatePost;

import io.reactivex.Observable;

public interface TranslateProvider<TranslatePost> {
    Observable<TranslatePost> translate(String word, String langFrom, String langTo);
}
