package com.example.myplanner.ui.allNote;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AllNoteViewModel extends AndroidViewModel {
    private ListRepository repository;
    private LiveData<List<ListItem>> allNotes;

    public AllNoteViewModel(@NonNull Application application) {
        super(application);
        repository = new ListRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(ListItem listItem) {
        repository.insert(listItem);
    }

    public void update(ListItem listItem) {
        repository.update(listItem);
    }

    public void delete(ListItem listItem) {
        repository.delete(listItem);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<ListItem>> getAllNotes() {
        return allNotes;
    }
//    private MutableLiveData<String> mText;
//
//    public AllNoteViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is All Note fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
}