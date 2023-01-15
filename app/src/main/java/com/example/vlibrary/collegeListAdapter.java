package com.VIT.vlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class collegeListAdapter extends RecyclerView.Adapter<collegeListAdapter.viewHolder>
{

    Context context;
    static ArrayList<College> list;
    private collegeListAdapter.ItemClickListener clickListener;

    public collegeListAdapter(Context context, ArrayList<College> list, ItemClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public collegeListAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_list,parent,false);
        return new viewHolder(v,this.clickListener);
    }

    @Override
    public void onBindViewHolder(collegeListAdapter.viewHolder holder,int position)
    {
        College college = list.get(position);
        holder.collegeName.setText(college.getCollegeName());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setClickListener(collegeListAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView collegeName;
        private collegeListAdapter.ItemClickListener clickListener;

        public viewHolder(@NonNull View itemView, ItemClickListener clickListener) {
            super(itemView);

            collegeName = itemView.findViewById(R.id.collegeName);
            itemView.setOnClickListener(this);
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }


}























//package com.VIT.vlibrary;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class collegeListAdapter extends RecyclerView.Adapter<collegeListAdapter.MyViewHolder>
//{
//    Context context;
//    ArrayList<College> list;
//    private collegeListAdapter.ItemClickListener clickListener;
//
//    public collegeListAdapter(Context context, ArrayList<College> collegeList, collegeListAdapter.ItemClickListener clickListener)
//    {
//        this.context=context;
//        this.list = collegeList;
//        this.clickListener = clickListener;
//    }
//
//
//    @NonNull
//    @Override
//    public collegeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_list,parent,false);
//        return new collegeListAdapter.MyViewHolder(v,this.clickListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull collegeListAdapter.MyViewHolder holder, int position) {
//        College college = list.get(position);
//        holder.title.setText(college.getTitle());
////        Log.d("TAG", "Books settext: Bind" + books.getAuthor());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public void setClickListener(collegeListAdapter.ItemClickListener itemClickListener) {
//        this.clickListener = itemClickListener;
//    }
////    public interface ItemClickListener {
////        void onClick(View view, int position);
////    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
//    {
//        private final TextView title;
//        private collegeListAdapter.ItemClickListener clickListener;
//        public MyViewHolder(View itemView, collegeListAdapter.ItemClickListener clickListener) {
//            super(itemView);
//
////            Log.d("TAG", "Books settext: MyViewHolder");
//            title = itemView.findViewById(R.id.title2);
//            itemView.setOnClickListener(this);
//            this.clickListener = clickListener;
//        }
//        @Override
//        public void onClick(View view) {
//            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
//        }
//    }
//}
