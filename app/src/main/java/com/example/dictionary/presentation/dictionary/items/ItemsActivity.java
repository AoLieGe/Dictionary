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

    private ItemsRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        getSupportActionBar().setTitle(R.string.dictionary_items_title);
        ButterKnife.bind(this);

        initRecycler();
        mPresenter.openDb(getTableName());
        mPresenter.supplyItemsSheet();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribeItemsSheet();
        mPresenter.closeDb();
    }

    private void initRecycler() {
        renderer = new ItemsRenderer(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);

        renderer.setData(new ArrayList<>());
        dictionaryItemSheet.setHasFixedSize(true);
        dictionaryItemSheet.setLayoutManager(manager);
        dictionaryItemSheet.setAdapter(renderer);
    }

    private String getTableName() {
        Intent intent = getIntent();
        SheetItem dictionary = intent.getParcelableExtra(Utils.DICTIONARY_OPEN_TAG);
        return dictionary.getName();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(Utils.TRANSLATE_MODE_TAG, Utils.TRANSLATE_ADD_TAG);
        startActivityForResult(intent, Utils.TRANSLATE_ACTIVITY_REQUEST_ADD);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( resultCode == RESULT_OK ) {
            switch (requestCode) {
                case Utils.TRANSLATE_ACTIVITY_REQUEST_ADD:
                    Item newItem = data.getParcelableExtra(Utils.TRANSLATE_RESULT_TAG);
                    mPresenter.insert(newItem);
                    break;

                case Utils.TRANSLATE_ACTIVITY_REQUEST_EDIT:
                    Item beforeEdit = data.getParcelableExtra(Utils.TRANSLATE_BEFORE_EDIT_TAG);
                    Item afterEdit = data.getParcelableExtra(Utils.TRANSLATE_RESULT_TAG);
                    mPresenter.update(beforeEdit, afterEdit);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(Item item) {
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(Utils.TRANSLATE_MODE_TAG, Utils.TRANSLATE_EDIT_TAG);
        intent.putExtra(Utils.TRANSLATE_EDIT_TAG, item);
        startActivityForResult(intent, Utils.TRANSLATE_ACTIVITY_REQUEST_EDIT);
    }

    @Override
    public void onItemLongClick(Item item) {
        mPresenter.delete(item);
    }

    @Override
    public void onGetItems(List<Item> data) {
        renderer.setData( data );
        renderer.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        Log.d(Utils.LOG_TAG, e.toString());
    }

    @Override
    public void onInsertComplete() {
        Log.d(Utils.LOG_TAG, "onInsertComplete");
    }

    @Override
    public void onUpdateComplete() {
        Log.d(Utils.LOG_TAG, "onUpdateComplete");
    }

    @Override
    public void onDeleteComplete() {
        Log.d(Utils.LOG_TAG, "onDeleteComplete");
    }
}
