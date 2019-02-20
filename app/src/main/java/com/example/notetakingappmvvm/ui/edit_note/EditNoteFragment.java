package com.example.notetakingappmvvm.ui.edit_note;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
import com.example.notetakingappmvvm.ui.save_note.SaveNoteModelFactory;
import com.example.notetakingappmvvm.utils.InjectorUtils;

public class EditNoteFragment extends Fragment {

    private EditNoteViewModel mViewModel;
    EditText editTextTitle;
    EditText editTextDescription;
    NumberPicker numberPickerPriority;

    private EditNoteViewModel.MyCustomCallback callback;
    int id = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.edit_note_fragment, container, false);
        editTextTitle = (EditText) rootView.findViewById(R.id.edit_text_title);
        editTextDescription = (EditText) rootView.findViewById(R.id.edit_text_description);
        numberPickerPriority = (NumberPicker) rootView.findViewById(R.id.number_picker_priority);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("idArg");
            editTextTitle.setText(bundle.getString("TitleArg"));
            editTextDescription.setText(bundle.getString("DescriptionArg"));
            numberPickerPriority.setValue(bundle.getInt("priorityArg"));
        }
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        EditNoteModelFactory factory = InjectorUtils.provideEditNoteViewModelFactory(getContext());
        mViewModel = ViewModelProviders.of(this, factory).get(EditNoteViewModel.class);

        callback = new EditNoteViewModel.MyCustomCallback() {
            @Override
            public void actionIsSuccessful() {
                Toast.makeText(getContext(), "Success Callback", Toast.LENGTH_LONG).show();
                Navigation.findNavController(rootView).navigateUp();
            }

            @Override
            public void actionFailed() {
                Toast.makeText(getContext(), "failed Callback", Toast.LENGTH_LONG).show();
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

                mViewModel.editNote(callback, id,title, description, priority);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
