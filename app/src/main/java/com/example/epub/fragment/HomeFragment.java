package com.example.epub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epub.R;
import com.example.epub.adapter.BookAdapter;
import com.example.epub.model.Book;
import com.example.epub.model.DetailInformation;
import com.example.epub.network.ApiClient;
import com.example.epub.network.ApiInterface;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String PERIOD_QUERY = "Daily";
    private static final int DATA_TYPE_MOST_DOWNLOAD = 1;
    private static final int DATA_TYPE_RECENTLY_ADDED = 2;
    private static final int DATA_TYPE_VIRAL_BOOK = 3;
    private static final int DATA_TYPE_RANDOM_BOOK = 4;
    private RecyclerView rcMostDownLoad;
    private RecyclerView rcRecentlyAdded;
    private RecyclerView rcRivalBook;
    private RecyclerView rcRandomBook;
    private List<DetailInformation> mostDownloadList = new ArrayList<>();
    private List<DetailInformation> recentlyAddedList = new ArrayList<>();
    private List<DetailInformation> viralBookList = new ArrayList<>();
    private List<DetailInformation> randomBookList = new ArrayList<>();
    private BookAdapter recentlyAddedAdapter;
    private BookAdapter mostDownloadAdapter;
    private BookAdapter viralBookAdapter;
    private BookAdapter randomBookAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        rcMostDownLoad = rootView.findViewById(R.id.rv_most_download);
        rcRecentlyAdded = rootView.findViewById(R.id.rv_recently_added);
        rcRivalBook = rootView.findViewById(R.id.rv_viral_book);
        rcRandomBook = rootView.findViewById(R.id.rv_random_book);
        configRecycleView(rcMostDownLoad,rootView);
        configRecycleView(rcRecentlyAdded,rootView);
        configRecycleView(rcRivalBook,rootView);
        configRecycleView(rcRandomBook,rootView);
        loadDataAPI(DATA_TYPE_MOST_DOWNLOAD);
        loadDataAPI(DATA_TYPE_RECENTLY_ADDED);
        loadDataAPI(DATA_TYPE_VIRAL_BOOK);
        loadDataAPI(DATA_TYPE_RANDOM_BOOK);
        return rootView;
    }

    private void configRecycleView(RecyclerView recyclerView,ViewGroup viewGroup){

        recyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }


    private void loadDataAPI(int dataType){

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<Book> apiCall;
        switch (dataType){
            case DATA_TYPE_MOST_DOWNLOAD:
                apiCall = apiInterface.getMostDownload(30);
                apiCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                        if (response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            mostDownloadList = response.body().getData();
                            mostDownloadAdapter = new BookAdapter(getActivity(),mostDownloadList);
                            rcMostDownLoad.setAdapter(mostDownloadAdapter);
                            mostDownloadAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
                break;
            case DATA_TYPE_RECENTLY_ADDED:
                apiCall = apiInterface.getRecentlyAdded(30);
                apiCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                        if (response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            recentlyAddedList  = response.body().getData();
                            recentlyAddedAdapter = new BookAdapter(getActivity(),recentlyAddedList);
                            rcRecentlyAdded.setAdapter(recentlyAddedAdapter);
                            recentlyAddedAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
                break;
            case DATA_TYPE_VIRAL_BOOK:
                apiCall = apiInterface.getViral(30,PERIOD_QUERY);
                apiCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                        if (response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            viralBookList = response.body().getData();
                            viralBookAdapter = new BookAdapter(getActivity(),viralBookList);
                            rcRivalBook.setAdapter(viralBookAdapter);
                            viralBookAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
                break;
            case DATA_TYPE_RANDOM_BOOK:
                apiCall = apiInterface.getRandombooks(30);
                apiCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                        if (response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            randomBookList = response.body().getData();
                            randomBookAdapter = new BookAdapter(getActivity(),randomBookList);
                            rcRandomBook.setAdapter(randomBookAdapter);
                            randomBookAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dataType);
        }

    }
}