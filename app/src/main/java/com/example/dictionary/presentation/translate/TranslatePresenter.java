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
    private TranslateUseCase translateUseCase;
    private Disposable textChangesSubscriber;

    TranslatePresenter(TranslateUseCase translateUseCase) {
        this.translateUseCase = translateUseCase;
    }

    void subscribeTextChanges(Observable<String> textChangeSupplier, Language langFrom, Language langTo) {
        disposeTextChanges();

        textChangesSubscriber = textChangeSupplier
                .switchMap(s -> translateUseCase.translate(s, langFrom, langTo))
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
        if (textChangesSubscriber != null && !textChangesSubscriber.isDisposed()) {
            textChangesSubscriber.dispose();
        }
    }

    public Observable<Item> translate(String word, Language from, Language to) {
        return translateUseCase.translate(word, from, to);
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
