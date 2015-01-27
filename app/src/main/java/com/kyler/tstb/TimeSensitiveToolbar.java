package com.kyler.tstb;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;
import com.kyler.tbmd2.ui.TBMDTextView;

import java.util.Calendar;

public class TimeSensitiveToolbar extends ToolbarMenudrawer {
    protected static final AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
    protected static final int RevealDuration = 1000;
    private static final int toolBarColorChangeDuration = 1200;
    private static final int statusBarColorChangeDuration = 50;
    private static final int fadeInText = 600;
    private static Toolbar mToolbar;
    private static ImageView timeOfDayIV;
    private static TBMDTextView timeOfDayText;
    private static RelativeLayout rl;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_TSTB;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_sensitive_toolbar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //    mToolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        timeOfDayIV = (ImageView) findViewById(R.id.timeOfDayIV);
        timeOfDayIV.setVisibility(View.INVISIBLE);

        timeOfDayText = (TBMDTextView) findViewById(R.id.timeOfDayText);

        rl = (RelativeLayout) findViewById(R.id.rl_tstb);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        // > greater than
        // < less than
        // :P

        if (hour == 0) {
            setMidnight();
        } else if (hour >= 0 && hour <= 7) {
            setDawn();
        } else if (hour >= 7 && hour <= 12) {
            setMorning();
        } else if (hour >= 12 && hour <= 15) {
            setAfternoon();
        } else if (hour >= 15 && hour <= 17) {
            setMidday();
        } else if (hour >= 17 && hour <= 18) {
            setEvening();
        } else if (hour >= 18 && hour <= 21) {
            setDusk();
        } else if (hour >= 21 && hour <= 23) {
            setNighttime();
        }
    }

    private final void setDawn() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.dawn);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(toolBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mToolbar.setBackgroundColor((Integer) animator.getAnimatedValue());
                getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setDawnStatusbarColor();

        timeOfDayText.setText(R.string.its_dawn);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private final void setDawnStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.midnight);
        final Integer colorTo = getResources().getColor(R.color.dawn_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddDawnIV();
    }

    private final void setMorning() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.morning);
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
        setMorningStatusbarColor();

        timeOfDayText.setText(R.string.its_morning);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private final void setMorningStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.dawn);
        final Integer colorTo = getResources().getColor(R.color.morning_20);
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
        setAddMorningIV();
    }

    private final void setAfternoon() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.afternoon);
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
        setAfternoonStatusbarColor();

        timeOfDayText.setText(R.string.its_afternoon);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private final void setAfternoonStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.afternoon);
        final Integer colorTo = getResources().getColor(R.color.afternoon_20);
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
        setAddAfternoonIV();
    }

    private final void setMidday() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.midday);
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
        setMiddayStatusbarColor();

        timeOfDayText.setText(R.string.its_midday);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private final void setMiddayStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.midday);
        final Integer colorTo = getResources().getColor(R.color.midday_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
           /*   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); */
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddMiddayIV();
    }

    private final void setEvening() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.evening);
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
        setEveningStatusbarColor();

        timeOfDayText.setText(R.string.its_evening);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(fadeInText);
        fadeIn.setFillAfter(true);
    }

    private final void setEveningStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.evening);
        final Integer colorTo = getResources().getColor(R.color.evening_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
           /*   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); */
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddEveningIV();
    }

    private final void setDusk() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.dusk);
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
        setDuskStatusbarColor();

        timeOfDayText.setText(R.string.its_dusk);
        timeOfDayText.startAnimation(fadeIn);

        fadeIn.setDuration(1200);
        fadeIn.setFillAfter(true);
    }

    private final void setDuskStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.dusk);
        final Integer colorTo = getResources().getColor(R.color.dusk_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                Window window = getWindow();
           /*   window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); */
                window.setStatusBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
        setAddDuskIV();
    }

    private final void setNighttime() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.nighttime);
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
        setNighttimeStatusbarColor();
    }

    private final void setNighttimeStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.nighttime);
        final Integer colorTo = getResources().getColor(R.color.nighttime_20);
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
        setAddNighttimeIV();
    }

    private final void setMidnight() {
        final Integer colorFrom = getResources().getColor(android.R.color.transparent);
        final Integer colorTo = getResources().getColor(R.color.midnight);
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
        setMidnightStatusbarColor();
    }

    private final void setMidnightStatusbarColor() {
        final Integer colorFrom = getResources().getColor(R.color.midnight);
        final Integer colorTo = getResources().getColor(R.color.midnight_20);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                getWindow().setStatusBarColor((Integer) animator.getAnimatedValue());

            }

        });
        colorAnimation.start();
        setAddMidnightIV();
    }

    private final void setAddDawnIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.dawn));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private final void setAddMorningIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.morning));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private final void setAddAfternoonIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.afternoon));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private final void setAddMiddayIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.midday));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private final void setAddEveningIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.evening));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }


    private final void setAddDuskIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.dusk));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    private final void setAddNighttimeIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.nighttime));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

        darkenRL();
    }

    private final void darkenRL() {
        rl.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                final Integer colorFrom = getResources().getColor(android.R.color.white);
                final Integer colorTo = getResources().getColor(R.color.lightish_black);
                ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                colorAnimation.setDuration(statusBarColorChangeDuration);
                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        rl.setBackgroundColor((Integer) animator.getAnimatedValue());
                    }

                });
                colorAnimation.start();
            }
        }, 4500);

        fadeInTextView();
    }

    private final void fadeInTextView() {
        timeOfDayText.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {

                timeOfDayText.setTextColor(getResources().getColor(android.R.color.white));
                timeOfDayText.setText(R.string.its_nighttime);
                timeOfDayText.startAnimation(fadeIn);

                fadeIn.setDuration(1200);
                fadeIn.setFillAfter(true);

            }
        }, 4000);

        setNavBarNightColor();
    }

    private final void setNavBarNightColor() {
        final Integer colorFrom = getResources().getColor(android.R.color.black);
        final Integer colorTo = getResources().getColor(R.color.lightish_black_darker);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(statusBarColorChangeDuration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                getWindow().setNavigationBarColor((Integer) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }

    private final void setAddMidnightIV() {
        timeOfDayIV.setImageDrawable(getResources().getDrawable(R.drawable.midnight));
        timeOfDayIV.postDelayed(new Runnable() {

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public final void run() {
                // get the center for the clipping circle
                final int cx = (timeOfDayIV.getLeft() + timeOfDayIV.getRight()) / 2;
                final int cy = (timeOfDayIV.getTop() + timeOfDayIV.getBottom()) / 2;

                // get the final radius for the clipping circle
                final int finalRadius = Math.max(timeOfDayIV.getWidth(), timeOfDayIV.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(timeOfDayIV, cx, cy, 0, finalRadius);

                // make the view visible and start the animation
                timeOfDayIV.setVisibility(View.VISIBLE);
                anim.setDuration(RevealDuration);
                anim.start();

            }
        }, 3000);

    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.time_sensitive_toolbar_menu, menu);
        return true;
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
