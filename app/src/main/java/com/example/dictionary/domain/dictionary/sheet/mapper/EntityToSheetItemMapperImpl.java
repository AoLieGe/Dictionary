package com.example.dictionary.domain.dictionary.sheet.mapper;

import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.app.Language;
import com.example.dictionary.domain.Mapper;
import com.example.dictionary.entity.dictionary.sheet.SheetItemEntity;

public class EntityToSheetItemMapperImpl extends Mapper<SheetItemEntity, SheetItem> {
    @Override
    protected SheetItem mapImpl(SheetItemEntity dictionaryDB) {
        return new SheetItem(
                dictionaryDB.getName(),
                Language.valueOf(dictionaryDB.getLangFrom()),
                Language.valueOf(dictionaryDB.getLangTo())
        );
    }
}
