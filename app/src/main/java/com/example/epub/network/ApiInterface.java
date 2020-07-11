package com.example.epub.network;

import com.example.epub.model.Book;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("api-access-token:epubvn-react-app-123456")
    @GET("mostdownload")
    Call<Book> getMostDownload(@Query("limit") int limit);

    @Headers("api-access-token:epubvn-react-app-123456")
    @GET("recentlyadded")
    Call<Book> getRecentlyAdded(@Query("limit") int limit);

    @Headers("api-access-token:epubvn-react-app-123456")
    @GET("viral")
    Call<Book> getViral(@Query("limit") int limit,
                        @Query("period") String period);

    @Headers("api-access-token:epubvn-react-app-123456")
    @GET("randombooks")
    Call<Book> getRandombooks(@Query("limit") int limit);

}

