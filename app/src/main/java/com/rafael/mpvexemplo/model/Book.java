package com.rafael.mpvexemplo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rafael on 26/01/17.
 */

public class Book implements Parcelable {
    private String cod;
    private String nome;
    private String img;
    private String data;
    private String descricao;

    public Book(){

    }

    public Book(String cod, String nome, String img, String data, String descricao) {
        this.cod = cod;
        this.nome = nome;
        this.img = img;
        this.data = data;
        this.descricao = descricao;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cod);
        dest.writeString(this.nome);
        dest.writeString(this.img);
        dest.writeString(this.data);
        dest.writeString(this.descricao);
    }

    protected Book(Parcel in) {
        this.cod = in.readString();
        this.nome = in.readString();
        this.img = in.readString();
        this.data = in.readString();
        this.descricao = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
