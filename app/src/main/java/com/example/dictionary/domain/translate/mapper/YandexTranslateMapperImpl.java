package com.example.dictionary.domain.translate.mapper;

import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.translate.YandexTranslatePost;

public class YandexTranslateMapperImpl extends Mapper<YandexTranslatePost, Item> {

    @Override
    protected Item mapImpl(YandexTranslatePost yandexTranslatePost) {
        return new Item(yandexTranslatePost.getWord(),
                yandexTranslatePost.getText().get(0));
    }
}
