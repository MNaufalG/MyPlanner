package com.example.myplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreateNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.myplanner.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.example.myplanner.EXTRA_DESC";
    public static final String EXTRA_PRIORITY = "com.example.myplanner.EXTRA_PRIORITY";

    private EditText editTextTitle, editTextDesc;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDesc = findViewById(R.id.edit_text_desc);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDesc.getText().toString();
        int priority = numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this,"Please Insert a Title and Description", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this,"title :" +title+ "desc :" +description+ "priority :" +priority, Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent(this,MainActivity.class);
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESC,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.create_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}