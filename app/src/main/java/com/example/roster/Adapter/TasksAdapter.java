package com.example.roster.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.roster.AddNewTask;
import com.example.roster.MainActivity;
import com.example.roster.Model.TasksModel;
import com.example.roster.R;
import com.example.roster.Utils.DatabaseHandler;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    private List<TasksModel> taskList;
    private MainActivity activity;
    private DatabaseHandler db;

    public TasksAdapter(DatabaseHandler db, MainActivity activity) {
        this.activity = activity;
        this.db = db;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        db.openDatabase();
        TasksModel item = taskList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    db.updateStatus(item.getId(), 1);
                }
                else{
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    public int getItemCount() {
        return taskList.size();
    }

    private boolean toBoolean(int n) {
        return n!=0;
    }

    public void setTasks(List<TasksModel> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return activity;
    }


    public void editItem(int position) {
        TasksModel item = taskList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.task_Complete);
        }

    }
}
