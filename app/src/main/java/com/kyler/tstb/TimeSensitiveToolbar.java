package com.kyler.tstb;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;

import java.util.Calendar;

public class TimeSensitiveToolbar extends ToolbarMenudrawer {
    private static final int toolBarColorChangeDuration = 2000;
    private static final int statusBarColorChangeDuration = 1000;
    private static Toolbar mToolbar;
    private static ImageView timeOfDayIV;

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

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        // > greater than
        // < less than
        // :P

        if (hour == 0) {
            setMidnight();
            Toast.makeText(this, "It's midnight", Toast.LENGTH_LONG).show();
        } else if (hour >= 0 && hour <= 7) {
            setDawn();
            Toast.makeText(this, "It's dawn", Toast.LENGTH_LONG).show();
        } else if (hour >= 7 && hour <= 12) {
            setMorning();
            Toast.makeText(this, "It's morning", Toast.LENGTH_LONG).show();
        } else if (hour >= 12 && hour <= 15) {
            setAfternoon();
            Toast.makeText(this, "It's afternoon", Toast.LENGTH_LONG).show();
        } else if (hour >= 15 && hour <= 17) {
            setMidday();
            Toast.makeText(this, "It's Midday", Toast.LENGTH_LONG).show();
        } else if (hour >= 17 && hour <= 19) {
            setEvening();
            Toast.makeText(this, "It's the evening", Toast.LENGTH_LONG).show();
        } else if (hour >= 19 && hour <= 21) {
            setDusk();
            Toast.makeText(this, "It's dusk", Toast.LENGTH_LONG).show();
        } else if (hour >= 21 && hour <= 23) {
            setNighttime();
            Toast.makeText(this, "It's nighttime", Toast.LENGTH_LONG).show();
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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

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
                anim.setDuration(2000);
                anim.start();

            }
        }, 5000);

    }

    public final int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);

        // Credits for this: https://github.com/Musenkishi/wally
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
