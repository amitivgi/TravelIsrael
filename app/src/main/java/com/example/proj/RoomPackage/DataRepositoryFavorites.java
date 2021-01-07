package com.example.proj.RoomPackage;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DataRepositoryFavorites {

    private DataDaoFavorites dataDaoFavorites;
    private LiveData<List<DataFavorites>> allDataFavorites;

    public DataRepositoryFavorites(Application application) {
        DataRoomDatabaseFavorites db = DataRoomDatabaseFavorites.getDatabase(application);
        dataDaoFavorites = db.dataDao();
        allDataFavorites = dataDaoFavorites.getAllDataFavorites();
    }

    public LiveData<List<DataFavorites>> getAllDataFavorites() {
        return allDataFavorites;
    }

    private static class DeleteLastSearchAsyncTask extends AsyncTask<Void, Void, Void> {

        private DataDaoFavorites dataDaoFavorites;

        private DeleteLastSearchAsyncTask(DataDaoFavorites dao) {
            dataDaoFavorites = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dataDaoFavorites.deleteAll();
            return null;
        }
    }

    void deleteLastSearch() {
        DeleteLastSearchAsyncTask deleteLastSearchAsyncTask = new DeleteLastSearchAsyncTask(dataDaoFavorites);
        deleteLastSearchAsyncTask.execute();
    }

    private static class deleteDataAsyncTask extends AsyncTask<DataFavorites, Void, Void> {

        private DataDaoFavorites dataDaoFavorites;

        private deleteDataAsyncTask(DataDaoFavorites dao) {
            dataDaoFavorites = dao;
        }

        @Override
        protected Void doInBackground(final DataFavorites... params) {
            dataDaoFavorites.delete(params[0]);
            return null;
        }
    }

    void deleteData(DataFavorites dataFavorites) {
        new deleteDataAsyncTask(dataDaoFavorites).execute(dataFavorites);
    }

    private static class insertAsyncTask extends AsyncTask<DataFavorites, Void, Void> {

        private DataDaoFavorites dataDaoFavorites;

        private insertAsyncTask(DataDaoFavorites dao) {
            dataDaoFavorites = dao;
        }

        @Override
        protected Void doInBackground(final DataFavorites... params) {
            try {
                dataDaoFavorites.insert(params[0]);
            } catch (Exception e) {

            }
            return null;
        }
    }

    private void insertData(DataFavorites dataFavorites) {
        new insertAsyncTask(dataDaoFavorites).execute(dataFavorites);
    }

    public void insertData(List<DataFavorites> dataFavoritesList) {
        for (DataFavorites p : dataFavoritesList) {
            insertData(p);
        }
    }

}
