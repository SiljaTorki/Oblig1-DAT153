package com.example.oblig1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.oblig1.domain.Cat;
import com.example.oblig1.sqlDAOs.CatDao;
import com.example.oblig1.sqlLite.AppDatabase;
import com.example.oblig1.sqlLite.DatabaseClient;

import java.util.List;

public class Repository {

    private CatDao mCatDao;
    private LiveData<List<Cat>> mAllCats;
    private AppDatabase catDatabase;

    public Repository(Application application) {
        DatabaseClient clientDB = DatabaseClient.getInstance(application);
        catDatabase = clientDB.getAppDatabase();
        mCatDao= catDatabase.catDao();
        mAllCats = mCatDao.getAllLive();
    }

    public LiveData<List<Cat>> getAllLive() {
        return mAllCats;
    }

    public void insert (Cat cat) {
        new insertAsyncTask(mCatDao).execute(cat);
    }

    public void delete (Cat cat) {
        new deleteAsyncTask(mCatDao).execute(cat);
    }

    private static class insertAsyncTask extends AsyncTask<Cat, Void, Void> {

        private CatDao mAsyncTaskDao;

        insertAsyncTask(CatDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cat... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Cat, Void, Void> {

        private CatDao mAsyncTaskDao;

        deleteAsyncTask(CatDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cat... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
