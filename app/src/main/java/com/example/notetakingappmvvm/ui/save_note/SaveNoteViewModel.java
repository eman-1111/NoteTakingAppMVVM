package com.example.notetakingappmvvm.ui.save_note;

import com.example.notetakingappmvvm.data.Note;
import com.example.notetakingappmvvm.data.NoteRepository;

import androidx.lifecycle.ViewModel;

public class SaveNoteViewModel extends ViewModel {

    NoteRepository mRepository;
    public SaveNoteViewModel(NoteRepository repository) {
        mRepository = repository;
    }

    public void insert(Note note) {
        mRepository.insert(note);
    }



    private MyCustomCallback callback;
    public void saveNote(MyCustomCallback mCallback, String title, String description, int priority){

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            mCallback.actionFailed();

        }else{
            Note note = new Note(title, description, priority);
            insert(note);
            mCallback.actionIsSuccessful();
        }
    }
    public void setCallback(MyCustomCallback callback){
        this.callback = callback;
    }
    public MyCustomCallback getCallback(){
        return this.callback;
    }
    public interface MyCustomCallback{
        void actionIsSuccessful();
        void actionFailed();
    }
}

