package com.kyler.tbmd2.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.kyler.tbmd2.R;

public class TBMDTextView extends TextView {

    public TBMDTextView(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public TBMDTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        isInEditMode();
        init(attrs);

    }

    public TBMDTextView(Context context) {
        super(context);
        isInEditMode();
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.TBMDTextView);
            String fontName = a
                    .getString(R.styleable.TBMDTextView_fontName);
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext()
                        .getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }

}