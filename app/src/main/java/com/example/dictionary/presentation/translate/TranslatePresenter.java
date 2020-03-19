package com.example.dictionary.presentation.translate;

import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.app.Language;
import com.example.dictionary.domain.translate.TranslateUseCase;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.dictionary.app.Utils.validateTranslation;

@InjectViewState
public class TranslatePresenter extends MvpPresenter<TranslateView> {
    private TranslateUseCase translateUseCase;
    private Disposable mViewTextChangesSubscription;


    public TranslatePresenter(TranslateUseCase translateUseCase) {
        this.translateUseCase = translateUseCase;
    }

    public void subscribeViewTextChangesOnTranslation(TextView view, Language langFrom, Language langTo) {
        unsubscribeViewTextChangesOnTranslation();

        mViewTextChangesSubscription = RxTextView.textChanges(view)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .filter(s -> !s.isEmpty())
                .observeOn(Schedulers.io())
                .switchMap(s -> translateUseCase.translate(s, langFrom, langTo))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::onShowTranslation,
                        getViewState()::onTranslationError);
    }

    public void unsubscribeViewTextChangesOnTranslation() {
        if(mViewTextChangesSubscription != null && !mViewTextChangesSubscription.isDisposed()) {
            mViewTextChangesSubscription.dispose();
        }
    }

    public Observable<DictionaryViewItem> translate(String word, Language from, Language to) {
        return translateUseCase.translate(word, from, to);
    }

    public void finishIfValidationSuccess(String word, String translation, DictionaryViewItem itemBeforeEdit) {
        Integer validateRes = validateTranslation(word, translation, itemBeforeEdit);
        if (null != validateRes) {
            getViewState().onShowErrorMsg(validateRes);
            return;
        }

        getViewState().onFinish(new DictionaryViewItem(word, translation));
    }

}
