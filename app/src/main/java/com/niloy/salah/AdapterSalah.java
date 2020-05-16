package com.niloy.salah;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSalah extends
        RecyclerView.Adapter<AdapterSalah.ViewHolder> {
    Context context;
    ArrayList<ListSalah> salahNames;

    public AdapterSalah(Context context, ArrayList<ListSalah> salahNames){
        this.context = context;
        this.salahNames = salahNames;
    }

    @NonNull
    @Override
    public AdapterSalah.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_salah, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSalah.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(salahNames.get(i).getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RakatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Salah_ID",salahNames.get(i).getId());
                System.out.println("On recycle item click: "+salahNames.get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return salahNames.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.salah_name);
        }

    }
}
