package com.vanannek.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(v -> {
            try {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                boolean flag = myDB.addBook(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        Integer.valueOf(pages_input.getText().toString().trim()));
                if (flag)
                    clearText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void clearText() {
        title_input.getText().clear();
        author_input.getText().clear();
        pages_input.getText().clear();
    }
}