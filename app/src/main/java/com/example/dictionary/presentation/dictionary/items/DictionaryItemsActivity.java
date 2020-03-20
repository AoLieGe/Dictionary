package com.example.dictionary.presentation.dictionary.items;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.dictionary.R;
import com.example.dictionary.app.dictionary.Item;
import com.example.dictionary.app.Utils;
import com.example.dictionary.domain.dictionary.items.MockItemUseCaseImpl;
import com.example.dictionary.presentation.dictionary.items.renderer.DictionaryItemRenderer;
import com.example.dictionary.presentation.translate.TranslateActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryItemsActivity extends MvpAppCompatActivity
implements DictionaryItemsView,
        DictionaryItemRenderer.OnDictionaryItemClickListener,
        DictionaryItemRenderer.OnDictionaryItemLongClickListener {

    @BindView(R.id.dictionaryItemSheet)
    RecyclerView dictionaryItemSheet;

    @InjectPresenter
    DictionaryItemsPresenter mPresenter;

    @ProvidePresenter
    DictionaryItemsPresenter providePresenter() {
        return new DictionaryItemsPresenter(new MockItemUseCaseImpl());
    }

    private DictionaryItemRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        getSupportActionBar().setTitle(R.string.dictionary_items_title);
        ButterKnife.bind(this);

        initRecycler();
    }

    private void initRecycler() {
        renderer = new DictionaryItemRenderer(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);

        dictionaryItemSheet.setHasFixedSize(true);
        dictionaryItemSheet.setLayoutManager(manager);
        dictionaryItemSheet.setAdapter(renderer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getAll();
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
                    //TODO insert new item to dictionary
                    Item newItem = data.getParcelableExtra(Utils.TRANSLATE_RESULT_TAG);
                    mPresenter.add(newItem);
                    break;

                case Utils.TRANSLATE_ACTIVITY_REQUEST_EDIT:
                    //TODO edit item
                    Item beforeEdit = data.getParcelableExtra(Utils.TRANSLATE_BEFORE_EDIT_TAG);
                    Item afterEdit = data.getParcelableExtra(Utils.TRANSLATE_RESULT_TAG);
                    mPresenter.edit(beforeEdit, afterEdit);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDictionaryOneClick(Item item) {
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(Utils.TRANSLATE_MODE_TAG, Utils.TRANSLATE_EDIT_TAG);
        intent.putExtra(Utils.TRANSLATE_EDIT_TAG, item);
        startActivityForResult(intent, Utils.TRANSLATE_ACTIVITY_REQUEST_EDIT);
    }

    @Override
    public void onDictionaryOneLongClick(Item item) {
        mPresenter.delete(item);
    }

    @Override
    public void onUpdateSheet(List<Item> data) {
        renderer.setData( data );
        renderer.notifyDataSetChanged();
    }
}
