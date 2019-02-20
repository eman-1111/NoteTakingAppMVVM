package com.example.notetakingappmvvm.ui.edit_note;

import com.example.notetakingappmvvm.data.Note;
import com.example.notetakingappmvvm.data.NoteRepository;

import androidx.lifecycle.ViewModel;

public class EditNoteViewModel extends ViewModel {
    NoteRepository mRepository;
    public EditNoteViewModel(NoteRepository repository) {
        mRepository = repository;
    }

    private void update(Note note) {
        mRepository.update(note);
    }



    private MyCustomCallback callback;
    public void editNote(MyCustomCallback mCallback,int id, String title, String description, int priority){

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            mCallback.actionFailed();

        }else{
            Note note = new Note(title, description, priority);
            if(id != -1)
                note.setId(id);

            update(note);
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
