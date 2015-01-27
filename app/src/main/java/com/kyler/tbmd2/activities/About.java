package com.kyler.tbmd2.activities;

import android.os.Bundle;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

/**
 * Created by Kyler on 1/27/2015.
 */
public class About extends ToolbarMenudrawer {
  /*  private View headerView;
    private static final int toolBarColorChangeDuration = 1500;
    private static final int statusBarColorChangeDuration = 750; */

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_ABOUT;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        //    headerView = findViewById(R.id.about_header);

        //    animateColorChanges();

    }

/*    private final void animateColorChanges() {
        final Integer colorFrom = getResources().getColor(R.color.main_app_color);
        final Integer colorTo = getResources().getColor(R.color.about_header_bg);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        animateStatusbarColorChange();
    }

    private final void animateStatusbarColorChange() {
        final Integer colorFrom = getResources().getColor(R.color.about_header_bg);
        final Integer colorTo = getResources().getColor(R.color.about_header_bg_darker);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private final void animateColorChanges() {
        headerView.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {

                final Integer colorFrom = getResources().getColor(R.color.main_app_color);
                final Integer colorTo = getResources().getColor(R.color.about_header_bg);
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.setDuration(statusBarColorChangeDuration);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());

                    }

                });
                colorAnimation.start();
            }
        }, 200);
    } */
}
