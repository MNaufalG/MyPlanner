package com.example.myplanner.ui.allNote;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ListDao {

    @Insert
    void insert (ListItem listItem);

    @Update
    void update (ListItem listItem);

    @Delete
    void delete (ListItem listItem);

    @Query("DELETE FROM list_table")
    void deleteAllNotes();

    @Query("SELECT * FROM list_table ORDER BY priority DESC")
    LiveData<List<ListItem>> getAllNotes();
}
