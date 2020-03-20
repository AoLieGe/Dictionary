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
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.Utils;
import com.example.dictionary.domain.translate.YandexTranslateUseCaseImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

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

    private String mMode;
    private Item itemBeforeEdit;
    private Disposable editSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        readIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.subscribeViewTextChangesOnTranslation(word, Language.ENGLISH, Language.RUSSIAN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unsubscribeViewTextChangesOnTranslation();
    }

    private void readIntent() {
        Intent intent = getIntent();
        mMode = intent.getStringExtra(Utils.TRANSLATE_MODE_TAG);

        switch (mMode) {
            case Utils.TRANSLATE_EDIT_TAG:
                button.setText(R.string.translate_edit_button_text);

                itemBeforeEdit = getIntent().getParcelableExtra(Utils.TRANSLATE_EDIT_TAG);
                word.setText(itemBeforeEdit.getWord());
                translation.setText(itemBeforeEdit.getTranslation());
                break;

            case Utils.TRANSLATE_ADD_TAG:
                button.setText(R.string.translate_add_button_text);

                itemBeforeEdit = null;
                break;
        }
    }
/*
    private void supplyTextChanges() {
        unsubscibe();

        editSubscriber = RxTextView.textChanges(word)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .filter(s -> !s.isEmpty())
                .observeOn(Schedulers.io())
                .switchMap(s -> mPresenter.translate(s, Language.ENGLISH, Language.RUSSIAN))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void unsubscibe() {
        if(editSubscriber != null && !editSubscriber.isDisposed()) {
            editSubscriber.dispose();
        }
    }*/

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
    public void onFinish(Item dictionaryViewItem) {
        Intent intent = new Intent();
        switch (mMode) {
            case Utils.TRANSLATE_EDIT_TAG:
                intent.putExtra(Utils.TRANSLATE_BEFORE_EDIT_TAG, itemBeforeEdit);
                break;
        }

        intent.putExtra(Utils.TRANSLATE_RESULT_TAG, dictionaryViewItem);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onShowErrorMsg(int msgResource) {
        Toast.makeText(this, msgResource, Toast.LENGTH_SHORT).show();
    }
}
