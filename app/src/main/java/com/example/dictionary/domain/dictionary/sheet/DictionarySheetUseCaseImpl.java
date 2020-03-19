package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.App;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.domain.dictionary.sheet.mapper.DictionaryDbToViewMapperImpl;
import com.example.dictionary.domain.dictionary.sheet.mapper.DictionaryViewToDbMapperImpl;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;
import com.example.dictionary.entity.dictionary.sheet.provider.DictionarySheetProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class DictionarySheetUseCaseImpl implements DictionarySheetUseCase {
    private DictionaryDbToViewMapperImpl dbToViewMapper;
    private DictionaryViewToDbMapperImpl viewToDbMapper;
    private DictionarySheetProvider provider;

    public DictionarySheetUseCaseImpl() {
        dbToViewMapper = new DictionaryDbToViewMapperImpl();
        viewToDbMapper = new DictionaryViewToDbMapperImpl();
        provider = App.getInstance().getDictionarySheetProvider();
    }

    @Override
    public void add(DictionaryView item) {
        provider.add(viewToDbMapper.map(item));
    }

    @Override
    public void delete(DictionaryView item) {
        provider.delete(viewToDbMapper.map(item));
    }

    @Override
    public Observable<List<DictionaryView>> getAll() {
        return provider.getAll()
                .map(this::map);
    }

    private List<DictionaryView> map(List<DictionaryDB> data) {
        List<DictionaryView> result = new ArrayList<DictionaryView>();

        for (DictionaryDB item : data) {
            result.add( dbToViewMapper.map(item) );
        }
        return result;
    }
}
