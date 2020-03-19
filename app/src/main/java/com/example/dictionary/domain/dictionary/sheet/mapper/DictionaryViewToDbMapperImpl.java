package com.example.dictionary.domain.dictionary.sheet.mapper;

import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

public class DictionaryViewToDbMapperImpl extends Mapper<DictionaryView, DictionaryDB> {
    @Override
    protected DictionaryDB mapImpl(DictionaryView dictionaryView) {
        return new DictionaryDB(-1, dictionaryView.getName(), dictionaryView.getLangFrom().toString(), dictionaryView.getLangTo().toString());
    }
}
