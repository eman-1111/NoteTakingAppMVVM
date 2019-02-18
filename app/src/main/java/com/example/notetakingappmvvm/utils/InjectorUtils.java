package com.example.notetakingappmvvm.utils;

import android.content.Context;

import com.example.notetakingappmvvm.AppExecutors;
import com.example.notetakingappmvvm.data.NoteDatabase;
import com.example.notetakingappmvvm.data.NoteRepository;
import com.example.notetakingappmvvm.ui.note_list.NoteListModelFactory;
import com.example.notetakingappmvvm.ui.save_note.SaveNoteModelFactory;

public class InjectorUtils {

    private static NoteRepository provideRepository(Context context) {
        NoteDatabase database = NoteDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();

        //todo create NetworkDataSource
        return NoteRepository.getInstance(database.noteDao(), executors);
    }

    public static NoteListModelFactory provideNoteListViewModelFactory(Context context) {
        NoteRepository repository = provideRepository(context.getApplicationContext());
        return new NoteListModelFactory(repository);
    }

    public static SaveNoteModelFactory provideSaveNoteViewModelFactory(Context context) {
        NoteRepository repository = provideRepository(context.getApplicationContext());
        return new SaveNoteModelFactory(repository);
    }



}
