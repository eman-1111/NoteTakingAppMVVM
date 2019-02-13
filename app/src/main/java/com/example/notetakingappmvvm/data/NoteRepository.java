package com.example.notetakingappmvvm.data;

import android.app.Application;
import android.util.Log;

import com.example.notetakingappmvvm.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private static final String LOG_TAG = NoteRepository.class.getSimpleName();

    //for Singleton instantiation
    private static final Object LOCK = new Object();
    private static NoteRepository sInstance;
    private final NoteDao mNoteDao;
    private final AppExecutors mAppExecutors;

    private NoteRepository(NoteDao mNoteDao, AppExecutors mAppExecutors) {
        this.mNoteDao = mNoteDao;
        this.mAppExecutors = mAppExecutors;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        //todo

    }

    public synchronized static NoteRepository getInstance(NoteDao mNoteDao,  AppExecutors mAppExecutors) {

        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NoteRepository(mNoteDao, mAppExecutors);
                Log.d(LOG_TAG, "Made new repository");

            }
        }
        return sInstance;
    }



    public void delete(final Note note){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.delete(note);
            }
        });
    }
    public void deleteAll(){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.deleteAll();
            }
        });
    }

    public void update(final Note note){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.update(note);
            }
        });
    }

    public void insert(final Note note){
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mNoteDao.insert(note);
            }
        });
    }

    public LiveData<List<Note>> getNoteList(){
        return mNoteDao.getAllNote();
    }
}
