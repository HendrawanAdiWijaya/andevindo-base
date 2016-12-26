package com.andevindo.andevindobase.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
    private int mPage = 1;
    private boolean mIsLoading = true;
    private int mVisibleThreshold = 5;
    private boolean mIsNull;
    private int mLastVisibleItem, mTotalItemCount, mFirstVisibleItem, mVisibleItemCount, mPreviousTotal = 0;
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

    public void setLoadMorePresenter(final BaseAdapterLoadMoreListener presenter, RecyclerView recyclerView){
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mTotalItemCount = linearLayoutManager.getItemCount();
                mVisibleItemCount = linearLayoutManager.getChildCount();
                mFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!mIsLoading&&(mTotalItemCount-1)>=lastVisibleItem&&!mIsNull){
                    mPage++;
                    addProgress();
                    presenter.onLoadMore(mPage);
                    mIsLoading = true;
                }

            }
        });
    }

    public interface BaseAdapterLoadMoreListener extends BaseAdapterListener{
        void onLoadMore(int page);
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
        mPage = 1;
        mIsLoading = false;
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
        mIsLoading = false;
    }

    public void addMoreData(T t){
        if (mList==null){
            mList = new ArrayList<>(1);
        }
        removeProgress();
        mList.add(t);
        int index = mList.indexOf(t);
        notifyItemInserted(index);
        mIsLoading = false;
    }

    public void addMoreData(List<T> list){
        if (mList==null){
            mList = new ArrayList<>(1);
        }
        removeProgress();
        int startIndex = mList.size();
        mList.addAll(mList);
        notifyItemInserted(startIndex);
        mIsLoading = false;
    }

    public void setIsNull(){
        mIsNull = true;
    }

    public void addProgress(){
        if (mList!=null)
            mList = new ArrayList<>(1);
        int lastIndex = mList.size();
        mList.add(null);
        notifyItemInserted(lastIndex+1);
    }

    public void removeProgress(){
        if (mList!=null){
            if (mList.size()>0){
                int lastIndex = mList.size();
                mList.remove(lastIndex);
                notifyItemRemoved(lastIndex);
            }
        }
    }

    public void addData(List<T> list){
        if (mList==null){
            mList = new ArrayList<>(1);
        }
        int startIndex = mList.size();
        getData().addAll(mList);
        notifyItemInserted(startIndex);
        mIsLoading = false;
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
