package com.example.dictionary.presentation.dictionary.items;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.domain.dictionary.items.ItemsUseCase;

import java.util.List;

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

    public ItemsPresenter(ItemsUseCase itemsUseCase) {
        this.itemsUseCase = itemsUseCase;
    }

    public void openDb(String dictionaryTableName) {
        itemsUseCase.openDb(dictionaryTableName);
    }

    public void closeDb() {
        itemsUseCase.closeDb();
    }

    public void supplyItemsSheet() {
        unsubscribeItemsSheet();

        itemsSupplier = itemsUseCase.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onGetItems,
                        getViewState()::onError
                );
    }

    public void unsubscribeItemsSheet() {
        unsubscribeSupplier(itemsSupplier);
    }

    private void unsubscribeSupplier(Disposable supplier) {
        if(supplier != null && !supplier.isDisposed()) {
            supplier.dispose();
            supplier = null;
        }
    }

    public void insert(Item item) {
        unsubscribeSupplier(insertSupplier);

        insertSupplier = itemsUseCase.insert(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onInsertComplete,
                        getViewState()::onError
                );
    }

    public void update(Item itemToEdit, Item newState) {
        unsubscribeSupplier(updateSupplier);

        updateSupplier = itemsUseCase.update(itemToEdit, newState)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onUpdateComplete,
                        getViewState()::onError
                );
    }

    public void delete(Item item) {
        unsubscribeSupplier(deleteSupplier);

        deleteSupplier = itemsUseCase.delete(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        getViewState()::onDeleteComplete,
                        getViewState()::onError
                );
    }
}
