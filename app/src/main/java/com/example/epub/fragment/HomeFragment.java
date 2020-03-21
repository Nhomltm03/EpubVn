package com.example.epub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epub.R;
import com.example.epub.adapter.BookAdapter;
import com.example.epub.detail.DetailBookActivity;
import com.example.epub.model.Book;
import com.example.epub.model.DetailInformation;
import com.example.epub.network.ApiClient;
import com.example.epub.network.ApiInterface;
import com.example.epub.ultis.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String PERIOD_QUERY = "Daily";
    private static final int DATA_TYPE_MOST_DOWNLOAD = 1;
    private static final int DATA_TYPE_RECENTLY_ADDED = 2;
    private static final int DATA_TYPE_VIRAL_BOOK = 3;
    private static final int DATA_TYPE_RANDOM_BOOK = 4;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @BindView(R.id.ed_find_bar)
    EditText edFindBar;

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    @BindView(R.id.rv_most_download)
    RecyclerView rvMostDownload;

    @BindView(R.id.rv_recently_added)
    RecyclerView rvRecentlyAdded;

    @BindView(R.id.rv_viral_book)
    RecyclerView rvViralBook;

    @BindView(R.id.rv_random_book)
    RecyclerView rvRandomBook;

    private List<DetailInformation> mostDownloadList;
    private List<DetailInformation> recentlyAddedList;
    private List<DetailInformation> viralBookList;
    private List<DetailInformation> randomBookList;
    private BookAdapter recentlyAddedAdapter;
    private BookAdapter mostDownloadAdapter;
    private BookAdapter viralBookAdapter;
    private BookAdapter randomBookAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        this.init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        this.initRecycleViews(rootView);
        this.initData();
    }

    private void initData() {
        this.loadDataAPI(DATA_TYPE_MOST_DOWNLOAD);
        this.loadDataAPI(DATA_TYPE_RECENTLY_ADDED);
        this.loadDataAPI(DATA_TYPE_VIRAL_BOOK);
        this.loadDataAPI(DATA_TYPE_RANDOM_BOOK);
    }

    private void initRecycleViews(ViewGroup rootView) {
        this.mostDownloadList = new ArrayList<>();
        this.recentlyAddedList = new ArrayList<>();
        this.viralBookList = new ArrayList<>();
        this.randomBookList = new ArrayList<>();
        this.configRecycleView(this.rvMostDownload, rootView);
        this.configRecycleView(this.rvRecentlyAdded, rootView);
        this.configRecycleView(this.rvViralBook, rootView);
        this.configRecycleView(this.rvRandomBook, rootView);
    }

    private void configRecycleView(RecyclerView recyclerView, ViewGroup viewGroup) {

        recyclerView.setLayoutManager(new LinearLayoutManager(viewGroup.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void viralBookOnClickItem() {
        this.viralBookAdapter.setOnClickItemListener(position -> {
            DetailInformation model = viralBookList.get(position);
            Intent intent = new Intent(this.getContext(), DetailBookActivity.class);
            intent.setAction(Constants.ACTION_GET_DETAIL_BOOK);
            intent.putExtra(Constants.EXTRA_DATA_BOOK_SLUG, model.getSlug());
            Objects.requireNonNull(getContext()).startActivity(intent);
        });
    }

    private void randomBookOnClickItem() {
        this.randomBookAdapter.setOnClickItemListener(position -> {
            DetailInformation model = randomBookList.get(position);
            Intent intent = new Intent(this.getContext(), DetailBookActivity.class);
            intent.setAction(Constants.ACTION_GET_DETAIL_BOOK);
            intent.putExtra(Constants.EXTRA_DATA_BOOK_SLUG, model.getSlug());
            Objects.requireNonNull(getContext()).startActivity(intent);
        });
    }

    private void recentlyBookOnClickItem() {
        this.recentlyAddedAdapter.setOnClickItemListener(position -> {
            DetailInformation model = this.recentlyAddedList.get(position);
            Intent intent = new Intent(getContext(), DetailBookActivity.class);
            intent.setAction(Constants.ACTION_GET_DETAIL_BOOK);
            intent.putExtra(Constants.EXTRA_DATA_BOOK_SLUG, model.getSlug());
            if (this.getActivity() != null) {
                this.startActivity(intent);
            }
        });
    }

    private void mostDownloadBookOnClickItem() {
        this.mostDownloadAdapter.setOnClickItemListener(position -> {
            DetailInformation model = this.mostDownloadList.get(position);
            Intent intent = new Intent(this.getContext(), DetailBookActivity.class);
            intent.setAction(Constants.ACTION_GET_DETAIL_BOOK);
            intent.putExtra(Constants.EXTRA_DATA_BOOK_SLUG, model.getSlug());
            if (this.getContext() != null) {
                this.startActivity(intent);
            }
        });
    }


    private void loadDataAPI(int dataType) {

        ApiInterface apiInterface = ApiClient.getAPIClient().create(ApiInterface.class);
        Call<Book> apiCall;
        switch (dataType) {
            case DATA_TYPE_MOST_DOWNLOAD:
                apiCall = apiInterface.getMostDownload(30);
                apiCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                        if (response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            mostDownloadList = response.body().getData();
                            mostDownloadAdapter = new BookAdapter(getActivity(), mostDownloadList);
                            rvMostDownload.setAdapter(mostDownloadAdapter);
                            mostDownloadAdapter.notifyDataSetChanged();
                            mostDownloadBookOnClickItem();
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
                            recentlyAddedList = response.body().getData();
                            recentlyAddedAdapter = new BookAdapter(getActivity(), recentlyAddedList);
                            rvRecentlyAdded.setAdapter(recentlyAddedAdapter);
                            recentlyAddedAdapter.notifyDataSetChanged();
                            recentlyBookOnClickItem();
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
                break;
            case DATA_TYPE_VIRAL_BOOK:
                apiCall = apiInterface.getViral(30, PERIOD_QUERY);
                apiCall.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {

                        if (response.body() != null && response.isSuccessful() && response.body().getData() != null) {
                            viralBookList = response.body().getData();
                            viralBookAdapter = new BookAdapter(getActivity(), viralBookList);
                            rvViralBook.setAdapter(viralBookAdapter);
                            viralBookAdapter.notifyDataSetChanged();
                            viralBookOnClickItem();
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
                            randomBookAdapter = new BookAdapter(getActivity(), randomBookList);
                            rvRandomBook.setAdapter(randomBookAdapter);
                            randomBookAdapter.notifyDataSetChanged();
                            randomBookOnClickItem();
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


    @OnClick({R.id.iv_back, R.id.ed_find_bar, R.id.iv_user_profile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.ed_find_bar:
                break;
            case R.id.iv_user_profile:
                break;
        }
    }
}