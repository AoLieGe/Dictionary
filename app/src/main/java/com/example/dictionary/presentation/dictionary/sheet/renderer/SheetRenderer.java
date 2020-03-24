package com.example.dictionary.presentation.dictionary.sheet.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.R;
import com.example.dictionary.app.dictionary.SheetItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SheetRenderer extends RecyclerView.Adapter<SheetRenderer.SheetHolder> {
    private List<SheetItem> data;
    private OnSheetClickListener clickListener;
    private OnSheetLongClickListener longClickListener;

    public SheetRenderer(OnSheetClickListener listener, OnSheetLongClickListener longClickListener) {
        this.clickListener = listener;
        this.longClickListener = longClickListener;
    }

    public void setData(List<SheetItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SheetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sheet_item, parent, false);
        return new SheetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SheetHolder holder, int position) {
        SheetItem dictionary = data.get(position);
        holder.name.setText(dictionary.getName());
        holder.from.setText(dictionary.getLangFrom().toString());
        holder.to.setText(dictionary.getLangTo().toString());
        holder.separator.setText(" - ");
        holder.layout.setOnClickListener(v -> {
            clickListener.onSheetClick(dictionary);
        });
        holder.layout.setOnLongClickListener(v -> {
            longClickListener.onSheetLongClick(dictionary);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SheetHolder extends RecyclerView.ViewHolder {
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

        public SheetHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnSheetClickListener {
        void onSheetClick(SheetItem dictionary);
    }

    public interface OnSheetLongClickListener {
        void onSheetLongClick(SheetItem dictionary);
    }
}
