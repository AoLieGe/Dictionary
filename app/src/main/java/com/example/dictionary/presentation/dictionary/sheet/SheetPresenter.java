package com.example.dictionary.presentation.dictionary.sheet;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.dictionary.sheet.SheetUseCase;

import java.util.List;
import java.util.function.Function;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SheetPresenter extends MvpPresenter<SheetView> {
    private SheetUseCase sheetUseCase;
    private Disposable sheetSupplier;
    private Disposable insertSupplier;
    private Disposable deleteSupplier;

    public SheetPresenter(SheetUseCase sheetUseCase) {
        this.sheetUseCase = sheetUseCase;
    }

    public void supplyDictionarySheet() {
        unsubsribeDictionarySheet();

        sheetSupplier = sheetUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onUpdateSheet,
                        getViewState()::onError
                );
    }

    public void unsubsribeDictionarySheet() {
        unsubscribeSupplier(sheetSupplier);
    }

    private void unsubscribeSupplier(Disposable supplier) {
        if (supplier != null && !supplier.isDisposed()) {
            supplier.dispose();
            supplier = null;
        }
    }

    public void insert(SheetItem item) {
        unsubscribeSupplier(insertSupplier);

        insertSupplier = sheetUseCase.add(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onInsertComplete,
                        getViewState()::onError
                );
    }

    public void delete(SheetItem item) {
        unsubscribeSupplier(deleteSupplier);

        deleteSupplier = sheetUseCase.delete(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onDeleteComplete,
                        getViewState()::onError
                );
    }
}