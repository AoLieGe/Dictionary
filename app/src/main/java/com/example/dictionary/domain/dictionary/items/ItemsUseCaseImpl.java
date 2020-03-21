package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.App;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.dictionary.items.mapper.EntityToItemMapperImpl;
import com.example.dictionary.domain.dictionary.items.mapper.ItemToEntityMapperImpl;
import com.example.dictionary.entity.dictionary.items.ItemEntity;
import com.example.dictionary.entity.dictionary.items.provider.ItemsProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class ItemsUseCaseImpl implements ItemsUseCase {

    private EntityToItemMapperImpl entityToItemMapper;
    private ItemToEntityMapperImpl itemToEntityMapper;
    private ItemsProvider provider;

    public ItemsUseCaseImpl() {
        entityToItemMapper = new EntityToItemMapperImpl();
        itemToEntityMapper = new ItemToEntityMapperImpl();
        provider = App.getInstance().getItemsProvider();
    }

    @Override
    public void openDb(String dictionaryTableName) {
        provider.openDb(dictionaryTableName);
    }

    @Override
    public void closeDb() {
        provider.closeDb();
    }

    @Override
    public Completable insert(Item item) {
        return provider.insert(itemToEntityMapper.map(item));
    }

    @Override
    public Completable update(Item itemToEdit, Item newState) {
        return provider.update(
                itemToEntityMapper.map(itemToEdit),
                itemToEntityMapper.map(newState)
        );
    }

    @Override
    public Completable delete(Item item) {
        return provider.delete(itemToEntityMapper.map(item));
    }

    @Override
    public Observable<List<Item>> getAll() {
        return provider.getAll()
                .map(this::map)
                .map(this::sort);
    }

    private List<Item> map(List<ItemEntity> data) {
        List<Item> result = new ArrayList<>();
        for (ItemEntity item : data) {
            result.add(entityToItemMapper.map(item));
        }
        return result;
    }

    private List<Item> sort(List<Item> data) {
        Collections.sort(data);
        return data;
    }

}
