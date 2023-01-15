package com.VIT.vlibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context context;
    ArrayList<Books> list;
    private MyAdapter.ItemClickListener clickListener;

    public MyAdapter(Context context, ArrayList<Books> list,ItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list,parent,false);
        return new MyViewHolder(v,this.clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Books books = list.get(position);
        holder.title.setText(books.getTitle());
//        Log.d("TAG", "Books settext: Bind" + books.getAuthor());
        holder.author.setText(books.getAuthor());
        holder.year.setText(books.getYear());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setClickListener(MyAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView title,author,year;
        private ItemClickListener clickListener;
        public MyViewHolder(View itemView, ItemClickListener clickListener) {
            super(itemView);

//            Log.d("TAG", "Books settext: MyViewHolder");
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            year = itemView.findViewById(R.id.year);
            itemView.setOnClickListener(this);
            this.clickListener = clickListener;
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}


