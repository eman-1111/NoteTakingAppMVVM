package com.example.notetakingappmvvm.ui.note_list;

import com.example.notetakingappmvvm.data.Note;
import com.example.notetakingappmvvm.data.NoteRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NoteListViewModel extends ViewModel {
    private LiveData<List<Note>> allNotes;

    NoteRepository mRepository;
    public NoteListViewModel(NoteRepository repository) {
        mRepository = repository;
        allNotes = repository.getNoteList();
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }

    public void update(Note note) {
        mRepository.update(note);
    }

    public void delete(Note note) {
        mRepository.delete(note);
    }

    public void deleteAllNotes() {
        mRepository.deleteAll();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mRepository.getNoteList();
    }
}

