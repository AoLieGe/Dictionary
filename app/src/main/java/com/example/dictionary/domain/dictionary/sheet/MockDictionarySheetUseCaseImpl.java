package com.example.dictionary.domain.dictionary.sheet;

import com.example.dictionary.app.App;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.entity.dictionary.sheet.DictionaryDB;

import java.util.ArrayList;
import java.util.List;

public class MockDictionarySheetUseCaseImpl implements DictionarySheetUseCase {

    @Override
    public List<DictionaryView> getAll() {
        List<DictionaryDB> data = App.getInstance().getDictionarySheetProvider().getAll();

        return map( data

        );
    }

    private List<DictionaryView> map(List<DictionaryDB> data) {
        List<DictionaryView> result = new ArrayList<DictionaryView>();

        for (DictionaryDB item : data) {
            result.add(
                    new DictionaryView(
                            item.getName(),
                            item.getLangFrom(),
                            item.getLangTo()
                    )
            );
        }
        return result;
    }
}
