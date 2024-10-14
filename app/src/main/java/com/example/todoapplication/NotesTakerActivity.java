package com.example.todoapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoapplication.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {


    Toolbar toolbar;
    ImageView save;
    EditText textTitle;
    EditText textNotes;

    Notes note;
    boolean isOldNote = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_taker);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_notes_taker), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        note = new Notes();

        try {
            note = (Notes) getIntent().getSerializableExtra("old_note");
            textTitle.setText(note.getTitle());
            textNotes.setText(note.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = textTitle.getText().toString();
                String description = textNotes.getText().toString();

                if(description.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please, enter description", Toast.LENGTH_SHORT).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                Date date = new Date();

                if(!isOldNote){
                    note = new Notes();
                }

                note.setTitle(title);
                note.setNotes(description);
                note.setData(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note", note);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

    private void init(){
        save = (ImageView) findViewById(R.id.image_save);

        textTitle = (EditText) findViewById(R.id.editText_title);
        textNotes = (EditText) findViewById(R.id.editText_notes);
    }
}