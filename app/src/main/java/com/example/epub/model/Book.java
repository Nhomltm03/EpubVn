package com.example.epub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Book {

    @SerializedName("result")
    @Expose
    private Boolean result;

    @SerializedName("data")
    @Expose
    private List<DetailInformation> data = null;

    public Boolean getResult() {
        return this.result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<DetailInformation> getData() {
        return this.data;
    }

    public void setData(List<DetailInformation> data) {
        this.data = data;
    }
}
