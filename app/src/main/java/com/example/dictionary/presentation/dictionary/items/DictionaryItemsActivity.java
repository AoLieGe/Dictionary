package com.example.dictionary.presentation.dictionary.items;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dictionary.R;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.app.Utils;
import com.example.dictionary.domain.dictionary.items.DictionaryItemUseCase;
import com.example.dictionary.domain.dictionary.items.MockDictionaryItemUseCaseImpl;
import com.example.dictionary.presentation.dictionary.items.renderer.DictionaryItemRenderer;
import com.example.dictionary.presentation.translate.TranslateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryItemsActivity extends AppCompatActivity
implements DictionaryItemRenderer.OnDictionaryItemClickListener,
        DictionaryItemRenderer.OnDictionaryItemLongClickListener {

    @BindView(R.id.dictionaryItemSheet)
    RecyclerView dictionaryItemSheet;

    private DictionaryItemRenderer renderer;
    private DictionaryItemUseCase dictionaryItemUseCase = new MockDictionaryItemUseCaseImpl();

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

        UpdateSheet();
    }

    private void UpdateSheet() {
        renderer.setData( dictionaryItemUseCase.getAll() );
        renderer.notifyDataSetChanged();
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
                    //TODO add new item to dictionary
                    DictionaryViewItem newItem = data.getParcelableExtra(Utils.TRANSLATE_RESULT_TAG);
                    dictionaryItemUseCase.add(newItem);
                    UpdateSheet();
                    break;

                case Utils.TRANSLATE_ACTIVITY_REQUEST_EDIT:
                    //TODO edit item
                    DictionaryViewItem beforeEdit = data.getParcelableExtra(Utils.TRANSLATE_BEFORE_EDIT_TAG);
                    DictionaryViewItem afterEdit = data.getParcelableExtra(Utils.TRANSLATE_RESULT_TAG);
                    dictionaryItemUseCase.edit(beforeEdit, afterEdit);
                    UpdateSheet();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDictionaryOneClick(DictionaryViewItem item) {
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(Utils.TRANSLATE_MODE_TAG, Utils.TRANSLATE_EDIT_TAG);
        intent.putExtra(Utils.TRANSLATE_EDIT_TAG, item);
        startActivityForResult(intent, Utils.TRANSLATE_ACTIVITY_REQUEST_EDIT);
    }

    @Override
    public void onDictionaryOneLongClick(DictionaryViewItem item) {
        //TODO delete item from dictionary
        dictionaryItemUseCase.delete(item);
        UpdateSheet();
    }
}
