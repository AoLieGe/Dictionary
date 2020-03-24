package com.example.dictionary.presentation.dictionary.sheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.dictionary.R;
import com.example.dictionary.app.Const;
import com.example.dictionary.app.dictionary.SheetItem;
import com.example.dictionary.domain.dictionary.items.ItemsUseCaseImpl;
import com.example.dictionary.domain.dictionary.sheet.SheetUseCaseImpl;
import com.example.dictionary.presentation.dictionary.add.AddActivity;
import com.example.dictionary.presentation.dictionary.items.ItemsActivity;
import com.example.dictionary.presentation.dictionary.sheet.renderer.SheetRenderer;

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
    SheetPresenter presenter;

    @ProvidePresenter
    SheetPresenter providePresenter() {
        return new SheetPresenter(new SheetUseCaseImpl(), new ItemsUseCaseImpl());
    }

    private SheetRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_sheet);
        getSupportActionBar().setTitle(R.string.dictionary_sheet_title);
        ButterKnife.bind(this);

        initRecycler();
        presenter.supplyDictionarySheet();
    }

    private void initRecycler() {
        renderer = new SheetRenderer(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        renderer.setData(new ArrayList<>());
        dictionariesSheet.setHasFixedSize(true);
        dictionariesSheet.setLayoutManager(manager);
        dictionariesSheet.setAdapter(renderer);
    }

    private void openDictionary(SheetItem dictionary) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra(Const.DICTIONARY_OPEN_TAG, dictionary);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivityForResult(intent, 1);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            SheetItem dictionary = data.getParcelableExtra(Const.DICTIONARY_NEW_TAG);
            presenter.insert(dictionary);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSheetClick(SheetItem dictionary) {
        openDictionary(dictionary);
    }

    @Override
    public void onSheetLongClick(SheetItem dictionary) {
        presenter.delete(dictionary);
    }

    @Override
    public void onGetSheet(List<SheetItem> data) {
        renderer.setData(data);
        renderer.notifyDataSetChanged();
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
    public void onDeleteComplete() {
        Log.d(Const.LOG_TAG, "onDeleteComplete");
    }
}
