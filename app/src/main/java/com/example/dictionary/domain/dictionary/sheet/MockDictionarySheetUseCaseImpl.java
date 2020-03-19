package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.App;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;
import com.example.dictionary.entity.dictionary.sheet.provider.DictionarySheetProvider;

import java.util.ArrayList;
import java.util.List;

public class MockDictionarySheetUseCaseImpl implements DictionarySheetUseCase {
    private DictionaryDbToViewMapperImpl dbToViewMapper;
    private DictionaryViewToDbMapperImpl viewToDbMapper;
    private DictionarySheetProvider provider;

    public MockDictionarySheetUseCaseImpl() {
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
    public List<DictionaryView> getAll() {
        return map(provider.getAll());
    }

    private List<DictionaryView> map(List<DictionaryDB> data) {
        List<DictionaryView> result = new ArrayList<DictionaryView>();

        for (DictionaryDB item : data) {
            result.add( dbToViewMapper.map(item) );
        }
        return result;
    }
}
