package com.example.todoapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapplication.Models.Notes;
import com.example.todoapplication.NotesClickListener;
import com.example.todoapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> listNotes;

    NotesClickListener listener;

    public NotesListAdapter(Context context, NotesClickListener listener, List<Notes> listNotes) {
        this.context = context;
        this.listener = listener;
        this.listNotes = listNotes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.textViewTitle.setText(listNotes.get(position).getTitle());
        holder.textViewTitle.setSelected(true);

        holder.textViewAndeScoreNotes.setText(listNotes.get(position).getNotes());

        holder.textViewDate.setText(listNotes.get(position).getData());
        holder.textViewDate.setSelected(true);

        if(listNotes.get(position).isPinned()){
            holder.imageViewAnderScorePin.setImageResource(R.drawable.pin);
        }else{
            holder.imageViewAnderScorePin.setImageResource(0);
        }

        int randomColorCode = getRandomColor();
        holder.notes_container.setBackgroundColor(holder.itemView.getResources().getColor(randomColorCode, null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(listNotes.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(listNotes.get(holder.getAdapterPosition()), holder.notes_container);
                return false;
            }
        });
    }

    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<Integer>(){{
            add(R.color.red);
            add(R.color.orange);
            add(R.color.yellow);
            add(R.color.green);
            add(R.color.blue);
            add(R.color.purple);
            add(R.color.pink);
            add(R.color.indigo);
            add(R.color.brown);
        }};

        Random random = new Random();
        int randomColor = random.nextInt(colorCode.size());

        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public void filterList(List<Notes> filteredList){
        listNotes = filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textViewTitle;
    TextView textViewAndeScoreNotes;
    TextView textViewDate;
    ImageView imageViewAnderScorePin;


    public NotesViewHolder(@NonNull View itemView) {

        super(itemView);

        init();


    }

    void init(){
        notes_container = (CardView) itemView.findViewById(R.id.notes_container);
        textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewAndeScoreNotes = (TextView) itemView.findViewById(R.id.textViewAndeScoreNotes);
        textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
        imageViewAnderScorePin = (ImageView) itemView.findViewById(R.id.imageViewAnderScorePin);
    }
}
