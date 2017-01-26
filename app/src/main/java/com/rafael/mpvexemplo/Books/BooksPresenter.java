package com.rafael.mpvexemplo.Books;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.rafael.mpvexemplo.util_conection.TentarNovamente;
import com.rafael.mpvexemplo.util_conection.TestarConexao;
import com.rafael.mpvexemplo.model.Book;
import com.rafael.mpvexemplo.util_conection.NetworkConnection;
import com.rafael.mpvexemplo.util_conection.ResponseConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rafael on 26/01/17.
 */

public class BooksPresenter implements ResponseConnection, BooksContract.UserActionsListener,TentarNovamente{

    private String url_base = "http://suporteapp.esy.es/teste/books.php?pagina=";
    private String url = "";
    private BooksContract.View booksView;
    private Activity activity;
    private Gson gson;
    private boolean isUpdate, carregando = false, existNextPage = true;
    private int pagina = 0;

    public BooksPresenter(Activity activity , BooksContract.View booksView){

        this.activity = activity;
        this.booksView = booksView;
        gson = new Gson();
    }

    @Override
    public void loadNotes(boolean forceUpdate,boolean fromRefresh) {
        isUpdate = forceUpdate;

        if(forceUpdate){
            pagina = 0;
            existNextPage = true;
        }else{
            pagina++;
        }

        url = url_base+pagina;
        if (existNextPage)
        if (TestarConexao.VerificaConexao(activity, this) && !carregando) {

            if (!fromRefresh)
                booksView.setProgressIndicator(true);

            carregando = true;

            NetworkConnection.getInstance(activity).conectionVolley(this, url, Request.Method.GET);
        }

    }


    @Override
    public void openBookDetails(@NonNull Book requestedBook,int position) {
        booksView.showBookDetailUi(requestedBook.getCod(),position);
    }

    @Override
    public Map<String, String> doBefore() {
        return null;
    }

    @Override
    public void doAfter(JSONObject jsonObject) {

        try {
            if (!jsonObject.getBoolean("erro")) {

                JSONArray books = jsonObject.getJSONArray("data");
                existNextPage = jsonObject.getBoolean("existNexPage");

                Book[] book_array = gson.fromJson(books.toString(), Book[].class);
                List<Book> listBooks = new ArrayList<>();

                for (Book b : book_array){
                    listBooks.add(b);
                }

                if (isUpdate) {

                    booksView.showBooks(listBooks);

                }else{

                    booksView.moreBooks(listBooks);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        carregando = false;
        booksView.setProgressIndicator(false);
    }

    @Override
    public void erroServer(VolleyError error) {
        booksView.setProgressIndicator(false);
        TestarConexao.calldialog(activity,this);
        carregando = false;
    }

    @Override
    public void tentarNovamente() {

        if (TestarConexao.VerificaConexao(activity,this)) {

            if (!isUpdate)
                booksView.setProgressIndicator(true);

            carregando = true;
            NetworkConnection.getInstance(activity).conectionVolley(this, url, Request.Method.GET);
        }
    }
}
