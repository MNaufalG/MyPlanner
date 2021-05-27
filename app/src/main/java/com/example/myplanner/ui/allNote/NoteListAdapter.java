package com.example.myplanner.ui.allNote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.R;

public class NoteListAdapter extends ListAdapter<ListItem, NoteListAdapter.ViewHolder> {

    public NoteListAdapter(DiffUtil.ItemCallback<ListItem> diffCallback) {
        super(diffCallback);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.to_do_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = getItem(position) ;
        holder.tvPriority.setText(String.valueOf(listItem.getPriority()));
        holder.tvTitle.setText(listItem.getTitle());
        holder.tvDescription.setText(listItem.getDescription());
    }

    static class WordDiff extends DiffUtil.ItemCallback<ListItem> {
        @Override
        public boolean areItemsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPriority,tvTitle, tvDescription;

        public ViewHolder (View itemView){
            super (itemView);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}