package com.example.dictionary.domain.dictionary.items.mapper;

import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;

public class DictionaryItemDbToViewMapperImpl extends Mapper<DictionaryItemDB, DictionaryViewItem> {
    @Override
    protected DictionaryViewItem mapImpl(DictionaryItemDB dictionaryItemDB) {
        return new DictionaryViewItem(dictionaryItemDB.getWord(), dictionaryItemDB.getTranslation());
    }
}
