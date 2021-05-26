package com.example.myplanner.ui.allNote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {
    private List<ListItem> listItems = new ArrayList<>();

    @NonNull
    @Override
    public ListHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list, parent, false);
        return new ListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        ListItem currentListItem = listItems.get(position);
        holder.tvPriority.setText(String.valueOf(currentListItem.getPriority()));
        holder.tvTitle.setText(currentListItem.getTitle());
        holder.tvDescription.setText(currentListItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void setNotes(List<ListItem> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        private TextView tvPriority,tvTitle, tvDescription;

        public ListHolder (View itemView){
            super (itemView);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
