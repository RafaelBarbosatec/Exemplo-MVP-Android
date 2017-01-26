package com.rafael.mpvexemplo.DetalheBook;

import android.support.annotation.Nullable;

import com.rafael.mpvexemplo.model.Book;

/**
 * Created by rafael on 26/01/17.
 */

public class DetalheBookPresenter implements DetalheBookContract.UserActionsListener {

    private DetalheBookContract.View bookDetalheView;

    public DetalheBookPresenter( DetalheBookContract.View bookDetalheView){
        this.bookDetalheView = bookDetalheView;
    }

    @Override
    public void openNote(@Nullable Book book) {

        if (book.getData() != null && !book.getData().equals("")) {
            bookDetalheView.showData(book.getData());
        }else{
            bookDetalheView.hideData();
        }

        if (book.getDescricao() != null && !book.getDescricao().equals("")) {
            bookDetalheView.showDescription(book.getDescricao());
        }else {

        }

        if (book.getNome() != null && !book.getNome().equals("")) {
            bookDetalheView.showTitle(book.getNome());
        }else {

        }

        if (book.getImg() != null && !book.getImg().equals("")) {
            bookDetalheView.showImage(book.getImg());
        }else {

        }

    }
}
