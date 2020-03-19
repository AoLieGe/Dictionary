package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.App;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;
import com.example.dictionary.entity.dictionary.items.provider.DictionaryItemProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockDictionaryItemUseCaseImpl implements DictionaryItemUseCase {

    private DictionaryItemDbToViewMapperImpl dbToItemMapper;
    private DictionaryItemViewToDbMapperImpl viewToDbMapper;
    private DictionaryItemProvider provider;

    public MockDictionaryItemUseCaseImpl() {
        dbToItemMapper = new DictionaryItemDbToViewMapperImpl();
        viewToDbMapper = new DictionaryItemViewToDbMapperImpl();
        provider =  App.getInstance().getDictionaryItemProvider();
    }

    @Override
    public void add(DictionaryViewItem item) {
        provider.add( viewToDbMapper.map(item) );
    }

    @Override
    public void edit(DictionaryViewItem itemToEdit, DictionaryViewItem newState) {
        provider.edit( viewToDbMapper.map(itemToEdit),
                viewToDbMapper.map(newState) );
    }

    @Override
    public void delete(DictionaryViewItem item) {
        provider.delete( viewToDbMapper.map(item) );
    }

    @Override
    public List<DictionaryViewItem> getAll() {
        return sort( map(provider.getAll()) );
    }

    private List<DictionaryViewItem> map(List<DictionaryItemDB> data) {
        List<DictionaryViewItem> result = new ArrayList<>();
        for (DictionaryItemDB item : data) {
            result.add( dbToItemMapper.map(item) );
        }
        return result;
    }

    private List<DictionaryViewItem> sort(List<DictionaryViewItem> data) {
        Collections.sort(data);
        return data;
    }

}
