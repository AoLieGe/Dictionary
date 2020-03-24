package com.example.dictionary.presentation.translate;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.Language;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.translate.TranslateUseCase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.dictionary.app.Utils.validateTranslation;

@InjectViewState
public class TranslatePresenter extends MvpPresenter<TranslateView> {
    private TranslateUseCase mTranslateUseCase;
    private Disposable mTextChangesSubscriber;


    public TranslatePresenter(TranslateUseCase translateUseCase) {
        this.mTranslateUseCase = translateUseCase;
    }

    public void subscribeTextChanges(Observable<String> textChangeSupplier, Language langFrom, Language langTo) {
        disposeTextChanges();

        mTextChangesSubscriber = textChangeSupplier
                .switchMap(s -> mTranslateUseCase.translate(s, langFrom, langTo))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onShowTranslation,
                        getViewState()::onTranslationError);
    }

    @Override
    public void onDestroy() {
        disposeTextChanges();

        super.onDestroy();
    }

    private void disposeTextChanges() {
        if(mTextChangesSubscriber != null && !mTextChangesSubscriber.isDisposed()) {
            mTextChangesSubscriber.dispose();
        }
    }

    public Observable<Item> translate(String word, Language from, Language to) {
        return mTranslateUseCase.translate(word, from, to);
    }

    public void finishIfValidationSuccess(String word, String translation, Item itemBeforeEdit) {
        Integer validateRes = validateTranslation(word, translation, itemBeforeEdit);
        if (null != validateRes) {
            getViewState().onShowErrorMsg(validateRes);
            return;
        }

        getViewState().onFinish(new Item(word, translation));
    }

}
