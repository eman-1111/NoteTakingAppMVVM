package com.example.notetakingappmvvm.ui.note_list;

import android.os.Bundle;
import android.widget.Toast;

import com.example.notetakingappmvvm.R;
import com.example.notetakingappmvvm.data.Note;
import com.example.notetakingappmvvm.utils.InjectorUtils;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    NoteListViewModel mViewModel;
    private LiveData<List<Note>> allNotes;
    RecyclerView recyclerView;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecycler();
        NoteListModelFactory factory = InjectorUtils.provideNoteListViewModelFactory(this);
        mViewModel = ViewModelProviders.of(this,factory).get(NoteListViewModel.class);
        mViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                //update RecyclerView
                adapter.setNotes(notes);
            }
        });
    }

    private void setUpRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

    }
}
