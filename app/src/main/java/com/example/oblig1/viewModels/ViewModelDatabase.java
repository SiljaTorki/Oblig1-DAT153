package com.example.oblig1.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.repository.Repository;

import java.util.List;

public class ViewModelDatabase extends AndroidViewModel {
    public Repository mRepository;

    private LiveData<List<Cat>> mAllCats;

    public ViewModelDatabase (Application application) {
        super(application);
        mRepository = new Repository(application);
        mAllCats = mRepository.getAllLive();
    }

    public LiveData<List<Cat>> getAllLive() { return mAllCats; }

    public void insert(Cat cat) { mRepository.insert(cat); }

    public void delete(Cat cat) { mRepository.delete(cat); }

}
