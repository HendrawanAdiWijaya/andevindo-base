package com.andevindo.andevindobase.View;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andevindo.andevindobase.R;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

/**
 * Created by heendher on 11/18/2016.
 */

public class ListStarterPack extends RelativeLayout{

    private NestedScrollView mScrollState;
    private AVLoadingIndicatorView mLoader, mMoreLoader;
    private ImageView mServerErrorImage, mNetworkErrorImage, mNullImage;
    private TextView mServerErrorTitle, mNetworkErrorTitle, mNullTitle;
    private ImageView mServerErrorRefresh, mNetworkErrorRefresh, mNullRefresh;
    private LinearLayout mNetworkErrorState, mServerErrorState, mNullState, mContentState, mLoadingState;
    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipe;
    private View mView;
    private ListStarterPackPresenter mPresenter = new ListStarterPackPresenter() {
        @Override
        public void onRefresh() {

        }

        @Override
        public void onTryAgain() {

        }
    };

    public interface ListStarterPackPresenter{
        void onRefresh();
        void onTryAgain();
    }

    public ListStarterPack(Context context) {
        super(context);
        setupView();
    }

    public ListStarterPack(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView();
    }

    public ListStarterPack(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    private void setupView(){
        mView = LayoutInflater.from(getContext()).inflate(R.layout.list_starter_pack, null);

        mScrollState = (NestedScrollView)mView.findViewById(R.id.scroll_state);
        mContentState = (LinearLayout)mView.findViewById(R.id.content_state);
        mMoreLoader = (AVLoadingIndicatorView)mView.findViewById(R.id.more_loader);
        mRecycler = (RecyclerView)mView.findViewById(R.id.recycler);
        mSwipe = (SwipeRefreshLayout)mView.findViewById(R.id.swipe);
        mSwipe.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent),
                ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onRefresh();
            }
        });

        mLoadingState = (LinearLayout)mView.findViewById(R.id.loading_state);
        mLoader = (AVLoadingIndicatorView)mView.findViewById(R.id.loading_indicator);


        mNetworkErrorState = (LinearLayout)mView.findViewById(R.id.network_error_state);
        mNetworkErrorRefresh = (ImageView)mView.findViewById(R.id.network_refresh);
        mNetworkErrorRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onTryAgain();
            }
        });
        mNetworkErrorTitle = (TextView)mView.findViewById(R.id.network_error_title);
        mNetworkErrorImage = (ImageView)mView.findViewById(R.id.network_error_image);

        mServerErrorState = (LinearLayout)mView.findViewById(R.id.server_error_state);
        mServerErrorRefresh = (ImageView)mView.findViewById(R.id.server_error_refresh);
        mServerErrorRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onTryAgain();
            }
        });
        mServerErrorTitle = (TextView)mView.findViewById(R.id.server_error_title);
        mServerErrorImage = (ImageView)mView.findViewById(R.id.server_error_image);

        mNullState = (LinearLayout)mView.findViewById(R.id.null_state);
        mNullRefresh = (ImageView)mView.findViewById(R.id.null_refresh);
        mNullRefresh.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onTryAgain();
            }
        });
        mNullTitle = (TextView)mView.findViewById(R.id.null_title);
        mNullImage = (ImageView)mView.findViewById(R.id.null_image);

        addView(mView);
    }

    public void nestedScrolling(boolean enable){
        mRecycler.setNestedScrollingEnabled(enable);
    }

    public void onContentState(){
        mScrollState.setVisibility(NestedScrollView.INVISIBLE);
        mContentState.setVisibility(LinearLayout.VISIBLE);
        if (mMoreLoader.isShown())
            mMoreLoader.hide();
        if (mSwipe.isRefreshing())
            mSwipe.setRefreshing(false);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mLoader.smoothToHide();
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mNetworkErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
    }

    public void onNullState(){
        mScrollState.setVisibility(NestedScrollView.VISIBLE);
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mMoreLoader.isShown())
            mMoreLoader.hide();
        if (mSwipe.isRefreshing())
            mSwipe.setRefreshing(false);
        mNullState.setVisibility(LinearLayout.VISIBLE);
        mLoader.smoothToHide();
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mNetworkErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
    }

    public void onNetworkErrorState(){
        mScrollState.setVisibility(NestedScrollView.VISIBLE);
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mMoreLoader.isShown())
            mMoreLoader.hide();
        if (mSwipe.isRefreshing())
            mSwipe.setRefreshing(false);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mLoader.smoothToHide();
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mNetworkErrorState.setVisibility(LinearLayout.VISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
    }

    public void onServerErrorState(){
        mScrollState.setVisibility(NestedScrollView.VISIBLE);
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mMoreLoader.isShown())
            mMoreLoader.hide();
        if (mSwipe.isRefreshing())
            mSwipe.setRefreshing(false);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mLoader.smoothToHide();
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mNetworkErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.VISIBLE);
    }

    public void onLoadingState(){
        mScrollState.setVisibility(NestedScrollView.VISIBLE);
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mMoreLoader.isShown())
            mMoreLoader.hide();
        if (mSwipe.isRefreshing())
            mSwipe.setRefreshing(false);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mLoadingState.setVisibility(LinearLayout.VISIBLE);
        mLoader.smoothToShow();
        mNetworkErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
    }

    public void showMoreLoader(){
        mMoreLoader.smoothToShow();
    }

    public void hideMoreLoader(){
        mMoreLoader.smoothToHide();
    }

    public void setPresenter(ListStarterPackPresenter presenter){
        mPresenter = presenter;
    }

    public void setLoaderType(IndicatorTypes indicatorTypes){
        mLoader.setIndicator(indicatorTypes.name());
    }

    public void setMoreLoaderType(IndicatorTypes indicatorTypes){
        mMoreLoader.setIndicator(indicatorTypes.name());
    }

    public void setLoaderColor(int color){
        mLoader.setIndicatorColor(color);
    }

    public void setMoreLoaderColor(int color){
        mMoreLoader.setIndicatorColor(color);
    }

    public void setServerErrorTitle(String title){
        mServerErrorTitle.setText(title);
    }

    public void setServerErrorImage(int resId){
        mServerErrorImage.setImageResource(resId);
    }

    public void setNetworkErrorTitle(String title){
        mNetworkErrorTitle.setText(title);
    }

    public void setNetworkErrorImage(int resId){
        mNetworkErrorImage.setImageResource(resId);
    }

    public void setNullTitle(String title){
        mNullTitle.setText(title);
    }

    public void setNullImage(int resId){
        mNullImage.setImageResource(resId);
    }

}
