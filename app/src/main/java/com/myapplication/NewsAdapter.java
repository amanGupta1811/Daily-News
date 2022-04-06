package com.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public  class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

   // private ArrayList<News> aData;
    private LayoutInflater aInflater;
    private ClickInterface clickInterface;
    private ClickInterface listner;

    private News news;

    //private News title;


    NewsAdapter(ClickInterface listner) {
        this.listner = listner;



      //ArrayList<String>items = new ArrayList<>();



       // this.clickInterface = clickInterface;
        //this.aData = data;

    }

    ArrayList<News>aData = new ArrayList<>();






    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      // ArrayList<String>items = new ArrayList<>();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onItemClick(aData.get(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    //@Override
    public void onBindViewHolder(NewsViewHolder newsViewHolder, int position) {

        News currentItem;
       currentItem = aData.get(position);
        newsViewHolder.title1.setText(currentItem.getTitle());
       newsViewHolder.author1.setText( currentItem.getAuthor());

       Glide.with(newsViewHolder.itemView.getContext()).load(currentItem.getUrlToImage()).into(newsViewHolder.image1);


    }

    @Override
    public int getItemCount() {
        return aData.size();

    }

    public void upDate(ArrayList<News>upDate){
        aData.clear();
        aData.addAll(upDate);

        notifyDataSetChanged();
    }
}

    class NewsViewHolder extends RecyclerView.ViewHolder {


       TextView title1,author1;
       ImageView image1;

        public NewsViewHolder(View itemView) {
            super(itemView);

           // itemView.setOnClickListener(this);
            title1 = itemView.findViewById(R.id.title);

            image1 = itemView.findViewById(R.id.image);

            author1 = itemView.findViewById(R.id.author);

        }



    }









