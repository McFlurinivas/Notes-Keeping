package com.example.mcfluri;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

public class EditNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        getViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        setupViewModel();

        bindEditTextToLiveData(title, viewModel.title);
        bindEditTextToLiveData(content, viewModel.content);

        setupSaveButton();
    }

    private void setupViewModel() {
        final ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(EditNoteViewModel.class);

        final Bundle extras = getIntent().getExtras();
        final String id = (extras != null) ? extras.getString("id") : null;

        viewModel.initialize(this, id);
    }

    private void getViews() {
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.editTitle);
        content = findViewById(R.id.editContent);
        saveButton = findViewById(R.id.saveButton);
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(
            (v)-> {
                Log.i("", "title = " + title.getText() + ", " + "content = " + content.getText());

                viewModel.onClickedSaveButton(this);

                Toast.makeText(this, "Note saved", Toast.LENGTH_LONG).show();
            }
        );
    }

    private void bindEditTextToLiveData(
        @NonNull EditText editText, @NonNull MutableLiveData<String> liveData
    ) {
        editText.addTextChangedListener(
            new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    liveData.setValue(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) { }
            }
        );

        liveData.observe(
            this,
            (String s) -> {
                Log.i("","CustomLog: " + s);

                if(!s.equals(editText.getText().toString()))
                    editText.setText(s);
            }
        );
    }


    private Toolbar toolbar;
    private EditText title;
    private EditText content;
    private Button saveButton;

    private EditNoteViewModel viewModel = null;
}
