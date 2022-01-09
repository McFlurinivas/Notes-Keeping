package com.example.mcfluri;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataList = findViewById(R.id.dataList);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        setupViewModel();

        setupDataList();
        setupFloatingActionButton();
    }

    private void setupViewModel() {
        final ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(MainActivityViewModel.class);

        viewModel.initialize(this);
    }

    private void setupDataList() {
        final Adapter adapter = new Adapter(this, viewModel.notes);
        final GridLayoutManager layoutManager = new GridLayoutManager(
            this, 2, GridLayoutManager.VERTICAL, false
        );
        dataList.setAdapter(adapter);
        dataList.setLayoutManager(layoutManager);
        dataList.addItemDecoration(new PaddingItemDecoration(16));
    }

    private void setupFloatingActionButton() {
        floatingActionButton.setOnClickListener(
            (view) -> {
                Intent intent = new Intent(this, EditNoteActivity.class);
                startActivity(intent);
            }
        );
    }

    private Toolbar toolbar;
    private RecyclerView dataList;
    private FloatingActionButton floatingActionButton;

    private MainActivityViewModel viewModel = null;
}