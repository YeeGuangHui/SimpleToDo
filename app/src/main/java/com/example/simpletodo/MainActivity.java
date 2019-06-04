package com.example.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTask;
    Spinner spnAddRemove;

    ArrayList<String> alTask = new ArrayList<>();
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.button);
        btnClear = findViewById(R.id.button2);
        btnDelete = findViewById(R.id.button3);
        lvTask = findViewById(R.id.listViewTask);
        spnAddRemove = findViewById(R.id.spinner);


        //spinner
        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //show hint if item 1 is selected
                        etTask.setHint(R.string.app_name);
                        //button add is enabled
                        btnAdd.setEnabled(true);
                        //button delete is disabled
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        //show hint if item 2 is selected
                        etTask.setHint(R.string.app_name);
                        //button add is disabled
                        btnAdd.setEnabled(false);
                        //button delete is enabled
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //add the task
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the task user entered in the edit text
                String task = etTask.getText().toString();

                if (task.length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                } else {
                    alTask.add(task);
                    aaTask.notifyDataSetChanged();
                    clearField();
                    Toast.makeText(MainActivity.this, task, Toast.LENGTH_LONG).show();
                }

            }
        });

        //delete all task
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTask.size() == 0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                } else {
                    String task = etTask.getText().toString();
                    int pos = 0;
                    try {
                        pos = Integer.parseInt(etTask.getText().toString());
                    } catch (Exception e) {

                    }
                    if (pos >= 0 && pos < alTask.size()) {
                        //remove
                        alTask.remove(pos);
                        aaTask.notifyDataSetChanged();
                        clearField();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong text", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });


        aaTask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);
    }

    public void clearField() {
        etTask.setText("");
    }
}
