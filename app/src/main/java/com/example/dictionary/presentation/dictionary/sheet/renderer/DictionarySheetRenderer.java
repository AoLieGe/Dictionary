package com.example.dictionary.presentation.dictionary.sheet.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dictionary.R;
import com.example.dictionary.app.DictionaryView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionarySheetRenderer extends RecyclerView.Adapter<DictionarySheetRenderer.DictionarySheetHolder> {
    private List<DictionaryView> data;
    private OnDictionaryClickListener clickListener;
    private OnDictionaryLongClickListener longClickListener;

    public DictionarySheetRenderer(OnDictionaryClickListener listener, OnDictionaryLongClickListener longClickListener) {
        this.clickListener = listener;
        this.longClickListener = longClickListener;
    }

    public void setData(List<DictionaryView> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DictionarySheetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dictionaries_list_item, parent, false);
        return new DictionarySheetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionarySheetHolder holder, int position) {
        DictionaryView dictionary = data.get(position);
        holder.name.setText(dictionary.getName());
        holder.from.setText(dictionary.getLangFrom().toString());
        holder.to.setText(dictionary.getLangTo().toString());
        holder.separator.setText(" - ");
        holder.layout.setOnClickListener(v -> {
            clickListener.onDictionaryClick(dictionary);
        });
        holder.layout.setOnLongClickListener(v -> {
            longClickListener.onDictionaryLongClick(dictionary);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DictionarySheetHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dictionariesListLayout)
        LinearLayout layout;
        @BindView(R.id.dictionariesListName)
        TextView name;
        @BindView(R.id.dictionariesFromLanguage)
        TextView from;
        @BindView(R.id.dictionariesToLanguage)
        TextView to;
        @BindView(R.id.dictionariesLangSeparator)
        TextView separator;

        public DictionarySheetHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnDictionaryClickListener {
        void onDictionaryClick(DictionaryView dictionary);
    }

    public interface OnDictionaryLongClickListener {
        void onDictionaryLongClick(DictionaryView dictionary);
    }
}
