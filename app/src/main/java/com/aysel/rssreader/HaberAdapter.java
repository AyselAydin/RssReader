package com.aysel.rssreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HaberAdapter extends RecyclerView.Adapter<HaberAdapter.MyViewHolder> {

    private Context context;
    private List<HaberModel> postList;
    private View.OnClickListener mOnItemClickListener;

    public HaberAdapter(Context context, List<HaberModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            imageView = view.findViewById(R.id.imageView);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_haber_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        HaberModel post = postList.get(position);
        holder.title.setText(post.getTitle());
        Picasso.with(context).load(post.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}