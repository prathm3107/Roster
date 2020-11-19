package com.example.roster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.roster.Adapter.TasksAdapter;
import com.example.roster.Model.TasksModel;
import com.example.roster.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{
    private RecyclerView tasksRecyclerView;
    private TasksAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<TasksModel> tasklist;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        tasklist = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TasksAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        fab = findViewById(R.id.addTasks);

        tasklist = db.getAllTasks();
        Collections.reverse(tasklist);
        tasksAdapter.setTasks(tasklist);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }
    @Override
    public void handleDialogClose(DialogInterface dialog) {
        tasklist = db.getAllTasks();
        Collections.reverse(tasklist);
        tasksAdapter.notifyDataSetChanged();
    }
}