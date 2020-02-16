package com.aysel.rssreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String RSS_URL = "https://www.cnnturk.com/feed/rss/dunya/news";
    private PullParseXML pullAndParseXML;
    private List<HaberModel> haberList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullAndParseXML = new PullParseXML(RSS_URL);
        pullAndParseXML.downloadXML();

        while (pullAndParseXML.parsingComplete) ;
        haberList = pullAndParseXML.getPostList().subList(1, pullAndParseXML.getPostList().size());

        recyclerView = findViewById(R.id.recycler_view);

        HaberAdapter adp = new HaberAdapter(this, haberList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adp);
        adp.setOnItemClickListener(onItemNewsClickListener);
    }

    View.OnClickListener onItemNewsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int i = viewHolder.getAdapterPosition();
            HaberModel item = haberList.get(i);

            Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
            intent.putExtra("link", item.getLink());
            startActivity(intent);
        }
    };
}