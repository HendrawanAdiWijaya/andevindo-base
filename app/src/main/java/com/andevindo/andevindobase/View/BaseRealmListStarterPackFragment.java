package com.andevindo.andevindobase.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andevindo.andevindobase.Adapter.BaseAdapter;
import com.andevindo.andevindobase.Adapter.BaseRealmAdapter;
import com.andevindo.andevindobase.R;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by heendher on 11/18/2016.
 */

public abstract class BaseRealmListStarterPackFragment<T extends RealmObject> extends Fragment {

    private RecyclerView mRecyclerView;
    private BaseRealmAdapter<T> mAdapter;
    private View mView;
    private ListStarterPack mListStarterPack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (fragmentViewId() == 0)
            mView = inflater.inflate(R.layout.fragment_list_starter_pack, container, false);
        else
            mView = inflater.inflate(fragmentViewId(), container, false);

        if (listStarterPack() == 0)
            mListStarterPack = (ListStarterPack) mView.findViewById(R.id.list_starter_pack);
        else
            mListStarterPack = (ListStarterPack) mView.findViewById(listStarterPack());

        if (recyclerViewId() == 0)
            mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler);
        else
            mRecyclerView = (RecyclerView) mView.findViewById(recyclerViewId());

        mAdapter = adapter();
        mRecyclerView.setAdapter(mAdapter);

        if (layoutManager() != null)
            mRecyclerView.setLayoutManager(layoutManager());
        else
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (addItemDecoration() == null) {
            //mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }else {
            List<RecyclerView.ItemDecoration> list = addItemDecoration();
            for (int i = 0; i < addItemDecoration().size(); i++) {
                mRecyclerView.addItemDecoration(list.get(i));
            }
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityReady();
    }

    protected abstract void onActivityReady();

    public View getView() {
        return mView;
    }

    public void setNestedScrolling(boolean enable){
        mRecyclerView.setNestedScrollingEnabled(enable);
    }

    public ListStarterPack getListStarterPack(){
        return mListStarterPack;
    }

    public void onContentState(){
        mListStarterPack.onContentState();
    }

    public void onNullState(){
        mListStarterPack.onNullState();
    }

    public void onServerErrorState(){
        mListStarterPack.onServerErrorState();
    }

    public void onNetworkErrorState(){
        mListStarterPack.onNetworkErrorState();
    }

    public void onLoadingState(){
        mListStarterPack.onLoadingState();
    }

    public void showMoreLoader(){
        mListStarterPack.showMoreLoader();
    }

    public void hideMoreLoader(){
        mListStarterPack.hideMoreLoader();
    }

    public void setListStarterPackPresenter(ListStarterPack.ListStarterPackPresenter presenter){
        mListStarterPack.setPresenter(presenter);
    }

    protected int fragmentViewId() {
        return 0;
    }

    protected int listStarterPack() {
        return 0;
    }

    protected int recyclerViewId() {
        return 0;
    }

    protected abstract BaseRealmAdapter<T> adapter();

    public BaseRealmAdapter<T> getAdapter() {
        return mAdapter;
    }

    public void setAdapterData(RealmList<T> list){
        mAdapter.setData(list);
    }

    protected RecyclerView.LayoutManager layoutManager(){
        return null;
    }

    public List<RecyclerView.ItemDecoration> addItemDecoration() {
        return null;
    }

    public void setAdapterBaseListener(BaseAdapter.BaseAdapterListener<T> listener){
        mAdapter.setBaseListener(listener);
    }

}
