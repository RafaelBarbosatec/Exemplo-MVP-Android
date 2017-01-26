package com.rafael.mpvexemplo.Books;

import android.support.annotation.NonNull;

import com.rafael.mpvexemplo.model.Book;

import java.util.List;

/**
 * Created by rafael on 26/01/17.
 */

public interface BooksContract {

    interface View{

        void setProgressIndicator(boolean active);

        void showBooks(List<Book> books);

        void moreBooks(List<Book> books);

        void showBookDetailUi(String noteId,int position);

    }

    interface UserActionsListener {

        void loadNotes(boolean forceUpdate,boolean fromRefresh);


        void openBookDetails(@NonNull Book requestedNote,int position);
    }
}
