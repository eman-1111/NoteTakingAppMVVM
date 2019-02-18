package com.example.notetakingappmvvm.ui.save_note;

import com.example.notetakingappmvvm.data.NoteRepository;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link com.example.notetakingappmvvm.data.NoteRepository}
 */
public class SaveNoteModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final NoteRepository mRepository;
    public SaveNoteModelFactory(NoteRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SaveNoteViewModel(mRepository);
    }


}