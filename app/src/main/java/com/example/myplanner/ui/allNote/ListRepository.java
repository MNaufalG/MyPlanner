package com.example.myplanner.ui.allNote;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class ListRepository {
    private ListDao listDao;
    private LiveData<List<ListItem>> allNotes;

    public ListRepository(Application application) {
        ListDatabase database = ListDatabase.getInstance(application);
        listDao = database.listDao();
        allNotes = listDao.getAllNotes();
    }

    public void insert (ListItem listItem) {
        new InsertListItemAsyncTask(listDao).execute(listItem);
    }

    public void update (ListItem listItem) {
        new UpdateListItemAsyncTask(listDao).execute(listItem);
    }

    public void delete (ListItem listItem) {
        new DeleteListItemAsyncTask(listDao).execute(listItem);
    }

    public void deleteAllNotes() {
        new DeleteAllListItemAsyncTask(listDao).execute();
    }

    public LiveData<List<ListItem>> getAllNotes() {
        return allNotes;
    }

    private static class InsertListItemAsyncTask extends AsyncTask<ListItem, Void, Void> {
        private ListDao listDao;

        private InsertListItemAsyncTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(ListItem... listItems) {
            listDao.insert(listItems[0]);
            return null;
        }
    }

    private static class UpdateListItemAsyncTask extends AsyncTask<ListItem, Void, Void> {
        private ListDao listDao;

        private UpdateListItemAsyncTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(ListItem... listItems) {
            listDao.update(listItems[0]);
            return null;
        }
    }

    private static class DeleteListItemAsyncTask extends AsyncTask<ListItem, Void, Void> {
        private ListDao listDao;

        private DeleteListItemAsyncTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(ListItem... listItems) {
            listDao.delete(listItems[0]);
            return null;
        }
    }

    private static class DeleteAllListItemAsyncTask extends AsyncTask<Void, Void, Void> {
        private ListDao listDao;

        private DeleteAllListItemAsyncTask(ListDao listDao) {
            this.listDao = listDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            listDao.deleteAllNotes();
            return null;
        }
    }
}
