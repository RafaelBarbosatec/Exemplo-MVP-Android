package com.rafael.mpvexemplo.DetalheBook;

import android.support.annotation.Nullable;

import com.rafael.mpvexemplo.model.Book;

/**
 * Created by rafael on 26/01/17.
 */

public interface DetalheBookContract {

    interface View {


        void showTitle(String title);

        void showImage(String imageUrl);

        void showData(String data);


        void showDescription(String description);
    }

    interface UserActionsListener {

        void openNote(@Nullable Book book);
    }

}
