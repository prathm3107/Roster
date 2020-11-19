package com.example.roster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.roster.Adapter.TasksAdapter;
import com.example.roster.Model.TasksModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;
    private TasksAdapter tasksAdapter;

    private List<TasksModel> tasklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tasklist = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TasksAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        TasksModel task = new TasksModel();
        task.setTask("This is a test Task");
        task.setStatus(0);
        task.setId(1);

        tasklist.add(task);
        tasklist.add(task);
        tasklist.add(task);
        tasklist.add(task);
        tasklist.add(task);

        tasksAdapter.setTasks(tasklist);
    }
}