package com.niloy.salah;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRakat extends RecyclerView.Adapter<AdapterRakat.ViewHolder> {
    Context context;
    ArrayList<ListRakat> rakatList;
    ArrayList<String> priorityList;

    public AdapterRakat(Context context, ArrayList<ListRakat> rakatList, ArrayList<String> priorityList){
        this.context = context;
        this.rakatList = rakatList;
        this.priorityList = priorityList;
    }
    @NonNull
    @Override
    public AdapterRakat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_rakat, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRakat.ViewHolder viewHolder, int i) {
        viewHolder.rakat.setText(rakatList.get(i).getRakat());
        viewHolder.priority.setText(rakatList.get(i).getNiyat_bangla());
    }

    @Override
    public int getItemCount() {
        return rakatList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView rakat, priority;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rakat = itemView.findViewById(R.id.salah_rakat);
            priority = itemView.findViewById(R.id.salah_priority);
        }

    }
}
