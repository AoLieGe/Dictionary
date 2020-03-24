package com.example.dictionary.presentation.translate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.dictionary.R;
import com.example.dictionary.app.Const;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.translate.YandexTranslateUseCaseImpl;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class TranslateActivity extends MvpAppCompatActivity
        implements TranslateView {
    @BindView(R.id.translateWord)
    EditText word;
    @BindView(R.id.translateTranslation)
    EditText translation;
    @BindView(R.id.translateButton)
    Button button;

    @InjectPresenter
    TranslatePresenter mPresenter;

    @ProvidePresenter
    TranslatePresenter providePresenter() {
        return new TranslatePresenter(new YandexTranslateUseCaseImpl());
    }

    private String translateActivityMode;
    private Item itemBeforeEdit;
    private SheetItem parentDictionary;
    private Observable<String> textChangeSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        readIntent();
        supplyTextChanges();
        mPresenter.subscribeTextChanges(textChangeSupplier,
                parentDictionary.getLangFrom(),
                parentDictionary.getLangTo());
    }

    private void readIntent() {
        Intent intent = getIntent();
        parentDictionary = intent.getParcelableExtra(Const.TRANSLATE_LANG_TAG);
        translateActivityMode = intent.getStringExtra(Const.TRANSLATE_MODE_TAG);

        switch (translateActivityMode) {
            case Const.TRANSLATE_EDIT_TAG:
                button.setText(R.string.translate_edit_button_text);

                itemBeforeEdit = getIntent().getParcelableExtra(Const.TRANSLATE_EDIT_TAG);
                word.setText(itemBeforeEdit.getWord());
                translation.setText(itemBeforeEdit.getTranslation());
                break;

            case Const.TRANSLATE_ADD_TAG:
                button.setText(R.string.translate_add_button_text);

                itemBeforeEdit = null;
                break;
        }
    }

    private void supplyTextChanges() {
        if (textChangeSupplier == null) {
            textChangeSupplier = RxTextView.textChanges(word)
                    .skip(1)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .map(CharSequence::toString)
                    .filter(s -> !s.isEmpty());
        }
    }

    @Override
    public void onFinish(Item dictionaryViewItem) {
        Intent intent = new Intent();
        switch (translateActivityMode) {
            case Const.TRANSLATE_EDIT_TAG:
                intent.putExtra(Const.TRANSLATE_BEFORE_EDIT_TAG, itemBeforeEdit);
                break;
        }

        intent.putExtra(Const.TRANSLATE_RESULT_TAG, dictionaryViewItem);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.translateButton)
    protected void onButtonClick() {
        String word = this.word.getText().toString();
        String translation = this.translation.getText().toString();

        mPresenter.finishIfValidationSuccess(word, translation, itemBeforeEdit);
    }

    @Override
    public void onShowTranslation(Item dictionaryItem) {
        translation.setText(dictionaryItem.getTranslation());
    }

    @Override
    public void onTranslationError(Throwable e) {
        Log.e("TranslateError", e.toString());
    }

    @Override
    public void onShowErrorMsg(int msgResource) {
        Toast.makeText(this, msgResource, Toast.LENGTH_SHORT).show();
    }
}
