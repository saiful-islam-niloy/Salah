package com.niloy.salah;

import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class AdapterRakat extends RecyclerView.Adapter<AdapterRakat.ViewHolder> {
    Context context;
    ArrayList<ListRakat> rakatList;

    public AdapterRakat(Context context, ArrayList<ListRakat> rakatList){
        this.context = context;
        this.rakatList = rakatList;
    }
    @NonNull
    @Override
    public AdapterRakat.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_rakat, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRakat.ViewHolder viewHolder, final int i) {
        viewHolder.rakat.setText(rakatList.get(i).getRakat()+" রাকাত");
        viewHolder.priority.setText(rakatList.get(i).getPriority());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NiyatActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Rakat_ID",rakatList.get(i).getId());
                context.startActivity(intent);
            }
        });
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
