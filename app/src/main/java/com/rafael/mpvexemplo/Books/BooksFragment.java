package com.rafael.mpvexemplo.Books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rafael.mpvexemplo.DetalheBook.DetalheBookActivity;
import com.rafael.mpvexemplo.model.Book;
import com.rafael.mpvexemplo.R;
import com.rafael.mpvexemplo.adapters.BooksAdapter;

import java.util.ArrayList;
import java.util.List;


public class BooksFragment extends Fragment implements BooksContract.View{


    private BooksContract.UserActionsListener mUserActionsListener;
    private RecyclerView mRecyclerView;
    private ProgressBar books_progress;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private BooksAdapter adapter;
    private boolean preenchido = false;
    private View view;

    public BooksFragment() {
        // Required empty public constructor
    }


    public static BooksFragment newInstance() {
        BooksFragment fragment = new BooksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserActionsListener = new BooksPresenter(getActivity(),this);
        adapter = new BooksAdapter(getActivity(),new ArrayList<Book>(),mUserActionsListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_books, container, false);

        initReciclerview(view);

        return view;
    }

    private void initReciclerview(View view) {


        books_progress = (ProgressBar) view.findViewById(R.id.books_progress);

        //Configurando SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_books);
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionsListener.loadNotes(true,true);
            }
        });

        //configurando RecyclerView
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_books);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();

                if ((adapter.getItemCount()) <= llm.findLastCompletelyVisibleItemPosition() + 1 ){

                    mUserActionsListener.loadNotes(false,false);

                }
            }

        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

        mRecyclerView.setAdapter(adapter);

    }


    @Override
    public void setProgressIndicator(boolean active) {

        if (active){

            books_progress.setVisibility(View.VISIBLE);

        }else{

            if (mSwipeRefreshLayout.isRefreshing()){
                mSwipeRefreshLayout.setRefreshing(false);
            }

            books_progress.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showBooks(List<Book> books) {
        adapter.replaceData(books);


    }

    @Override
    public void moreBooks(List<Book> books) {
        for (Book b : books){
            adapter.addListaItem(b,adapter.getItemCount());
        }
    }


    @Override
    public void showBookDetailUi(String noteId,int position) {

        Intent detalhe =  new Intent(getActivity(), DetalheBookActivity.class);
        detalhe.putExtra("book",adapter.getItem(position));

        startActivity(detalhe);

    }


    @Override
    public void onResume() {
        super.onResume();
        if (!preenchido) {
            mUserActionsListener.loadNotes(true, false);
            preenchido = true;
        }
    }

}
