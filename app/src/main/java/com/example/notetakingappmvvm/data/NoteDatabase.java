package com.example.notetakingappmvvm.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class }, version = 1)
public abstract  class NoteDatabase  extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();



    private static final String LOG_TAG = NoteDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "note_table";

    private static final Object LOCK = new Object();
    private static volatile NoteDatabase sInstance;

    public static NoteDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                if(sInstance == null){
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, NoteDatabase.DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}
