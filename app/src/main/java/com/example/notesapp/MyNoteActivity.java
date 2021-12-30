package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class MyNoteActivity extends AppCompatActivity {
    EditText mtitle,mdescription;
    MaterialButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);

        mtitle = findViewById(R.id.title_edit);
        mdescription = findViewById(R.id.description_edit);
        mButton = findViewById(R.id.saveNote_edit);

        Intent intent = getIntent();
        String title = intent.getStringExtra("nTitle");
        String description = intent.getStringExtra("mdescription");
        mtitle.setText(title);
        mdescription.setText(description);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTitle = mtitle.getText().toString();
                String editDescription = mdescription.getText().toString();
                long createdTime = System.currentTimeMillis();

               // realm.beginTransaction();

            }
        });

    }
}