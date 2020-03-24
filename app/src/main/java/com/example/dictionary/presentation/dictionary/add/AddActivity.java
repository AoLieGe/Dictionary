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
import com.example.dictionary.app.Const;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.presentation.dictionary.add.renderer.LanguageRenderer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddActivity extends MvpAppCompatActivity
        implements AddView {
    @BindView(R.id.dictionaryName)
    EditText dictionaryName;
    @BindView(R.id.dictionaryLanguageFromSpinner)
    Spinner languageFromSpinner;
    @BindView(R.id.dictionaryLanguageToSpinner)
    Spinner languageToSpinner;

    @InjectPresenter
    AddPresenter presenter;

    @ProvidePresenter
    AddPresenter providePresenter() {
        return new AddPresenter();
    }

    private LanguageRenderer fromLangRenderer;
    private LanguageRenderer toLangRenderer;

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
        fromLangRenderer = new LanguageRenderer(this);
        toLangRenderer = new LanguageRenderer(this);
    }

    private void initSpinners() {
        languageFromSpinner.setAdapter(fromLangRenderer.getAdapter());
        languageFromSpinner.setOnItemSelectedListener(fromLangRenderer);

        languageToSpinner.setAdapter(toLangRenderer.getAdapter());
        languageToSpinner.setOnItemSelectedListener(toLangRenderer);
    }

    @OnClick(R.id.dictionaryAddButton)
    protected void onAddButtonClick() {
        String name = dictionaryName.getText().toString();
        Language fromLang = fromLangRenderer.getLanguage();
        Language toLang = toLangRenderer.getLanguage();

        presenter.add(name, fromLang, toLang);
    }

    @Override
    public void createDictionary(SheetItem dictionary) {
        Intent intent = new Intent();
        intent.putExtra(Const.DICTIONARY_NEW_TAG, dictionary);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onShowErrorMsg(int msgResource) {
        Toast.makeText(this, msgResource, Toast.LENGTH_SHORT).show();
    }
}
