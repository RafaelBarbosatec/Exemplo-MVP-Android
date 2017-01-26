package com.rafael.mpvexemplo.DetalheBook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rafael.mpvexemplo.Books.BooksContract;
import com.rafael.mpvexemplo.R;
import com.rafael.mpvexemplo.model.Book;

public class DetalheBookFragment extends Fragment implements DetalheBookContract.View{

    private static final String BOOK_ITEM = "book";
    private DetalheBookContract.UserActionsListener mUserActionsListener;
    private View view;
    private ImageView img_book;
    private TextView tv_titulo,tv_data,tv_desc;


    public DetalheBookFragment() {
        // Required empty public constructor
    }

    public static DetalheBookFragment newInstance(Book book) {
        DetalheBookFragment fragment = new DetalheBookFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOK_ITEM, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserActionsListener = new DetalheBookPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalhe_book, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        img_book = (ImageView) view.findViewById(R.id.img_book);
        tv_data = (TextView) view.findViewById(R.id.tv_data);
        tv_titulo = (TextView) view.findViewById(R.id.tv_titulo);
        tv_desc = (TextView) view.findViewById(R.id.tv_desc);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() != null) {
            Book book = getArguments().getParcelable(BOOK_ITEM);
            Log.i("BOOK","book: "+book.getCod());
            mUserActionsListener.openNote(book);
        }
    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showMissingNote() {

    }

    @Override
    public void hideTitle() {
        tv_titulo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showTitle(String title) {
        tv_titulo.setText(title);
        tv_titulo.setVisibility(View.VISIBLE);
    }

    @Override
    public void showImage(String imageUrl) {
        Glide.with(getActivity()).
                load(imageUrl).
                fitCenter().
                crossFade().
                into(img_book);
    }

    @Override
    public void hideImage() {
        img_book.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showData(String data) {
        tv_data.setText(data);
        tv_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideData() {
        tv_data.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideDescription() {
        tv_desc.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showDescription(String description) {
        tv_desc.setText(description);
        tv_desc.setVisibility(View.VISIBLE);
    }
}
