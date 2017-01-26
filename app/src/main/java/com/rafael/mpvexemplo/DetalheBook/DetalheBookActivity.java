package com.rafael.mpvexemplo.DetalheBook;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.rafael.mpvexemplo.Books.BooksFragment;
import com.rafael.mpvexemplo.R;
import com.rafael.mpvexemplo.model.Book;

public class DetalheBookActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Book b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_book);

        Intent intent = getIntent();
        if (intent != null){
            Bundle extras = intent.getExtras();
            if (extras != null){
                b = extras.getParcelable("book");
            }
        }

        mToolbar = (Toolbar)findViewById(R.id.toolbar_detalhe_books);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("MVP Exemplo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (null == savedInstanceState) {
            initFragment(DetalheBookFragment.newInstance(b));
        }

    }

    private void initFragment(Fragment notesFragment) {
        // Add the NotesFragment to the layout
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_frag, notesFragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
