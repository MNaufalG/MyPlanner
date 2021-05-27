package com.example.myplanner.ui.allNote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplanner.CreateNoteActivity;
import com.example.myplanner.MainActivity;
import com.example.myplanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AllNoteFragment extends Fragment {
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int RESULT_OK = -1;

    private AllNoteViewModel allNoteViewModel;
    private RecyclerView recyclerView;
    private NoteListAdapter list_adapter;
    private ArrayList<ListItem> listArrayListItem;

    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_note, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.text_recycleView);

        final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getContext(), CreateNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

//        allNoteViewModel = ViewModelProviders.of(getActivity()).get(AllNoteViewModel.class);
//        allNoteViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(AllNoteViewModel.class);
        allNoteViewModel = new ViewModelProvider(this).get(AllNoteViewModel.class);
        allNoteViewModel.getAllNotes().observe(getViewLifecycleOwner(), listItems -> {
            adapter.submitList(listItems);
        });


        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TEST", "test");

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(CreateNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(CreateNoteActivity.EXTRA_DESC);
            int priority = data.getIntExtra(CreateNoteActivity.EXTRA_PRIORITY, 1);

            Log.d("TEST", title);
            Log.d("TEST", description);
            Log.d("TEST", String.valueOf(priority));

            ListItem listItem = new ListItem(title,description,priority);
            allNoteViewModel.insert(listItem);

            Toast.makeText(getContext(),"Note Saved",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(),"Note Not Saved",Toast.LENGTH_SHORT).show();
        }
    }
}