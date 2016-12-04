package com.andevindo.andevindobase.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by heendher on 12/5/2016.
 */

public abstract class BaseRealmAdapter<T extends RealmObject> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private RealmResults<T> mList;
    private Context mContext;
    private BaseAdapter.BaseAdapterListener<T> mPresenter = new BaseAdapter.BaseAdapterListener<T>() {
        @Override
        public void onClick(T t) {
            Log.d("Adapter", "OnClick");
        }

        @Override
        public void onLongClick(T t) {
            Log.d("Adapter", "OnLongClick");
        }
    };

    public BaseRealmAdapter(Context context) {
        mContext = context;
    }

    public BaseRealmAdapter(RealmResults<T> list, Context context) {
        mList = list;
        mContext = context;
    }

    public void setBaseListener(BaseAdapter.BaseAdapterListener<T> presenter){
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

    protected BaseAdapter.BaseAdapterListener<T> getBaseAdapter(){
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

    public void setData(RealmResults<T> list){
        mList = null;
        notifyDataSetChanged();
        mList = list;
        notifyDataSetChanged();
    }

    public RealmResults<T> getData(){
        return mList;
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
