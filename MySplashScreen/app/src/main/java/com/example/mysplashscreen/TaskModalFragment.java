package com.example.mysplashscreen;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class TaskModalFragment extends BottomSheetDialogFragment {

    TaskModalFragment(){}
    Spinner spinner;
    ImageButton button;
    TextInputEditText textInputEditText;

    // enum for each exercise type
    public enum ExerciseTypes{
        PU("Push Up"),
        WL("Weightlifting");

        private String type;

        ExerciseTypes(String type){
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_task_modal, container, false);

        // set spinner to display enum values
        spinner = v.findViewById(R.id.exerciseSpinner);
        ArrayAdapter<ExerciseTypes> adapter = new ArrayAdapter<ExerciseTypes>(this.getActivity(), android.R.layout.simple_spinner_item, ExerciseTypes.values());
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        // retrieve modal components
        button = v.findViewById(R.id.closeTaskModal);
        textInputEditText = v.findViewById(R.id.newTaskName);
            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset values in modal
                textInputEditText.setText("");
                spinner.setSelection(0);

                // close modal
                dismiss();
            }
        });
        return v;
    }
}