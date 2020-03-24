package com.example.dictionary.presentation.dictionary.sheet;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.dictionary.items.ItemsUseCase;
import com.example.dictionary.domain.dictionary.sheet.SheetUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class SheetPresenter extends MvpPresenter<SheetView> {
    private SheetUseCase sheetUseCase;
    private ItemsUseCase itemsUseCase;
    private Disposable sheetSupplier;
    private Disposable insertSupplier;
    private Disposable deleteSupplier;

    SheetPresenter(SheetUseCase sheetUseCase, ItemsUseCase itemsUseCase) {
        this.sheetUseCase = sheetUseCase;
        this.itemsUseCase = itemsUseCase;
    }

    @Override
    public void onDestroy() {
        disposeDictionarySheet();
        super.onDestroy();
    }

    void supplyDictionarySheet() {
        disposeDictionarySheet();

        sheetSupplier = sheetUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onGetSheet,
                        getViewState()::onError
                );
    }

    void disposeDictionarySheet() {
        disposeSupplier(sheetSupplier);
    }

    private void disposeSupplier(Disposable supplier) {
        if (supplier != null && !supplier.isDisposed()) {
            supplier.dispose();
            supplier = null;
        }
    }

    public void insert(SheetItem item) {
        disposeSupplier(insertSupplier);

        insertSupplier = sheetUseCase.insert(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onInsertComplete,
                        getViewState()::onError
                );
    }

    public void delete(SheetItem item) {
        disposeSupplier(deleteSupplier);

        deleteSupplier = sheetUseCase.delete(item)
                .doOnComplete(() -> {
                    itemsUseCase.openDb(item.getName());
                    itemsUseCase.clearAll();
                    itemsUseCase.closeDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onDeleteComplete,
                        getViewState()::onError
                );
    }
}
