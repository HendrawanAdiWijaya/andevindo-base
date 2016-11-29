package com.andevindo.andevindobase.View;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andevindo.base.R;


/**
 * Created by heendher on 9/7/2016.
 */
public class RecyclerViewWithLoading extends RelativeLayout implements View.OnClickListener{

    private View mView;
    private ListHandlerPresenter mPresenter = new ListHandlerPresenter() {
        @Override
        public void onRefresh() {

        }

        @Override
        public void onTryAgain() {

        }
    };
    private HandlerPresenter mHandlerPresenter = new HandlerPresenter() {
        @Override
        public void onTryAgain() {

        }
    };
    private LinearLayout mConnectionErrorState, mServerErrorState, mNullState, mContentState, mLoadingState;
    private ImageView mBackground, mConnectionErrorRefresh, mServerErrorRefresh, mNullRefresh;
    private RecyclerView mRecycler;
    private SwipeRefreshLayout mSwipe;
    private TextView mNullDescription;

    public RecyclerViewWithLoading(Context context) {
        super(context);
        setupView();
    }

    public RecyclerViewWithLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView();
    }

    public RecyclerViewWithLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    void setupView(){
        mView = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_with_loading, null);
        mContentState = (LinearLayout)mView.findViewById(R.id.content_state);
        mLoadingState = (LinearLayout)mView.findViewById(R.id.loading_state);
        mConnectionErrorState = (LinearLayout)mView.findViewById(R.id.network_error_state);
        mServerErrorState = (LinearLayout)mView.findViewById(R.id.server_error_state);
        mNullState = (LinearLayout)mView.findViewById(R.id.null_state);
        mBackground = (ImageView)mView.findViewById(R.id.background);
        mConnectionErrorRefresh = (ImageView)mView.findViewById(R.id.network_refresh);
        mConnectionErrorRefresh.setOnClickListener(this);
        mServerErrorRefresh = (ImageView)mView.findViewById(R.id.server_refresh);
        mServerErrorRefresh.setOnClickListener(this);
        mNullRefresh = (ImageView)mView.findViewById(R.id.null_refresh);
        mNullRefresh.setOnClickListener(this);
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
        mRecycler = (RecyclerView)mView.findViewById(R.id.recycler);
        mNullDescription = (TextView)mView.findViewById(R.id.null_description);
        addView(mView);
    }

    public RecyclerView getRecycler(){
        return mRecycler;
    }

    public void setNullDescription(String description){
        mNullDescription.setText(description);
    }

    public void onContentState(){
        mContentState.setVisibility(LinearLayout.VISIBLE);
        if (mSwipe!=null){
            mSwipe.setEnabled(true);
            mSwipe.setRefreshing(false);
        }
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mConnectionErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mBackground.setVisibility(ImageView.INVISIBLE);
    }

    public void onLoadingState(){
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mSwipe!=null){
            mSwipe.setEnabled(false);
            mSwipe.setRefreshing(false);
        }
        mLoadingState.setVisibility(LinearLayout.VISIBLE);
        mConnectionErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mBackground.setVisibility(ImageView.VISIBLE);
    }

    public void onConnectionErrorState(){
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mSwipe!=null){
            mSwipe.setEnabled(false);
            mSwipe.setRefreshing(false);
        }
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mConnectionErrorState.setVisibility(LinearLayout.VISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mBackground.setVisibility(ImageView.VISIBLE);
    }

    public void onServerErrorState(){
        mContentState.setVisibility(LinearLayout.INVISIBLE);
        if (mSwipe!=null){
            mSwipe.setEnabled(false);
            mSwipe.setRefreshing(false);
        }
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mConnectionErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.VISIBLE);
        mNullState.setVisibility(LinearLayout.INVISIBLE);
        mBackground.setVisibility(ImageView.VISIBLE);
    }

    public void onNullState(){
        mContentState.setVisibility(LinearLayout.VISIBLE);
        if (mSwipe!=null){
            mSwipe.setEnabled(false);
            mSwipe.setRefreshing(false);
        }
        mLoadingState.setVisibility(LinearLayout.INVISIBLE);
        mConnectionErrorState.setVisibility(LinearLayout.INVISIBLE);
        mServerErrorState.setVisibility(LinearLayout.INVISIBLE);
        mNullState.setVisibility(LinearLayout.VISIBLE);
        mBackground.setVisibility(ImageView.VISIBLE);
    }

    public void setListListener(ListHandlerPresenter presenter){
        mPresenter = presenter;
    }

    public void setListener(HandlerPresenter presenter){
        mHandlerPresenter = presenter;
    }

    public void setCustomContent(View view){
        mContentState.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        mContentState.addView(view);
        Log.d("Count", mContentState.getChildCount() + "");
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.network_refresh||view.getId()==R.id.server_refresh||view.getId()==R.id.null_refresh){
            mPresenter.onTryAgain();
            mHandlerPresenter.onTryAgain();
        }
    }

    public interface ListHandlerPresenter{
        void onRefresh();
        void onTryAgain();
    }

    public interface HandlerPresenter{
        void onTryAgain();
    }

}
