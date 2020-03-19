package com.example.dictionary.presentation.translate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dictionary.R;
import com.example.dictionary.app.DictionaryView;
import com.example.dictionary.app.DictionaryViewItem;
import com.example.dictionary.app.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.dictionary.app.Utils.validateTranslation;

public class TranslateActivity extends AppCompatActivity {
    @BindView(R.id.translateWord)
    EditText word;
    @BindView(R.id.translateTranslation)
    EditText translation;
    @BindView(R.id.translateButton)
    Button button;

    private String mMode;
    private DictionaryViewItem itemBeforeEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        readIntent();
    }

    private void readIntent() {
        Intent intent = getIntent();
        mMode = intent.getStringExtra(Utils.TRANSLATE_MODE_TAG);

        switch (mMode) {
            case Utils.TRANSLATE_EDIT_TAG:
                button.setText(R.string.translate_edit_button_text);

                itemBeforeEdit = getIntent().getParcelableExtra(Utils.TRANSLATE_EDIT_TAG);
                word.setText(itemBeforeEdit.getWord());
                translation.setText(itemBeforeEdit.getTranslation());
                break;

            case Utils.TRANSLATE_ADD_TAG:
                button.setText(R.string.translate_add_button_text);
                break;
        }
    }

    @OnClick(R.id.translateButton)
    protected void onButtonClick() {
        String word = this.word.getText().toString();
        String translation = this.translation.getText().toString();

        Integer validateRes = validateTranslation(word, translation, this.itemBeforeEdit);
        if (null != validateRes) {
            Toast.makeText(this, validateRes, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();

        switch (mMode) {
            case Utils.TRANSLATE_EDIT_TAG:
                intent.putExtra(Utils.TRANSLATE_BEFORE_EDIT_TAG, itemBeforeEdit);
                break;
        }

        intent.putExtra(Utils.TRANSLATE_RESULT_TAG, new DictionaryViewItem(word, translation));
        setResult(RESULT_OK, intent);
        finish();
    }
}
