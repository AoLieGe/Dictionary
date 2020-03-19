package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

public class DictionaryItemViewToDbMapperImpl extends Mapper<DictionaryViewItem, DictionaryItemDB> {
    @Override
    protected DictionaryItemDB mapImpl(DictionaryViewItem item) {
        return new DictionaryItemDB(-1, item.getWord(), item.getTranslation());
    }
}
