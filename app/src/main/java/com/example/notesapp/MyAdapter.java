package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    RealmResults<Notes> noteslist;

    public MyAdapter(Context context, RealmResults<Notes> noteslist) {
        this.context = context;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Notes notes = noteslist.get(position);
        holder.titleoutput.setText(notes.getTitle());
        holder.descriptionOutput.setText(notes.getDescription());

        String fomatedTime = DateFormat.getDateTimeInstance().format(notes.createdTime);
        holder.timeOutput.setText(fomatedTime);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MyNoteActivity.class);
                intent.putExtra("nTitle", notes.getTitle());
                intent.putExtra("mdescription",notes.getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("DELETE");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("DELETE"))
                        {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            notes.deleteFromRealm();
                            realm.commitTransaction();
                            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                menu.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleoutput, descriptionOutput,timeOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleoutput = itemView.findViewById(R.id.titleOutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionOutput);
            timeOutput = itemView.findViewById(R.id.timeOutput);
        }
    }
}
