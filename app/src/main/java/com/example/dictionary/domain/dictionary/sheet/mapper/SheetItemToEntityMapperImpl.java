package com.example.dictionary.domain.dictionary.sheet.mapper;

import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.sheet.SheetItemEntity;

public class SheetItemToEntityMapperImpl extends Mapper<SheetItem, SheetItemEntity> {
    @Override
    protected SheetItemEntity mapImpl(SheetItem sheetItem) {
        return new SheetItemEntity(0,
                sheetItem.getName(),
                sheetItem.getLangFrom().toString(),
                sheetItem.getLangTo().toString()
        );
    }
}
