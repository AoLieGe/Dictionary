package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.App;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.dictionary.items.mapper.EntityToItemMapperImpl;
import com.example.dictionary.domain.dictionary.items.mapper.ItemToEntityMapperImpl;
import com.example.dictionary.entity.dictionary.items.ItemEntity;
import com.example.dictionary.entity.dictionary.items.provider.ItemProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockItemUseCaseImpl implements ItemUseCase {

    private EntityToItemMapperImpl entityToItemMapper;
    private ItemToEntityMapperImpl itemToEntityMapper;
    private ItemProvider provider;

    public MockItemUseCaseImpl() {
        entityToItemMapper = new EntityToItemMapperImpl();
        itemToEntityMapper = new ItemToEntityMapperImpl();
        provider =  App.getInstance().getItemProvider();
    }

    @Override
    public void add(Item item) {
        provider.add( itemToEntityMapper.map(item) );
    }

    @Override
    public void edit(Item itemToEdit, Item newState) {
        provider.edit( itemToEntityMapper.map(itemToEdit),
                itemToEntityMapper.map(newState) );
    }

    @Override
    public void delete(Item item) {
        provider.delete( itemToEntityMapper.map(item) );
    }

    @Override
    public List<Item> getAll() {
        return sort( map(provider.getAll()) );
    }

    private List<Item> map(List<ItemEntity> data) {
        List<Item> result = new ArrayList<>();
        for (ItemEntity item : data) {
            result.add( entityToItemMapper.map(item) );
        }
        return result;
    }

    private List<Item> sort(List<Item> data) {
        Collections.sort(data);
        return data;
    }

}
