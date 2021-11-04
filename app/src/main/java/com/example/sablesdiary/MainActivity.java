package com.example.sablesdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sablesdiary.database.DiaryEntry;
import com.example.sablesdiary.database.DiaryEntryViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DiaryListAdapter.ItemClickListener {

    private DiaryListAdapter mAdapter;
    private DiaryEntryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View emptyView = findViewById(R.id.emptyview);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        EmptyRecyclerView recyclerView = findViewById(R.id.recyclerview_diary);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mAdapter = new DiaryListAdapter(this, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setEmptyView(emptyView);

        mViewModel = new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        mViewModel.getEntries().observe(this, new Observer<List<DiaryEntry>>() {
            @Override
            public void onChanged(List<DiaryEntry> diaryEntries) {
                //sortEntriesDescending(diaryEntries);
                mAdapter.setEntries(diaryEntries);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(int id) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("diary_id", id);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int id) {
        mViewModel.deleteById(id);
    }

}