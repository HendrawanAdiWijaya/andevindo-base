package com.andevindo.andevindobase.View;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by heendher on 11/21/2016.
 */

public class BaseHorizontalScrollView<T> extends HorizontalScrollView {

    private final int SWIPE_MIN_DISTANCE = 5;
    private final int SWIPE_THRESHOLD_VELOCITY = 1;

    private List<T> mList;
    private int mActiveFeature = 0;
    private LinearLayout mWrapperLinearLayout;
    private CountDownTimer mCountDownTimer;
    private DisplayMetrics mDisplaymetrics;
    private final int mPauseLimit = 2;
    private int mPauseTime = 0;
    private final int mScrollTime = 3000;//ms
    private GestureDetector mGestureDetector;
    private HorizontalScrollViewPresenter mPresenter = new HorizontalScrollViewPresenter() {
        @Override
        public void onChange(int position) {

        }

        @Override
        public void onOpenDetail(Object o) {

        }
    };

    public BaseHorizontalScrollView(Context context) {
        super(context);
        setWrapper();
    }

    void setWrapper() {
        mWrapperLinearLayout = new LinearLayout(getContext());
        mWrapperLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mWrapperLinearLayout);
    }

    public void removeViews() {
        if (mWrapperLinearLayout != null)
            mWrapperLinearLayout.removeAllViews();
        mActiveFeature = 0;
    }

    public void setHorizontalScollViewPresenter(HorizontalScrollViewPresenter presenter){
        mPresenter = presenter;
    }

    public void addData(T t, View view) {
        if (mList == null)
            mList = new ArrayList<>();
        mList.add(t);
        mWrapperLinearLayout.addView(view);
    }

    public void onReady(){
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPauseTime = 0;
                //If the user swipes
                if (mGestureDetector.onTouchEvent(event)) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        mGestureDetector = new GestureDetector(getContext(), new MyGestureDetector());
        smoothScrollTo(0, 0);
    }

    public void startAutoCycle() {
        if (mCountDownTimer == null)
            if (mList != null)
                if (mList.size() != 0 || mList.size() != 1) {
                    mCountDownTimer = new CountDownTimer(3000000, mScrollTime) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            if (mPauseTime >= mPauseLimit) {
                                if (mActiveFeature == mList.size() - 1) {
                                    mActiveFeature = 0;
                                    smoothScrollTo(0, 0);
                                    mPresenter.onChange(mActiveFeature);
                                } else {
                                    int featureWidth = getMeasuredWidth();
                                    mActiveFeature = (mActiveFeature < (mList.size() - 1)) ? mActiveFeature + 1 : mList.size() - 1;
                                    smoothScrollTo(mActiveFeature * featureWidth, 0);
                                    mPresenter.onChange(mActiveFeature);
                                }
                            } else {
                                mPauseTime++;
                            }

                        }

                        @Override
                        public void onFinish() {
                            mCountDownTimer.start();
                        }
                    };
                    mCountDownTimer.start();
                }


    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            mPresenter.onOpenDetail(mList.get(mActiveFeature));
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                //right to left
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    int featureWidth = getMeasuredWidth();
                    mActiveFeature = (mActiveFeature < (mList.size() - 1)) ? mActiveFeature + 1 : mList.size() - 1;
                    smoothScrollTo(mActiveFeature * featureWidth, 0);
                    mPresenter.onChange(mActiveFeature);
                    return true;
                }
                //left to right
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    int featureWidth = getMeasuredWidth();
                    mActiveFeature = (mActiveFeature > 0) ? mActiveFeature - 1 : 0;
                    smoothScrollTo(mActiveFeature * featureWidth, 0);
                    mPresenter.onChange(mActiveFeature);
                    return true;
                }
            } catch (Exception e) {
                Log.e("Fling", "There was an error processing the Fling event:" + e.getMessage() + e.getCause());
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            mPauseTime = 0;
            Log.d("Pause", "Pause");
            return super.onDown(e);
        }
    }

    public interface HorizontalScrollViewPresenter<T> {
        void onChange(int position);

        void onOpenDetail(T t);
    }

}
