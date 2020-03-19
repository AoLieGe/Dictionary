package com.example.dictionary.domain.dictionary.items;

import com.example.dictionary.app.App;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.entity.dictionary.items.DictionaryItemDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockDictionaryItemUseCaseImpl implements DictionaryItemUseCase {

    @Override
    public List<DictionaryViewItem> getAll() {
        return sort(
                map(App.getInstance().getDictionaryItemProvider().getAll())
        );
    }


    private List<DictionaryViewItem> map(List<DictionaryItemDB> data) {
        List<DictionaryViewItem> result = new ArrayList<>();
        for (DictionaryItemDB item : data) {
            result.add(
                    new DictionaryViewItem(
                            item.getWord(),
                            item.getTranslation()
                    )
            );
        }
        return result;
    }

    private List<DictionaryViewItem> sort(List<DictionaryViewItem> data) {
        Collections.sort(data);
        return data;
    }

}
