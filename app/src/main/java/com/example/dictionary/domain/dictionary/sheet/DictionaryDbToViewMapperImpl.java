package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.Language;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

public class DictionaryDbToViewMapperImpl extends Mapper<DictionaryDB, DictionaryView> {
    @Override
    protected DictionaryView mapImpl(DictionaryDB dictionaryDB) {
        return new DictionaryView(
                dictionaryDB.getName(),
                Language.valueOf(dictionaryDB.getLangFrom()),
                Language.valueOf(dictionaryDB.getLangTo())
        );
    }
}
