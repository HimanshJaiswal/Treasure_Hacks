package com.VIT.vlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.MyViewHolder>
{
    Context context;
    ArrayList<Books> list;
    private myAdapter2.ItemClickListener clickListener;

    public myAdapter2(Context context, ArrayList<Books> list, myAdapter2.ItemClickListener clickListener)
    {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }



    @NonNull
    @Override
    public myAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list2,parent,false);
        return new myAdapter2.MyViewHolder(v,this.clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter2.MyViewHolder holder, int position) {
        Books books = list.get(position);
        holder.title.setText(books.getTitle());
//        Log.d("TAG", "Books settext: Bind" + books.getAuthor());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(myAdapter2.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView title;
        private myAdapter2.ItemClickListener clickListener;
        public MyViewHolder(View itemView, myAdapter2.ItemClickListener clickListener) {
            super(itemView);

//            Log.d("TAG", "Books settext: MyViewHolder");
            title = itemView.findViewById(R.id.title2);
            itemView.setOnClickListener(this);
            this.clickListener = clickListener;
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}
