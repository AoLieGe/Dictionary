package com.example.dictionary.domain.dictionary.items.mapper;

import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.items.ItemEntity;

public class EntityToItemMapperImpl extends Mapper<ItemEntity, Item> {
    @Override
    protected Item mapImpl(ItemEntity dictionaryItemDB) {
        return new Item(dictionaryItemDB.getWord(), dictionaryItemDB.getTranslation());
    }
}
