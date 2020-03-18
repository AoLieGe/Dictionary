package com.example.dictionary.presentation.dictionary.add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dictionary.R;
import com.example.dictionary.app.Language;
import com.example.dictionary.presentation.dictionary.add.renderer.LanguageRenderer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryAdd extends AppCompatActivity {
    @BindView(R.id.dictionaryLanguageFromSpinner)
    Spinner languageFromSpinner;
    @BindView(R.id.dictionaryLanguageToSpinner)
    Spinner languageToSpinner;

    private LanguageRenderer mFromLangRenderer;
    private LanguageRenderer mToLangRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_add);
        ButterKnife.bind(this);

        initLanguageRenderers();
        initSpinners();
    }

    private void initLanguageRenderers() {
        mFromLangRenderer = new LanguageRenderer(this);
        mToLangRenderer = new LanguageRenderer(this);
    }

    private void initSpinners() {
        languageFromSpinner.setAdapter(mFromLangRenderer.getAdapter());
        languageFromSpinner.setOnItemSelectedListener(mFromLangRenderer);

        languageToSpinner.setAdapter(mToLangRenderer.getAdapter());
        languageToSpinner.setOnItemSelectedListener(mToLangRenderer);
    }
}
