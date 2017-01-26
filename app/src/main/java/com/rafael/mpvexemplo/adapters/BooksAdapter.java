package com.rafael.mpvexemplo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rafael.mpvexemplo.model.Book;
import com.rafael.mpvexemplo.Books.BooksContract;
import com.rafael.mpvexemplo.R;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by rafael on 14/10/16.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<Book> mlista;
    private Context c;
    private BooksContract.UserActionsListener mUserActionsListener;

    public BooksAdapter(Context c, List<Book> b, BooksContract.UserActionsListener mUserActionsListener) {
       
        mlista = b;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mUserActionsListener = mUserActionsListener;
        this.c = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_book, parent, false);


        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }


    public void addListaItem(Book c, int position) {
        mlista.add(c);
        notifyItemInserted(position);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        Glide.with(c).
                load(mlista.get(position).getImg()).
                centerCrop().
                crossFade().
                into(holder.img_book);

        holder.tv_titulo.setText(mlista.get(position).getNome());

        holder.tv_data.setText(mlista.get(position).getData());

        holder.tv_desc.setText(mlista.get(position).getDescricao());

        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserActionsListener.openBookDetails(getItem(position),position);
            }
        });

    }

    public void replaceData(List<Book> books) {
        setList(books);
        notifyDataSetChanged();
    }

    private void setList(List<Book> books) {
        mlista = checkNotNull(books);
    }

    public Book getItem(int position){
        return mlista.get(position);
    }
    @Override
    public int getItemCount() {
        return mlista.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout rl_item;
        public ImageView img_book;
        public TextView tv_titulo,tv_data,tv_desc;



        public MyViewHolder(View itemView) {
            super(itemView);

            rl_item = (RelativeLayout)itemView.findViewById(R.id.rl_item);

            img_book = (ImageView) itemView.findViewById(R.id.img_book);

            tv_titulo =(TextView)itemView.findViewById(R.id.tv_titulo);

            tv_data =(TextView)itemView.findViewById(R.id.tv_data);

            tv_desc =(TextView)itemView.findViewById(R.id.tv_desc);
        }


    }
}
