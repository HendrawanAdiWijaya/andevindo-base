package com.andevindo.andevindobase.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andevindo.base.Adapter.BaseAdapter;
import com.andevindo.base.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private View mView;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.list_with_recyclerview_with_loading, container, false);
        LinearLayout contentContainer = (LinearLayout)mView.findViewById(R.id.content_state);
        RecyclerViewWithLoading recyclerViewWithLoading = (RecyclerViewWithLoading) mView.findViewById(R.id.recycler_with_loading);
        recyclerViewWithLoading.setCustomContent(getCustomView(inflater, contentContainer));

        return mView;
    }

    @Nullable
    public RecyclerViewWithLoading getRecyclerViewWithLoading() {
        return (RecyclerViewWithLoading) mView.findViewById(R.id.recycler_with_loading);
    }

    public abstract View getCustomView(LayoutInflater inflater, ViewGroup container);

    public List<RecyclerView.ItemDecoration> addItemDecoration() {
        return null;
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

}
