package com.example.proj.RoomPackage;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DataViewModelFavorites extends AndroidViewModel {

    private DataRepositoryFavorites dataRepositoryFavorites;
    private LiveData<List<DataFavorites>> listLiveData;

    public DataViewModelFavorites(Application application) {
        super(application);

        dataRepositoryFavorites = new DataRepositoryFavorites(application);
        listLiveData = dataRepositoryFavorites.getAllDataFavorites();
    }

    public LiveData<List<DataFavorites>> getAllDataFavorites() {
        return listLiveData;
    }

    public void insertData(List<DataFavorites> dataFavorites) {
        dataRepositoryFavorites.insertData(dataFavorites);
    }

    public void deleteAll() {
        dataRepositoryFavorites.deleteLastSearch();
    }

    public void deleteData(DataFavorites dataFavorites) {
        dataRepositoryFavorites.deleteData(dataFavorites);
    }

}
