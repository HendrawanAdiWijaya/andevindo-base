package com.andevindo.andevindobase.View;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.andevindo.andevindobase.Adapter.BaseAdapter;
import com.andevindo.andevindobase.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseListFragment<T> extends Fragment {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mView;
    private BaseAdapter<T> mAdapter;


    public BaseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (fragmentViewId() == 0)
            mView = inflater.inflate(R.layout.list_with_recyclerview_with_loading, container, false);
        else
            mView = inflater.inflate(fragmentViewId(), container, false);

        if (recyclerViewId() == 0)
            mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler);
        else
            mRecyclerView = (RecyclerView) mView.findViewById(recyclerViewId());

        if (swipeRefreshId() != 0) {
            mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(swipeRefreshId());

        }else{
            mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe);
        }

        mAdapter = recyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        if (layoutManager() != null)
            mRecyclerView.setLayoutManager(layoutManager());
        else
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (addItemDecoration() == null)
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        else {
            List<RecyclerView.ItemDecoration> list = addItemDecoration();
            for (int i = 0; i < addItemDecoration().size(); i++) {
                mRecyclerView.addItemDecoration(list.get(i));
            }
        }


        return mView;
    }

    @Nullable
    public RecyclerViewWithLoading getRecyclerViewWithLoading() {
        return (RecyclerViewWithLoading) mView.findViewById(R.id.recycler_with_loading);
    }

    public List<RecyclerView.ItemDecoration> addItemDecoration() {
        return null;
    }

    public void onSwipeRefresh() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityReady();
    }

    public View getView() {
        return mView;
    }

    protected abstract void onActivityReady();

    protected int fragmentViewId(){
        return 0;
    }

    protected int recyclerViewId(){
        return 0;
    }

    protected RecyclerView.LayoutManager layoutManager(){
        return null;
    }

    protected int swipeRefreshId(){
        return 0;
    }

    protected abstract BaseAdapter<T> recyclerViewAdapter();

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public BaseAdapter<T> getAdapter() {
        return mAdapter;
    }

    public void setOnLoading() {
        if (swipeRefreshId() != 0)
            mSwipeRefreshLayout.setRefreshing(true);
    }

    public void setOnDone() {
        if (swipeRefreshId() != 0)
            mSwipeRefreshLayout.setRefreshing(false);
    }
}
