package br.com.julian.movieapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel<T> {
    @SerializedName("items")
    List<T> items;
    @SerializedName("name")
    String listName;

    public List<T> getItems() {
        return items;
    }

    public String getListName() {
        return listName;
    }
}
