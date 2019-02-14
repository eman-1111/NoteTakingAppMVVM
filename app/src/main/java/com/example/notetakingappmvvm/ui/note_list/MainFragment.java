package com.example.notetakingappmvvm.ui.note_list;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.example.notetakingappmvvm.R;
import com.example.notetakingappmvvm.data.Note;
import com.example.notetakingappmvvm.utils.InjectorUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainFragment extends Fragment {

    NoteListViewModel mViewModel;
    private LiveData<List<Note>> allNotes;
    RecyclerView recyclerView;
    NoteAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView =(RecyclerView) rootView.findViewById(R.id.recycler_view);
        FloatingActionButton buttonAddNote = rootView.findViewById(R.id.button_add_note);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        NoteListModelFactory factory = InjectorUtils.provideNoteListViewModelFactory(getContext());


        mViewModel = ViewModelProviders.of(getActivity(), factory).get(NoteListViewModel.class);

        mViewModel.getAllNotes().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNotes(notes);
            }
        });        setHasOptionsMenu(true);

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.mainToAdd);
            }
        });
        return rootView;
    }

    private void setUpRecycler() {



    }


}
