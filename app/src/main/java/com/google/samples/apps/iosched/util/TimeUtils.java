/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.iosched.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;

import com.kyler.tbmd2.Config;
import com.kyler.tbmd2.R;
import com.kyler.tbmd2.utils.UIUtils;

import java.text.DateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

public class TimeUtils {
    /**
     * Boolean preference that when checked, indicates that the user would like to see times
     * in their local timezone throughout the app.
     */
    public static final String PREF_LOCAL_TIMES = "pref_local_times";

    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    public static TimeZone getDisplayTimeZone(Context context) {
        TimeZone defaultTz = TimeZone.getDefault();
        return (isUsingLocalTime(context) && defaultTz != null)
                ? defaultTz : Config.CONFERENCE_TIMEZONE;
    }

    public static boolean isUsingLocalTime(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_LOCAL_TIMES, false);
    }

    public static String formatShortTime(Context context, Date time) {
        DateFormat format = DateFormat.getTimeInstance(DateFormat.SHORT);
        TimeZone tz = getDisplayTimeZone(context);
        if (tz != null) {
            format.setTimeZone(tz);
        }
        return format.format(time);
    }

    public static String formatShortDate(Context context, Date date) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        return DateUtils.formatDateRange(context, formatter, date.getTime(), date.getTime(),
                DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_NO_YEAR,
                getDisplayTimeZone(context).getID()).toString();
    }

    /**
     * Returns "Today", "Tomorrow", "Yesterday", or a short date format.
     */
    public static String formatHumanFriendlyShortDate(final Context context, long timestamp) {
        long localTimestamp, localTime;
        long now = UIUtils.getCurrentTime(context);

        TimeZone tz = getDisplayTimeZone(context);
        localTimestamp = timestamp + tz.getOffset(timestamp);
        localTime = now + tz.getOffset(now);

        long dayOrd = localTimestamp / 86400000L;
        long nowOrd = localTime / 86400000L;

        if (dayOrd == nowOrd) {
            return context.getString(R.string.day_title_today);
        } else if (dayOrd == nowOrd - 1) {
            return context.getString(R.string.day_title_yesterday);
        } else if (dayOrd == nowOrd + 1) {
            return context.getString(R.string.day_title_tomorrow);
        } else {
            return formatShortDate(context, new Date(timestamp));
        }
    }
}
