package com.rafael.mpvexemplo.Books;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rafael.mpvexemplo.R;

public class BooksActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbar_books);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MVP Exemplo");

        if (null == savedInstanceState) {
            initFragment(BooksFragment.newInstance());
        }
    }

    private void initFragment(Fragment notesFragment) {
        // Add the NotesFragment to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_frag, notesFragment);
        transaction.commit();
    }
}
