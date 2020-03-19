package com.example.dictionary.domain.translate.mapper;

import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.translate.YandexTranslatePost;

public class YandexTranslateMapperImpl extends Mapper<YandexTranslatePost, DictionaryViewItem> {

    @Override
    protected DictionaryViewItem mapImpl(YandexTranslatePost yandexTranslatePost) {
        return new DictionaryViewItem(yandexTranslatePost.getWord(),
                yandexTranslatePost.getText().get(0));
    }
}
