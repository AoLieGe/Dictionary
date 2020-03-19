package com.example.dictionary.presentation.dictionary.items.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.app.DictionaryViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.dictionary.R;

import java.util.List;

public class DictionaryItemRenderer extends RecyclerView.Adapter<DictionaryItemRenderer.DictionaryItemHolder> {
    private List<DictionaryViewItem> data;
    private OnDictionaryItemClickListener clickListener;
    private OnDictionaryItemLongClickListener longClickListener;

    public DictionaryItemRenderer(OnDictionaryItemClickListener clickListener, OnDictionaryItemLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void setData(List<DictionaryViewItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DictionaryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dictionary_item, parent, false);
        return new DictionaryItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryItemHolder holder, int position) {
        DictionaryViewItem item = data.get(position);

        holder.word.setText(item.getWord());
        holder.tranlation.setText(item.getTranslation());
        holder.layout.setOnClickListener(v -> {
            clickListener.onDictionaryOneClick(item);
        });
        holder.layout.setOnLongClickListener(v -> {
            longClickListener.onDictionaryOneLongClick(item);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DictionaryItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dictionaryLayout)
        LinearLayout layout;
        @BindView(R.id.dictionaryWord)
        TextView word;
        @BindView(R.id.dictionaryTranslation)
        TextView tranlation;

        public DictionaryItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnDictionaryItemClickListener {
        void onDictionaryOneClick(DictionaryViewItem item);
    }

    public interface OnDictionaryItemLongClickListener {
        void onDictionaryOneLongClick(DictionaryViewItem item);
    }

}
