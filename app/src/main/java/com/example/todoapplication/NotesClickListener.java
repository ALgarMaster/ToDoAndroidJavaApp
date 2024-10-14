package com.example.todoapplication;

import androidx.cardview.widget.CardView;

import com.example.todoapplication.Models.Notes;

public interface NotesClickListener {

    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
