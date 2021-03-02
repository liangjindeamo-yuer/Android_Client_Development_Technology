package com.example.homework1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener{
    private List<String> mList;
    final  private Context mContext;

    public HomeAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        //根据RecyclerView获得当前View的位置
        int position = v.getTop()/v.getMeasuredHeight();
        //程序执行到此，会去执行具体实现的onItemClick()方法
        if (onItemClickListener!=null){
            onItemClickListener.onItemClick(v,position,mList.get(position));
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position, String data);
    }
    private OnItemClickListener onItemClickListener;//声明一下这个接口
    //提供setter方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        itemView.setOnClickListener((View.OnClickListener) this);//设置监听器
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));
    }
    public void setFilter(List<String>filterWords){
        this.mList=filterWords;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        final private TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvItem);
        }
    }
}

