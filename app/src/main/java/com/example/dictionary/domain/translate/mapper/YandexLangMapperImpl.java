package com.example.dictionary.domain.translate.mapper;

import com.example.dictionary.app.Language;
import com.example.dictionary.domain.Mapper;

public class YandexLangMapperImpl extends Mapper<Language, String> {
    @Override
    protected String mapImpl(Language language) {
        String result = null;

        switch (language) {
            case ENGLISH:
                result = "en";
                break;
            case RUSSIAN:
                result = "ru";
                break;
            case FRENCH:
                result = "fr";
                break;
            case DEUTSCH:
                result = "de";
                break;
        }

        return result;
    }
}
