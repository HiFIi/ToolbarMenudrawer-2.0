package com.kyler.tbmd2.activities;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.graphics.Palette;
import android.view.Window;

import com.kyler.tbmd2.R;
import com.kyler.tbmd2.ToolbarMenudrawer;
import com.kyler.tbmd2.utils.BuildUtils;

/**
 * Created by Kyler on 1/27/2015.
 */
public class PaletteActivity extends ToolbarMenudrawer {
    private static final int mToolbarPaddingTop = 48;
    private Drawable drawable;
    private Bitmap bitmap;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_PALETTE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.palette);

        drawable = (Drawable) getResources().getDrawable(R.drawable.palette_demo);
        bitmap = ((BitmapDrawable) drawable).getBitmap();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                paletteExample();
            }
        }, 2500);

        // left, top, right, bottom
        mToolbar.setPadding(0, mToolbarPaddingTop, 0, 0);
        super.mToolbar.setNavigationIcon(R.drawable.ic_drawer_dark);
    }

    private void paletteExample() {
        if (BuildUtils.isL() == true) {
            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {

                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onGenerated(final Palette palette) {
                    final Integer colorFrom = getResources().getColor(android.R.color.black);
                    final Integer colorTo = palette.getDarkVibrantColor(R.id.paletteIV);
                    ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
                    colorAnimation.setDuration(1500);
                    colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            Window window = getWindow();
                            window.setNavigationBarColor((Integer) animator.getAnimatedValue());
                        }

                    });
                    colorAnimation.start();
                }

                ;
            });
        }
    }
}
