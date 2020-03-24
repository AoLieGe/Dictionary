package com.example.dictionary.presentation.dictionary.items;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.dictionary.R;
import com.example.dictionary.app.Const;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.app.Utils;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.dictionary.items.ItemsUseCaseImpl;
import com.example.dictionary.presentation.dictionary.items.renderer.ItemsRenderer;
import com.example.dictionary.presentation.translate.TranslateActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsActivity extends MvpAppCompatActivity
        implements ItemsView,
        ItemsRenderer.OnItemClickListener,
        ItemsRenderer.OnItemLongClickListener {

    @BindView(R.id.dictionaryItemSheet)
    RecyclerView dictionaryItemSheet;

    @InjectPresenter
    ItemsPresenter mPresenter;

    @ProvidePresenter
    ItemsPresenter providePresenter() {
        return new ItemsPresenter(new ItemsUseCaseImpl());
    }

    private ItemsRenderer mRenderer;
    private SheetItem mParentDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        getSupportActionBar().setTitle(R.string.dictionary_items_title);
        ButterKnife.bind(this);

        getParentDictionary();
        initRecycler();
        mPresenter.openDb(mParentDictionary.getName());
        mPresenter.supplyItemsSheet();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribeItemsSheet();
        mPresenter.closeDb();
    }

    private void initRecycler() {
        mRenderer = new ItemsRenderer(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRenderer.setData(new ArrayList<>());
        dictionaryItemSheet.setHasFixedSize(true);
        dictionaryItemSheet.setLayoutManager(manager);
        dictionaryItemSheet.setAdapter(mRenderer);
    }

    private void getParentDictionary() {
        Intent intent = getIntent();
        mParentDictionary = intent.getParcelableExtra(Const.DICTIONARY_OPEN_TAG);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startTranslateActivity(Const.TRANSLATE_ADD_TAG, null);
        return super.onOptionsItemSelected(item);
    }

    private void startTranslateActivity(String modeTag, Item itemToEdit) {
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(Const.TRANSLATE_LANG_TAG, mParentDictionary);
        intent.putExtra(Const.TRANSLATE_MODE_TAG, modeTag);

        int requestCode;

        switch (modeTag) {
            case Const.TRANSLATE_EDIT_TAG:
                requestCode = Const.TRANSLATE_ACTIVITY_REQUEST_EDIT;
                intent.putExtra(Const.TRANSLATE_EDIT_TAG, itemToEdit);
                break;

            default:
            case Const.TRANSLATE_ADD_TAG:
                requestCode = Const.TRANSLATE_ACTIVITY_REQUEST_ADD;
                break;
        }

        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Const.TRANSLATE_ACTIVITY_REQUEST_ADD:
                    Item newItem = data.getParcelableExtra(Const.TRANSLATE_RESULT_TAG);
                    mPresenter.insert(newItem);
                    break;

                case Const.TRANSLATE_ACTIVITY_REQUEST_EDIT:
                    Item beforeEdit = data.getParcelableExtra(Const.TRANSLATE_BEFORE_EDIT_TAG);
                    Item afterEdit = data.getParcelableExtra(Const.TRANSLATE_RESULT_TAG);
                    mPresenter.update(beforeEdit, afterEdit);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(Item item) {
        startTranslateActivity(Const.TRANSLATE_EDIT_TAG, item);
    }

    @Override
    public void onItemLongClick(Item item) {
        mPresenter.delete(item);
    }

    @Override
    public void onGetItems(List<Item> data) {
        mRenderer.setData(data);
        mRenderer.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        Log.d(Const.LOG_TAG, e.toString());
    }

    @Override
    public void onInsertComplete() {
        Log.d(Const.LOG_TAG, "onInsertComplete");
    }

    @Override
    public void onUpdateComplete() {
        Log.d(Const.LOG_TAG, "onUpdateComplete");
    }

    @Override
    public void onDeleteComplete() {
        Log.d(Const.LOG_TAG, "onDeleteComplete");
    }
}
