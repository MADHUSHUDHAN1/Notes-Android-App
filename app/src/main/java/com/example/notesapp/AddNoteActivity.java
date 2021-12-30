package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {
    EditText titleInput,descriptionInput;
    MaterialButton saveNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleInput = findViewById(R.id.title_input);
        descriptionInput = findViewById(R.id.description);
        saveNoteBtn = findViewById(R.id.saveNote);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();


        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();

                realm.beginTransaction();
                Notes notes = realm.createObject(Notes.class);
                notes.setTitle(title);
                notes.setDescription(description);
                notes.setCreatedTime(createdTime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
               finish();
            }
        });
    }
}