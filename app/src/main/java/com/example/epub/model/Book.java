package com.example.epub.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("data")
    @Expose
    private List<DetailInformation> data = null;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<DetailInformation> getData() {
        return data;
    }

    public void setData(List<DetailInformation> data) {
        this.data = data;
    }
}
