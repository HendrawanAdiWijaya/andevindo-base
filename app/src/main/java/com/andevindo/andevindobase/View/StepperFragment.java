package com.andevindo.andevindobase.View;


import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andevindo.base.Model.Stepper;
import com.andevindo.base.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class StepperFragment extends Fragment {

    private View mView;
    private LinearLayout mStepperContainer;
    private int mLastIndex = 0;
    private List<Stepper> mList;
    private LinearLayout.LayoutParams hiddenLP, showUpLP;
    private int mMarginLeft;

    public StepperFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_stepper, container, false);

        mStepperContainer = (LinearLayout) mView.findViewById(R.id.stepper_container);
        hiddenLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.hiddenStepperContentHeight));
        showUpLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMarginLeft = (int) getResources().getDimension(R.dimen.stepperMarginLeft);
        hiddenLP.setMargins(mMarginLeft, 0, 0, 0);
        showUpLP.setMargins(mMarginLeft, 0, 0, 0);
        generateStepperView(setStepperData());

        return mView;
    }

    void generateStepperView(final List<Stepper> list) {

        mList = list;
        mList.get(0).setVisited(true);
        mList.get(0).setShowUp(true);

        for (int i = 0; i < list.size(); i++) {
            final int finalI = i;
            int stepperPadding = (int) getResources().getDimension(R.dimen.stepperPadding);
            View titleView = LayoutInflater.from(getContext()).inflate(R.layout.stepper_title, null);
            View contentView = LayoutInflater.from(getContext()).inflate(R.layout.stepper_content, null);

            Stepper s = list.get(i);
            final View content = s.getView();
            RelativeLayout viewContainer = (RelativeLayout) contentView.findViewById(R.id.view_container);
            viewContainer.addView(content);
            TextView title = (TextView) titleView.findViewById(R.id.title);
            title.setText(s.getTitle());
            TextView number = (TextView) titleView.findViewById(R.id.stepper_number);
            number.setText(i + 1 + "");
            ImageView circle = (ImageView) titleView.findViewById(R.id.stepper_circle);
            ImageView check = (ImageView) titleView.findViewById(R.id.stepper_check);
            Button positiveButton = (Button) contentView.findViewById(R.id.positive_button);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI != list.size() - 1) {
                        if (onNext(finalI)) {
                            mLastIndex = finalI;
                            mList.get(finalI).setComplete(true);
                            mList.get(finalI+1).setVisited(true);
                            changeViews(finalI + 1);
                        }
                    } else {
                        if (onDone(finalI)) {

                        }
                    }
                }
            });
            Button negativeButton = (Button) contentView.findViewById(R.id.negative_button);
            if (i == 0) {
                titleView.setPadding(stepperPadding, stepperPadding, 0, 0);
                DrawableCompat.setTint(circle.getBackground(), ContextCompat.getColor(getContext(), R.color.colorAccent));
                negativeButton.setVisibility(Button.GONE);
            } else {
                contentView.setPadding(0, 0, stepperPadding, stepperPadding);
                DrawableCompat.setTint(circle.getBackground(), ContextCompat.getColor(getContext(), R.color.colorInactiveStepper));
            }


            titleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mList.get(finalI).isVisited()) {
                        if (onNext(finalI))
                            mList.get(finalI).setComplete(true);
                        else
                            mList.get(finalI).setComplete(false);
                        if (onNext(mLastIndex))
                            mList.get(mLastIndex).setComplete(true);
                        else
                            mList.get(mLastIndex).setComplete(false);
                        mLastIndex = finalI;
                        changeViews(finalI);
                    }
                }
            });
            mStepperContainer.addView(titleView);
            mStepperContainer.addView(contentView);
            if (i != 0) {
                int childPosition = i + (i + 1);
                View view = ((LinearLayout) mStepperContainer.getChildAt(childPosition)).getChildAt(1);
                View stepperContent = ((LinearLayout) view).getChildAt(0);
                stepperContent.setVisibility(View.GONE);
                view.setLayoutParams(hiddenLP);

            }

            if (i == list.size() - 1) {
                ((LinearLayout) contentView).setShowDividers(0);
                positiveButton.setText("Kirim");
            }


            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNext(finalI))
                        mList.get(finalI).setComplete(true);
                    else
                        mList.get(finalI).setComplete(false);
                    if (onNext(mLastIndex))
                        mList.get(mLastIndex).setComplete(true);
                    else
                        mList.get(mLastIndex).setComplete(false);
                    mLastIndex = finalI;
                    changeViews(finalI - 1);
                }
            });

        }

    }

    public View getStepperView(){
        return mStepperContainer;
    }

    protected abstract boolean onNext(int index);

    protected abstract boolean onDone(int index);

    void changeViews(int chooseViewIndex) {


        for (int i = 0; i < mList.size(); i++) {
            int childPosition = i + (i + 1);
            if (i == chooseViewIndex)
                mList.get(i).setShowUp(true);
            else
                mList.get(i).setShowUp(false);
            View content = ((LinearLayout) mStepperContainer.getChildAt(childPosition)).getChildAt(1);
            RelativeLayout circleView = ((RelativeLayout) ((LinearLayout) mStepperContainer.getChildAt(childPosition - 1)).getChildAt(0));
            ImageView circle = (ImageView) circleView.getChildAt(0);
            TextView number = (TextView) circleView.getChildAt(1);
            ImageView check = (ImageView) circleView.getChildAt(2);
            Drawable accent = circle.getBackground();
            Drawable inactive = circle.getBackground();
            DrawableCompat.setTint(accent, ContextCompat.getColor(getContext(), R.color.colorAccent));
            DrawableCompat.setTint(inactive, ContextCompat.getColor(getContext(), R.color.colorInactiveStepper));
            if (mList.get(i).isComplete()) {
                DrawableCompat.setTint(circle.getBackground(), ContextCompat.getColor(getContext(), R.color.colorAccent));
                number.setVisibility(TextView.GONE);
                check.setVisibility(ImageView.VISIBLE);
            } else if (mList.get(i).isShowUp() && !mList.get(i).isComplete()) {
                DrawableCompat.setTint(circle.getBackground(), ContextCompat.getColor(getContext(), R.color.colorAccent));
                number.setVisibility(TextView.VISIBLE);
                check.setVisibility(ImageView.GONE);
            } else {
                DrawableCompat.setTint(circle.getBackground(), ContextCompat.getColor(getContext(), R.color.colorInactiveStepper));
                number.setVisibility(TextView.VISIBLE);
                check.setVisibility(ImageView.GONE);
            }
            View stepperContent = ((LinearLayout) content).getChildAt(0);
            if (chooseViewIndex == i) {
                stepperContent.setVisibility(View.VISIBLE);
                content.setLayoutParams(showUpLP);
            } else {
                stepperContent.setVisibility(View.GONE);
                content.setLayoutParams(hiddenLP);
            }

        }
    }

    public void readyToComplete(int index) {
        mList.get(index).setReadyToComplete(true);
    }

    public void cancelToComplete(int index) {

    }

    protected abstract List<Stepper> setStepperData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityReady();
    }

    protected abstract void onActivityReady();

}
