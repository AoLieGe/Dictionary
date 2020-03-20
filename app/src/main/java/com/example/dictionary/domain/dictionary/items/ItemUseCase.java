package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.dictionary.Item;

import java.util.List;

public interface ItemUseCase {
    void add(Item item);
    void edit(Item itemToEdit, Item newState);
    void delete(Item item);
    List<Item> getAll();
}
