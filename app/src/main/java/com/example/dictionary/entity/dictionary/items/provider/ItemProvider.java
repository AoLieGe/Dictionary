package com.example.dictionary.entity.dictionary.items.provider;

import com.example.dictionary.entity.dictionary.items.ItemEntity;

import java.util.List;

public interface ItemProvider {
    void add(ItemEntity item);
    void edit(ItemEntity itemToEdit, ItemEntity newState);
    void delete(ItemEntity item);
    List<ItemEntity> getAll();
}
