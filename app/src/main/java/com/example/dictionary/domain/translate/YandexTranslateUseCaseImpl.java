package com.example.dictionary.domain.translate;

import com.example.dictionary.app.App;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.app.Language;
import com.example.dictionary.domain.translate.mapper.YandexLangMapperImpl;
import com.example.dictionary.domain.translate.mapper.YandexTranslateMapperImpl;
import com.example.dictionary.entity.translate.provider.YandexTranslateProviderImpl;

import io.reactivex.Observable;

public class YandexTranslateUseCaseImpl implements TranslateUseCase {
    private YandexTranslateProviderImpl provider;
    private YandexLangMapperImpl langMapper;
    private YandexTranslateMapperImpl translateMapper;

    public YandexTranslateUseCaseImpl() {
        provider = (YandexTranslateProviderImpl) App.getInstance().getTranslateProvider();
        langMapper = new YandexLangMapperImpl();
        translateMapper = new YandexTranslateMapperImpl();
    }

    @Override
    public Observable<DictionaryViewItem> translate(String word, Language langFrom, Language langTo) {
        return provider.translate(
                word,
                langMapper.map(langFrom),
                langMapper.map(langTo)
        ).map(translateMapper::map);
    }
}
