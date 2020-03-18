package com.example.dictionary.presentation.dictionary.add.renderer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.dictionary.R;
import com.example.dictionary.app.App;
import com.example.dictionary.app.Language;

import java.util.List;

public class LanguageRenderer implements AdapterView.OnItemSelectedListener {
    private List<Language> mLanguageList;
    private ArrayAdapter<Language> mLanguageAdapter;
    private Language mLanguage;

    public LanguageRenderer(Context context) {
        this.mLanguageList = App.getInstance().getLanguageList();
        mLanguageAdapter = new ArrayAdapter<Language>(context, R.layout.language_item, mLanguageList);
    }

    public Language getLanguage() {
        return mLanguage;
    }

    public ArrayAdapter<Language> getAdapter() {
        return mLanguageAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("DicLog", "onItemSelected");
        mLanguage = (Language) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("DicLog", "onNothingSelected");
    }


}
