package com.example.dictionary.presentation.dictionary.add;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.dictionary.R;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.Utils;
import com.example.dictionary.presentation.dictionary.add.renderer.LanguageRenderer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DictionaryAddActivity extends MvpAppCompatActivity
implements DictionaryAddView{
    @BindView(R.id.dictionaryName)
    EditText dictionaryName;
    @BindView(R.id.dictionaryLanguageFromSpinner)
    Spinner languageFromSpinner;
    @BindView(R.id.dictionaryLanguageToSpinner)
    Spinner languageToSpinner;

    @InjectPresenter
    DictionaryAddPresenter mPresenter;

    @ProvidePresenter
    DictionaryAddPresenter providePresenter() {
        return new DictionaryAddPresenter();
    }

    private LanguageRenderer mFromLangRenderer;
    private LanguageRenderer mToLangRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_add);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        initLangRenderers();
        initSpinners();
    }

    private void initLangRenderers() {
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

        mPresenter.add(name, fromLang, toLang);
    }

    @Override
    public void createDictionary(SheetItem dictionary) {
        Intent intent = new Intent();
        intent.putExtra(Utils.DICTIONARY_NEW_TAG, dictionary);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onShowErrorMsg(int msgResource) {
        Toast.makeText(this, msgResource, Toast.LENGTH_SHORT).show();
    }
}
