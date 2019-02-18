package com.example.notetakingappmvvm.ui.save_note;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.notetakingappmvvm.R;
import com.example.notetakingappmvvm.utils.InjectorUtils;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {


     EditText editTextTitle;
     EditText editTextDescription;
     NumberPicker numberPickerPriority;
    SaveNoteViewModel mViewModel;

    private SaveNoteViewModel.MyCustomCallback callback;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_add_note, container, false);
        editTextTitle = (EditText)rootView.findViewById(R.id.edit_text_title);
        editTextDescription =(EditText) rootView.findViewById(R.id.edit_text_description);
        numberPickerPriority =(NumberPicker) rootView.findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        SaveNoteModelFactory factory = InjectorUtils.provideSaveNoteViewModelFactory(getContext());


        mViewModel = ViewModelProviders.of(getActivity(), factory).get(SaveNoteViewModel.class);
        callback = new SaveNoteViewModel.MyCustomCallback(){
            @Override
            public void actionIsSuccessful() {
                Toast.makeText(getContext(),"Success Callback",Toast.LENGTH_LONG).show();
                Navigation.findNavController(rootView).navigateUp();
            }

            @Override
            public void actionFailed() {
                Toast.makeText(getContext(),"failed Callback",Toast.LENGTH_LONG).show();
                Navigation.findNavController(rootView).navigateUp();
            }
        };



        mViewModel.setCallback(callback);
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_note_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                int priority = numberPickerPriority.getValue();

                mViewModel.saveNote(callback,title, description, priority);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
