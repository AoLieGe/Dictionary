package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.dictionary.items.ItemsUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ItemsPresenter extends MvpPresenter<ItemsView> {
    private ItemsUseCase itemsUseCase;
    private Disposable itemsSupplier;
    private Disposable insertSupplier;
    private Disposable updateSupplier;
    private Disposable deleteSupplier;

    ItemsPresenter(ItemsUseCase itemsUseCase) {
        this.itemsUseCase = itemsUseCase;
    }

    @Override
    public void onDestroy() {
        disposeItemsSheet();
        closeDb();
        super.onDestroy();
    }

    void openDb(String dictionaryTableName) {
        itemsUseCase.openDb(dictionaryTableName);
    }

    void closeDb() {
        itemsUseCase.closeDb();
    }

    void supplyItemsSheet() {
        disposeItemsSheet();

        itemsSupplier = itemsUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onGetItems,
                        getViewState()::onError
                );
    }

    void disposeItemsSheet() {
        disposeSupplier(itemsSupplier);
    }

    private void disposeSupplier(Disposable supplier) {
        if (supplier != null && !supplier.isDisposed()) {
            supplier.dispose();
            supplier = null;
        }
    }

    public void insert(Item item) {
        disposeSupplier(insertSupplier);

        insertSupplier = itemsUseCase.insert(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onInsertComplete,
                        getViewState()::onError
                );
    }

    public void update(Item itemToEdit, Item newState) {
        disposeSupplier(updateSupplier);

        updateSupplier = itemsUseCase.update(itemToEdit, newState)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onUpdateComplete,
                        getViewState()::onError
                );
    }

    public void delete(Item item) {
        disposeSupplier(deleteSupplier);

        deleteSupplier = itemsUseCase.delete(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onDeleteComplete,
                        getViewState()::onError
                );
    }
}
