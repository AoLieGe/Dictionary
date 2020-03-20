package com.example.dictionary.domain.dictionary.items.mapper;

import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.items.ItemEntity;

public class ItemToEntityMapperImpl extends Mapper<Item, ItemEntity> {
    @Override
    protected ItemEntity mapImpl(Item item) {
        return new ItemEntity(0, item.getWord(), item.getTranslation());
    }
}
