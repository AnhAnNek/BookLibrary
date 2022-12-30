package com.vanannek.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText title_input, author_input, pages_input;
    private Button update_button;

    private String id, title, author, pages;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);

        // First we call this
        getAndSetIntentData();

        update_button.setOnClickListener(v -> {
            // And only then we call this
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            title = title_input.getText().toString().trim();
            author = author_input.getText().toString().trim();
            pages = pages_input.getText().toString().trim();
            myDB.updateData(id, title, author, pages);

            // Start Main Activity
            Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
    
    public void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            // getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            // setting intent data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}