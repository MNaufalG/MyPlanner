package com.example.myplanner.ui.allNote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.CreateNoteActivity;
import com.example.myplanner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class AllNoteFragment extends Fragment {
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int RESULT_OK = -1;

    private AllNoteViewModel allNoteViewModel;
    private RecyclerView recyclerView;
    private ListAdapter list_adapter;
    private ArrayList<ListItem> listArrayListItem;

    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_note, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.text_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final ListAdapter adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        allNoteViewModel = ViewModelProviders.of(getActivity()).get(AllNoteViewModel.class);
        allNoteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<ListItem>>() {
            @Override
            public void onChanged(List<ListItem> listItems) {
                adapter.setNotes(listItems);
            }
        });

        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(CreateNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(CreateNoteActivity.EXTRA_DESC);
            int priority = data.getIntExtra(CreateNoteActivity.EXTRA_PRIORITY, 1);

//            try{
//                listItem = new ListItem (title, description, priority);
//                allNoteViewModel.insert(listItem);
//            } catch (NullPointerException ignored) {
//
//            }

            ListItem listItem = new ListItem(title,description,priority);
            allNoteViewModel.insert(listItem);

            Toast.makeText(getContext(),"Note Saved",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"Note Not Saved",Toast.LENGTH_SHORT).show();
        }
    }
}