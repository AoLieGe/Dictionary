package com.example.dictionary.presentation.dictionary.add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dictionary.R;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.Utils;
import com.example.dictionary.presentation.dictionary.add.renderer.LanguageRenderer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DictionaryAddActivity extends AppCompatActivity {
    @BindView(R.id.dictionaryName)
    EditText dictionaryName;
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
        getSupportActionBar().hide();
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

    @OnClick(R.id.dictionaryAddButton)
    protected void onAddButtonClick() {
        String name = dictionaryName.getText().toString();
        Language fromLang = mFromLangRenderer.getLanguage();
        Language toLang = mToLangRenderer.getLanguage();

        Integer validateRes = Utils.validateAddDictionary(name, fromLang, toLang);
        if(null !=  validateRes) {
            Toast.makeText(this, validateRes, Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO add dictionary and enter to it
        Intent intent = new Intent();
        intent.putExtra(Utils.DICTIONARY_NEW_TAG, new DictionaryView(name, fromLang, toLang));
        setResult(RESULT_OK, intent);
        finish();
    }
}