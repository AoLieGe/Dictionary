package com.example.dictionary.domain.dictionary.sheet;

import android.util.Log;

import com.example.dictionary.app.App;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.dictionary.sheet.mapper.EntityToSheetItemMapperImpl;
import com.example.dictionary.domain.dictionary.sheet.mapper.SheetItemToEntityMapperImpl;
import com.example.dictionary.entity.dictionary.sheet.SheetItemEntity;
import com.example.dictionary.entity.dictionary.sheet.provider.SheetProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class SheetUseCaseImpl implements SheetUseCase {
    private EntityToSheetItemMapperImpl entityToSheetItemMapper;
    private SheetItemToEntityMapperImpl sheetItemToEntityMapper;
    private SheetProvider provider;

    public SheetUseCaseImpl() {
        entityToSheetItemMapper = new EntityToSheetItemMapperImpl();
        sheetItemToEntityMapper = new SheetItemToEntityMapperImpl();
        provider = App.getInstance().getSheetProvider();
    }

    @Override
    public Completable insert(SheetItem item) {
        return provider.insert(sheetItemToEntityMapper.map(item));
    }

    @Override
    public Completable delete(SheetItem item) {
        return provider.delete(sheetItemToEntityMapper.map(item));
    }

    @Override
    public Observable<List<SheetItem>> getAll() {
        return provider.getAll()
                .map(this::map);
    }

    private List<SheetItem> map(List<SheetItemEntity> data) {
        List<SheetItem> result = new ArrayList<>();
        for (SheetItemEntity item : data) {
            result.add( entityToSheetItemMapper.map(item) );
        }
        return result;
    }
}
