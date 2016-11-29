package com.andevindo.andevindobase.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heendher on 8/7/2016.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private List<T> mList;
    private Context mContext;
    private BaseAdapterListener<T> mPresenter = new BaseAdapterListener<T>() {
        @Override
        public void onClick(T t) {
            Log.d("Adapter", "OnClick");
        }

        @Override
        public void onLongClick(T t) {
            Log.d("Adapter", "OnLongClick");
        }
    };

    public BaseAdapter(Context context) {
        mContext = context;
    }

    public BaseAdapter(List<T> list, Context context) {
        mList = list;
        mContext = context;
    }

    public void setBaseListener(BaseAdapterListener<T> presenter){
        mPresenter = presenter;
    }

    public Context getContext(){
        return mContext;
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(itemViewId(viewType), parent, false);
        return bindData(view);
    }

    protected abstract BaseViewHolder bindData(View view);

    protected abstract int itemViewId(int viewType);

    protected BaseAdapterListener<T> getBaseAdapter(){
        return mPresenter;
    }

    public interface BaseAdapterListener<T>{
        void onClick(T t);
        void onLongClick(T t);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.bindData(mList.get(position));
        holder.setBasePresenter(mList.get(position), mPresenter);
    }

    public void setData(List<T> list){
        mList = null;
        notifyDataSetChanged();
        mList = list;
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return mList;
    }

    public void addData(T t){
        if (mList==null){
            mList = new ArrayList<>(1);
        }
        mList.add(t);
        int index = mList.indexOf(t);
        notifyItemInserted(index);
    }

    public void removeDataById(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeData(T t){
        int index = mList.indexOf(t);
        mList.remove(t);
        notifyItemRemoved(index);
    }

    public T getDataById(int position){
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        else
            return mList.size();
    }
}
