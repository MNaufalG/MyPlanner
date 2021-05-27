package com.example.myplanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myplanner.ui.allNote.AllNoteFragment;
import com.example.myplanner.ui.allNote.AllNoteViewModel;
import com.example.myplanner.ui.allNote.ListItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_NOTE_REQUEST = 1;
    private AllNoteViewModel allNoteViewModel;
    private AppBarConfiguration mAppBarConfiguration;
    ImageView imageView;
    final int kodeGallery = 100;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvDescription = findViewById(R.id.tvDescription);
        TextView tvPriority = findViewById(R.id.tvPriority);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
//                startActivityForResult(intent, ADD_NOTE_REQUEST);
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_allNote, R.id.nav_recycleBin, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

//        Navigation.findNavController()
        View headerview = navigationView.getHeaderView(0);

        imageView = headerview.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGallery, kodeGallery);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == kodeGallery && resultCode == RESULT_OK){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }

//        Log.d("TEST", "test");
//
//        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
//            String title = data.getStringExtra(CreateNoteActivity.EXTRA_TITLE);
//            String description = data.getStringExtra(CreateNoteActivity.EXTRA_DESC);
//            int priority = data.getIntExtra(CreateNoteActivity.EXTRA_PRIORITY, 1);
//
//            Log.d("TEST", title);
//            Log.d("TEST", description);
//            Log.d("TEST", String.valueOf(priority));
//
//            ListItem listItem = new ListItem(title,description,priority);
//            allNoteViewModel.insert(listItem);
//
//            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this,"Note Not Saved",Toast.LENGTH_SHORT).show();
//        }
    }
}