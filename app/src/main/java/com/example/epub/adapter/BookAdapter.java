package com.example.epub.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.epub.R;
import com.example.epub.model.DetailInformation;
import com.example.epub.ultis.Utils;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<DetailInformation> detailInformationList;
    private Context context;
    private OnClickItemListener onClickItemListener;


    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public BookAdapter(Context context, List<DetailInformation>detailInformationList){
        this.context = context;
        this.detailInformationList = detailInformationList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book,parent,false);
        return new BookViewHolder(view);
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
      DetailInformation model = this.detailInformationList.get(position);
      RequestOptions mOptions = new RequestOptions();
      mOptions.placeholder(Utils.getRandomDrawableColor());
      mOptions.error(Utils.getRandomDrawableColor());
      mOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
      mOptions.centerCrop();
      Glide.with(this.context).load(model.getCover()).apply(mOptions)
                           .listener(new RequestListener<Drawable>() {
                               @Override
                               public boolean onLoadFailed(@Nullable GlideException e, Object model
                                       , Target<Drawable> target, boolean isFirstResource) {
                                   holder.progressBar.setVisibility(View.GONE);
                                   return false;
                               }

                               @Override
                               public boolean onResourceReady(Drawable resource, Object model
                                       , Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                   holder.progressBar.setVisibility(View.GONE);
                                   return false;
                               }
                           }).transition(DrawableTransitionOptions.withCrossFade()).into(holder.ivBookCover);
      holder.tvBookName.setText(model.getName());
      holder.tvAuthor.setText(model.getAuthor());
      holder.tvBookRate.setText(model.getRatingNumbers().toString());

    }

    public  interface OnClickItemListener{
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return this.detailInformationList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvBookName, tvAuthor, tvBookRate;
        ProgressBar progressBar;
        ImageView ivBookCover;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.initViews(itemView);
        }

        private void initViews(@NonNull View itemView) {
            this.tvBookName = itemView.findViewById(R.id.tv_book_name);
            this.tvBookRate = itemView.findViewById(R.id.tv_book_rate);
            this.tvAuthor = itemView.findViewById(R.id.tv_book_author);
            this.ivBookCover = itemView.findViewById(R.id.iv_book_cover);
            this.progressBar = itemView.findViewById(R.id.progress_load_image);
            this.tvBookName.setSelected(true);
            this.tvAuthor.setSelected(true);
        }

        @Override
        public void onClick(View view) {
            onClickItemListener.onItemClick(getAdapterPosition());
        }
    }
}
