package com.kyler.tbmd2;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.samples.apps.iosched.ui.widget.ScrimInsetsScrollView;
import com.kyler.tbmd2.activities.Home;
import com.kyler.tbmd2.activities.WebViewTBMD;
import com.kyler.tbmd2.ui.TBMDTextView;
import com.kyler.tbmd2.utils.LUtils;
import com.kyler.tbmd2.utils.UIUtils;
import com.kyler.tstb.TimeSensitiveToolbar;

import java.util.ArrayList;

public class ToolbarMenudrawer extends ActionBarActivity {

    // symbols for navdrawer items (indices must correspond to array below). This is
    // not a list of items that are necessarily *present* in the Nav Drawer; rather,
    // it's a list of all possible items.
    protected static final int NAVDRAWER_ITEM_HOME = 0;
    protected static final int NAVDRAWER_ITEM_WEBVIEW = 1;
    protected static final int NAVDRAWER_ITEM_TSTB = 2;
    protected static final int NAVDRAWER_ITEM_VOICE_COMMANDS = 3;
    protected static final int NAVDRAWER_ITEM_CONTACT = 4;
    protected static final int NAVDRAWER_ITEM_ABOUT = 5;
    protected static final int NAVDRAWER_ITEM_BUG_REPORT = 6;
    protected static final int NAVDRAWER_ITEM_REQUEST = 7;

    protected static final int NAVDRAWER_ITEM_INVALID = -1;
    protected static final int NAVDRAWER_ITEM_SETTINGS = -2;
    protected static final int NAVDRAWER_ITEM_SEPARATOR = -3;
    protected static final int NAVDRAWER_ITEM_SEPARATOR_SPECIAL = -4;

    private static final String TAG_FOLDERDEBUG = "ThemeTemplate";

    private static final int FADE_CROSSOVER_TIME_MILLIS = 300;

    private static final TypeEvaluator ARGB_EVALUATOR = new ArgbEvaluator();
    // Durations for certain animations we use:
    private static final int HEADER_HIDE_ANIM_DURATION = 100;
    // delay to launch nav drawer item, to allow close animation to play
    private static final int NAVDRAWER_LAUNCH_DELAY = 400;

    private static final int NAVDRAWER_LAUNCH_DELAY_LONGER = 1150;
    // fade in and fade out durations for the main content when switching between
    // different Activities of the app through the Nav Drawer
    private static final int MAIN_CONTENT_FADEOUT_DURATION = 400;
    private static final int MAIN_CONTENT_FADEIN_DURATION = 400;
    // titles for navdrawer items (indices must correspond to the above)
    // titles for navdrawer items (indices must correspond to the above)
    private static final int[] NAVDRAWER_TITLE_RES_ID = new int[]{
            R.string.home,
            R.string.webview,
            R.string.tstb,
            R.string.voice_commands,
            R.string.contact,
            R.string.about,
            R.string.bug_report,
            R.string.request

    };

    // icons for navdrawer items (indices must correspond to above array)
    private static final int[] NAVDRAWER_ICON_RES_ID = new int[]{
            R.drawable.ic_home,
            R.drawable.ic_webview,
            R.drawable.ic_tstb,
            R.drawable.ic_voice_commands,
            0,
            0,
            0,
            0
    };

    private static final String appNameFolder = "/ThemeTemplate";
    public TBMDTextView tv;
    public Toolbar mToolbar;
    // variables that control the Action Bar auto hide behavior (aka "quick recall")
    private int mActionBarAutoHideSensivity = 0;
    private int mActionBarAutoHideMinY = 0;
    private int mActionBarAutoHideSignal = 0;
    private Context context;
    private DrawerLayout mDrawerLayout;
    private ObjectAnimator mStatusBarColorAnimator;
    private Handler mHandler;
    private int mThemedStatusBarColor;
    private int mNormalStatusBarColor;
    // variables that control the Action Bar auto hide behavior (aka "quick recall")
    private boolean mActionBarAutoHideEnabled = false;
    private boolean mActionBarShown = true;
    // A Runnable that we should execute when the navigation drawer finishes its closing animation
    private Runnable mDeferredOnDrawerClosedRunnable;
    private ViewGroup mDrawerItemsListContainer;
    // list of navdrawer items that were actually added to the navdrawer, in order
    private ArrayList<Integer> mNavDrawerItems = new ArrayList<Integer>();
    // views that correspond to each navdrawer item, null if not yet created
    private View[] mNavDrawerItemViews = null;
    // Helper methods for L APIs
    private LUtils mLUtils;
    // When set, these components will be shown/hidden in sync with the action bar
    // to implement the "quick recall" effect (the Action Bar and the header views disappear
    // when you scroll down a list, and reappear quickly when you scroll up).
    private ArrayList<View> mHideableHeaderViews = new ArrayList<View>();

    // What nav drawer item should be selected?
    private int selfItem = getSelfNavDrawerItem();

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UIUtils.enableDisableActivitiesByFormFactor(this);

        // We use the SharePreferences method to check whether or not the app has been run before.
        // If not, open the Splashscreen.
        SharedPreferences first = PreferenceManager
                .getDefaultSharedPreferences(this);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            super.finish();
        }

        if (!first.getBoolean("firstTime", false)) {

            new Handler().post(new Runnable() {
                @Override
                public void run() {

            /*        // Basically, we're creating a folder on the first time the app runs.
                    // This is necessary for storing cache and miscellaneous other items.
                    File folder = new File(Environment.getExternalStorageDirectory() + appNameFolder);
                    boolean success = true;

                    if (!folder.exists()) {
                        success = folder.mkdir();

                    } else if (folder.exists()) {
                        Log.d(TAG_FOLDERDEBUG, "The folder already exists.");

                    }
                    if (success) {
                        Log.d(TAG_FOLDERDEBUG, "Success!");

                    } else if (!success) {
                        Log.d(TAG_FOLDERDEBUG, "Failure! D:");
                    }

                    Intent first = new Intent(ThemeTemplate.this, Welcome.class);
                    first.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(first);
                    finish(); */

                }
            });


            SharedPreferences.Editor editor = first.edit();

            editor.putBoolean("firstTime", true);
            editor.commit();

        }

        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mHandler = new Handler();
        //    RecentTasksStyler.styleRecentTasksEntry(this);
        mThemedStatusBarColor = getResources().getColor(R.color.main_app_color);
        mNormalStatusBarColor = mThemedStatusBarColor;

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();

    }

    /**
     * Returns the navigation drawer item that corresponds to this Activity. Subclasses
     * of BaseActivity override this to indicate what nav drawer item corresponds to them
     * Return NAVDRAWER_ITEM_INVALID to mean that this Activity should not have a Nav Drawer.
     */
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_INVALID;
    }

    private void setupNavDrawer() {

        int toolbarHeight = 0;
        TypedValue tv = new TypedValue();

        toolbarHeight = (int) (tv.getDimension(getResources().getDisplayMetrics()) / getResources().getDisplayMetrics().density);
        int drawerMaxWidth = (int) (getResources().getDimension(R.dimen.nav_drawer_max_width) / getResources().getDisplayMetrics().density);
        Configuration configuration = getResources().getConfiguration();
        int screenWidthDp = configuration.screenWidthDp;
        int drawerWidth = screenWidthDp - toolbarHeight;

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (mDrawerLayout == null) {
            return;
        }

        mDrawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.main_app_color));

        ScrimInsetsScrollView navDrawer = (ScrimInsetsScrollView)
                mDrawerLayout.findViewById(R.id.navdrawer);

        ViewGroup.LayoutParams layout_description = navDrawer.getLayoutParams();
        layout_description.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                drawerWidth < drawerMaxWidth ? drawerWidth : drawerMaxWidth,
                getResources().getDisplayMetrics());
        navDrawer.setLayoutParams(layout_description);

        if (selfItem == NAVDRAWER_ITEM_INVALID) {
            // do not show a nav drawer
            if (navDrawer != null) {
                ((ViewGroup) navDrawer.getParent()).addView(navDrawer);
            }
            return;
        }

        if (navDrawer != null) {
            final View chosenAccountContentView = findViewById(R.id.chosen_account_content_view);
            final View chosenAccountView = findViewById(R.id.chosen_account_view);
            navDrawer.setOnInsetsCallback(new ScrimInsetsScrollView.OnInsetsCallback() {
                @Override
                public void onInsetsChanged(Rect insets) {
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)
                            chosenAccountContentView.getLayoutParams();
                    lp.topMargin = insets.top;
                    chosenAccountContentView.setLayoutParams(lp);

                    ViewGroup.LayoutParams lp2 = chosenAccountView.getLayoutParams();
                    chosenAccountView.setLayoutParams(lp2);
                }
            });
        }

        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.ic_drawer);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            });
        }

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener()

                                        {
                                            @Override
                                            public void onDrawerClosed(View drawerView) {
                                                // run deferred action, if we have one
                                                if (mDeferredOnDrawerClosedRunnable != null) {
                                                    mDeferredOnDrawerClosedRunnable.run();
                                                    mDeferredOnDrawerClosedRunnable = null;
                                                }
                                                onNavDrawerStateChanged(false, false);
                                            }

                                            @Override
                                            public void onDrawerOpened(View drawerView) {
                                                onNavDrawerStateChanged(true, false);
                                            }

                                            @Override
                                            public void onDrawerStateChanged(int newState) {
                                                onNavDrawerStateChanged(isNavDrawerOpen(), newState != DrawerLayout.STATE_IDLE);
                                            }

                                            @Override
                                            public void onDrawerSlide(View drawerView, float slideOffset) {
                                                onNavDrawerSlide(slideOffset);
                                            }
                                        }

        );

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        // populate the nav drawer with the correct items
        populateNavDrawer();

        //    mDrawerLayout.openDrawer(Gravity.START);
    }


    // Subclasses can override this for custom behavior
    protected void onNavDrawerStateChanged(boolean isOpen, boolean isAnimating) {
        if (mActionBarAutoHideEnabled && isOpen) {
            autoShowOrHideActionBar(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.toolbar_menudrawer_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            default:

        }

        return super.onOptionsItemSelected(item);
    }

    public void onStart() {
        super.onStart();

    }

    protected void onNavDrawerSlide(float offset) {

    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }

    private void goToNavDrawerItem(int item) {
        Intent intent;
        switch (item) {
            case NAVDRAWER_ITEM_HOME:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;

            case NAVDRAWER_ITEM_WEBVIEW:
                intent = new Intent(this, WebViewTBMD.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;

            case NAVDRAWER_ITEM_TSTB:
                intent = new Intent(this, TimeSensitiveToolbar.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;

        }
    }

    private void onNavDrawerItemClicked(final int itemId) {
        if (itemId == getSelfNavDrawerItem()) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }

        if (isSpecialItem(itemId)) {
            goToNavDrawerItem(itemId);
        } else {
            // launch the target Activity after a short delay, to allow the close animation to play
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToNavDrawerItem(itemId);
                }
            }, NAVDRAWER_LAUNCH_DELAY_LONGER);

            // change the active item on the list so the user can see the item changed
            setSelectedNavDrawerItem(itemId);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDrawerLayout.closeDrawer(Gravity.START);
                }
            }, NAVDRAWER_LAUNCH_DELAY);

            // fade out the main content
            View mainContent = findViewById(R.id.main_content);
            if (mainContent != null) {
                mainContent.animate().alpha(0).setDuration(MAIN_CONTENT_FADEOUT_DURATION);
            }
        }
    }

    /**
     * Populates the navigation drawer with the appropriate items.
     */
    private void populateNavDrawer() {
        mNavDrawerItems.clear();
        mNavDrawerItems.add(NAVDRAWER_ITEM_HOME);
        mNavDrawerItems.add(NAVDRAWER_ITEM_WEBVIEW);
        mNavDrawerItems.add(NAVDRAWER_ITEM_TSTB);
        mNavDrawerItems.add(NAVDRAWER_ITEM_VOICE_COMMANDS);

        mNavDrawerItems.add(NAVDRAWER_ITEM_SEPARATOR_SPECIAL);
        mNavDrawerItems.add(NAVDRAWER_ITEM_CONTACT);
        mNavDrawerItems.add(NAVDRAWER_ITEM_ABOUT);
        mNavDrawerItems.add(NAVDRAWER_ITEM_BUG_REPORT);
        mNavDrawerItems.add(NAVDRAWER_ITEM_REQUEST);

        createNavDrawerItems();
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void createNavDrawerItems() {
        mDrawerItemsListContainer = (ViewGroup) findViewById(R.id.navdrawer_items_list);
        if (mDrawerItemsListContainer == null) {
            return;
        }

        mNavDrawerItemViews = new View[mNavDrawerItems.size()];
        mDrawerItemsListContainer.removeAllViews();
        int i = 0;
        for (int itemId : mNavDrawerItems) {
            mNavDrawerItemViews[i] = makeNavDrawerItem(itemId, mDrawerItemsListContainer);
            mDrawerItemsListContainer.addView(mNavDrawerItemViews[i]);
            ++i;
        }
    }

    /**
     * Sets up the given navdrawer item's appearance to the selected state. Note: this could
     * also be accomplished (perhaps more cleanly) with state-based layouts.
     */
    private void setSelectedNavDrawerItem(int itemId) {
        if (mNavDrawerItemViews != null) {
            for (int i = 0; i < mNavDrawerItemViews.length; i++) {
                if (i < mNavDrawerItems.size()) {
                    int thisItemId = mNavDrawerItems.get(i);
                    formatNavDrawerItem(mNavDrawerItemViews[i], thisItemId, itemId == thisItemId);
                }
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADEIN_DURATION);
        }
    }

    protected Toolbar getActionBarToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    public int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);

        // Credits for this: https://github.com/Musenkishi/wally
    }

    private View makeNavDrawerItem(final int itemId, ViewGroup container) {
        boolean selected = getSelfNavDrawerItem() == itemId;
        int layoutToInflate = 0;
        if (itemId == NAVDRAWER_ITEM_SEPARATOR) {
            layoutToInflate = R.layout.navdrawer_separator;
        } else if (itemId == NAVDRAWER_ITEM_SEPARATOR_SPECIAL) {
            layoutToInflate = R.layout.navdrawer_separator;
        } else {
            layoutToInflate = R.layout.navdrawer_item;
        }
        View view = getLayoutInflater().inflate(layoutToInflate, container, false);

        if (isSeparator(itemId)) {

            return view;
        }

        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView titleView = (TextView) view.findViewById(R.id.title);

        int iconId = itemId >= 0 && itemId < NAVDRAWER_ICON_RES_ID.length ?
                NAVDRAWER_ICON_RES_ID[itemId] : 0;
        int titleId = itemId >= 0 && itemId < NAVDRAWER_TITLE_RES_ID.length ?
                NAVDRAWER_TITLE_RES_ID[itemId] : 0;

        // set icon and text
        iconView.setVisibility(iconId > 0 ? View.VISIBLE : View.GONE);
        if (iconId > 0) {
            iconView.setImageResource(iconId);
        }
        titleView.setText(getString(titleId));

        formatNavDrawerItem(view, itemId, selected);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavDrawerItemClicked(itemId);
            }
        });

        return view;
    }

    protected void autoShowOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        onActionBarAutoShowOrHide(show);
    }

    private boolean isSeparator(int itemId) {
        return itemId == NAVDRAWER_ITEM_SEPARATOR || itemId == NAVDRAWER_ITEM_SEPARATOR_SPECIAL;
    }

    private void formatNavDrawerItem(View view, int itemId, boolean selected) {
        if (isSeparator(itemId)) {
            // not applicable
            return;
        }

        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);

        // configure its appearance according to whether or not it's selected
        titleView.setTextColor(selected ?
                getResources().getColor(android.R.color.white) :
                getResources().getColor(R.color.navdrawer_item_text_color));
        iconView.setColorFilter(selected ?
                getResources().getColor(android.R.color.white) :
                getResources().getColor(R.color.imageview_unselected_color));
        ll.setBackgroundColor(selected ?
                getResources().getColor(R.color.main_app_color) :
                getResources().getColor(android.R.color.white));

    }

    /**
     * Initializes the Action Bar auto-hide (aka Quick Recall) effect.
     */
    private void initActionBarAutoHide() {
        mActionBarAutoHideEnabled = true;
        mActionBarAutoHideMinY = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_min_y);
        mActionBarAutoHideSensivity = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_sensivity);
    }

    /**
     * Indicates that the main content has scrolled (for the purposes of showing/hiding
     * the action bar for the "action bar auto hide" effect). currentY and deltaY may be exact
     * (if the underlying view supports it) or may be approximate indications:
     * deltaY may be INT_MAX to mean "scrolled forward indeterminately" and INT_MIN to mean
     * "scrolled backward indeterminately".  currentY may be 0 to mean "somewhere close to the
     * start of the list" and INT_MAX to mean "we don't know, but not at the start of the list"
     */
    private void onMainContentScrolled(int currentY, int deltaY) {
        if (deltaY > mActionBarAutoHideSensivity) {
            deltaY = mActionBarAutoHideSensivity;
        } else if (deltaY < -mActionBarAutoHideSensivity) {
            deltaY = -mActionBarAutoHideSensivity;
        }

        if (Math.signum(deltaY) * Math.signum(mActionBarAutoHideSignal) < 0) {
            // deltaY is a motion opposite to the accumulated signal, so reset signal
            mActionBarAutoHideSignal = deltaY;
        } else {
            // add to accumulated signal
            mActionBarAutoHideSignal += deltaY;
        }

        boolean shouldShow = currentY < mActionBarAutoHideMinY ||
                (mActionBarAutoHideSignal <= -mActionBarAutoHideSensivity);
        autoShowOrHideActionBar(shouldShow);
    }

    protected void enableActionBarAutoHide(final ListView listView) {
        initActionBarAutoHide();
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            final static int ITEMS_THRESHOLD = 3;
            int lastFvi = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                onMainContentScrolled(firstVisibleItem <= ITEMS_THRESHOLD ? 0 : Integer.MAX_VALUE,
                        lastFvi - firstVisibleItem > 0 ? Integer.MIN_VALUE :
                                lastFvi == firstVisibleItem ? 0 : Integer.MAX_VALUE
                );
                lastFvi = firstVisibleItem;
            }
        });
    }

    public LUtils getLUtils() {
        return mLUtils;
    }

    private boolean isSpecialItem(int itemId) {
        return itemId == NAVDRAWER_ITEM_INVALID;
    }

    protected void onActionBarAutoShowOrHide(boolean shown) {
        if (mStatusBarColorAnimator != null) {
            mStatusBarColorAnimator.cancel();
        }
        mStatusBarColorAnimator = ObjectAnimator.ofInt(
                (mDrawerLayout != null) ? mDrawerLayout : mLUtils,
                (mDrawerLayout != null) ? "statusBarBackgroundColor" : "statusBarColor",
                shown ? Color.BLACK : mNormalStatusBarColor,
                shown ? mNormalStatusBarColor : Color.BLACK)
                .setDuration(200);
        if (mDrawerLayout != null) {
            mStatusBarColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ViewCompat.postInvalidateOnAnimation(mDrawerLayout);
                }
            });
        }
        mStatusBarColorAnimator.setEvaluator(ARGB_EVALUATOR);
        mStatusBarColorAnimator.start();

        for (View view : mHideableHeaderViews) {
            if (shown) {
                view.animate()
                        .translationY(0)
                        .alpha(1)
                        .setDuration(HEADER_HIDE_ANIM_DURATION)
                        .setInterpolator(new DecelerateInterpolator());
            } else {
                view.animate()
                        .translationY(-view.getBottom())
                        .alpha(0)
                        .setDuration(HEADER_HIDE_ANIM_DURATION)
                        .setInterpolator(new DecelerateInterpolator());
            }
        }
    }
}