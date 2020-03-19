package com.example.dictionary.presentation.dictionary.sheet;

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
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.Utils;
import com.example.dictionary.domain.dictionary.sheet.DictionarySheetUseCase;
import com.example.dictionary.domain.dictionary.sheet.MockDictionarySheetUseCaseImpl;
import com.example.dictionary.presentation.dictionary.sheet.renderer.DictionarySheetRenderer;
import com.example.dictionary.presentation.dictionary.add.*;
import com.example.dictionary.presentation.dictionary.items.*;
import com.example.dictionary.presentation.translate.TranslateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionarySheetActivity extends AppCompatActivity
        implements DictionarySheetRenderer.OnDictionaryClickListener,
        DictionarySheetRenderer.OnDictionaryLongClickListener {

    @BindView(R.id.dictionariesSheet)
    RecyclerView dictionariesSheet;

    private DictionarySheetUseCase dictionarySheetUseCase = new MockDictionarySheetUseCaseImpl();
    private DictionarySheetRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_sheet);
        getSupportActionBar().setTitle(R.string.dictionary_sheet_title);
        ButterKnife.bind(this);

        initRecycler();
    }

    private void initRecycler() {
        renderer = new DictionarySheetRenderer(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);

        dictionariesSheet.setHasFixedSize(true);
        dictionariesSheet.setLayoutManager(manager);
        dictionariesSheet.setAdapter(renderer);

        UpdateSheet();
    }

    private void UpdateSheet() {
        renderer.setData( dictionarySheetUseCase.getAll() );
        renderer.notifyDataSetChanged();
    }

    private void openDictionary(DictionaryView dictionary) {
        Intent intent = new Intent(this, DictionaryItemsActivity.class);
        intent.putExtra(Utils.DICTIONARY_OPEN_TAG, dictionary);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, DictionaryAddActivity.class);
        startActivityForResult(intent, 1);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( resultCode == RESULT_OK ) {
            //TODO add new dictionary
            DictionaryView dictionary = data.getParcelableExtra(Utils.DICTIONARY_NEW_TAG);
            dictionarySheetUseCase.add(dictionary);
            UpdateSheet();
            openDictionary(dictionary);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDictionaryClick(DictionaryView dictionary) {
        //TODO open selected dictionary
        openDictionary(dictionary);
    }

    @Override
    public void onDictionaryLongClick(DictionaryView dictionary) {
        dictionarySheetUseCase.delete(dictionary);
        UpdateSheet();
    }
}
