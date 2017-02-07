package com.rafael.mpvexemplo.model;

import java.util.List;

/**
 * Created by rafael on 07/02/17.
 */

public class ResponseBooks {

    private boolean erro;
    private boolean existNexPage;
    private List<Book> data;

    public ResponseBooks(boolean erro, boolean existNexPage, List<Book> data) {
        this.erro = erro;
        this.existNexPage = existNexPage;
        this.data = data;
    }


    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public boolean isExistNexPage() {
        return existNexPage;
    }

    public void setExistNexPage(boolean existNexPage) {
        this.existNexPage = existNexPage;
    }

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }
}
