package com.example.dictionary.presentation.dictionary.items.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.app.dictionary.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.dictionary.R;

import java.util.List;

public class ItemsRenderer extends RecyclerView.Adapter<ItemsRenderer.ItemHolder> {
    private List<Item> data;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public ItemsRenderer(OnItemClickListener clickListener, OnItemLongClickListener longClickListener) {
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dictionary_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = data.get(position);

        holder.word.setText(item.getWord());
        holder.tranlation.setText(item.getTranslation());
        holder.layout.setOnClickListener(v -> {
            clickListener.onItemClick(item);
        });
        holder.layout.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(item);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dictionaryLayout)
        LinearLayout layout;
        @BindView(R.id.dictionaryWord)
        TextView word;
        @BindView(R.id.dictionaryTranslation)
        TextView tranlation;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Item item);
    }

}
