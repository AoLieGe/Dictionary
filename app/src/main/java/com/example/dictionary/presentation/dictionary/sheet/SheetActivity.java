package com.example.dictionary.presentation.dictionary.sheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.dictionary.R;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.app.Utils;
import com.example.dictionary.domain.dictionary.sheet.SheetUseCaseImpl;
import com.example.dictionary.presentation.dictionary.sheet.renderer.SheetRenderer;
import com.example.dictionary.presentation.dictionary.add.*;
import com.example.dictionary.presentation.dictionary.items.*;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SheetActivity extends MvpAppCompatActivity
        implements SheetView,
        SheetRenderer.OnSheetClickListener,
        SheetRenderer.OnSheetLongClickListener {

    @BindView(R.id.dictionariesSheet)
    RecyclerView dictionariesSheet;

    @InjectPresenter
    SheetPresenter mPresenter;

    @ProvidePresenter
    SheetPresenter providePresenter() {
        return new SheetPresenter(new SheetUseCaseImpl());
    }

    private SheetRenderer renderer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_sheet);
        getSupportActionBar().setTitle(R.string.dictionary_sheet_title);
        ButterKnife.bind(this);

        initRecycler();
        mPresenter.supplyDictionarySheet();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubsribeDictionarySheet();
    }

    private void initRecycler() {
        renderer = new SheetRenderer(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        renderer.setData(new ArrayList<>());
        dictionariesSheet.setHasFixedSize(true);
        dictionariesSheet.setLayoutManager(manager);
        dictionariesSheet.setAdapter(renderer);
    }

    private void openDictionary(SheetItem dictionary) {
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
        if (resultCode == RESULT_OK) {
            //TODO insert new dictionary
            SheetItem dictionary = data.getParcelableExtra(Utils.DICTIONARY_NEW_TAG);
            mPresenter.insert(dictionary);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSheetClick(SheetItem dictionary) {
        //TODO open selected dictionary
        openDictionary(dictionary);
    }

    @Override
    public void onSheetLongClick(SheetItem dictionary) {
        mPresenter.delete(dictionary);
    }

    @Override
    public void onUpdateSheet(List<SheetItem> data) {
        renderer.setData(data);
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
    public void onDeleteComplete() {
        Log.d(Utils.LOG_TAG, "onDeleteComplete");
    }
}
