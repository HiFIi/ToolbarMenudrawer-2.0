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

package com.kyler.tbmd2;

import android.net.Uri;

import com.google.samples.apps.iosched.util.ParserUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Config {
    // General configuration

    // Is this an internal dogfood build?
    public static final boolean IS_DOGFOOD_BUILD = false;

    // Warning messages for dogfood build
    public static final String DOGFOOD_BUILD_WARNING_TITLE = "Test build";
    public static final String DOGFOOD_BUILD_WARNING_TEXT = "This is a test build.";

    public static final TimeZone CONFERENCE_TIMEZONE = TimeZone.getTimeZone("America/Los_Angeles");
    // shorthand for some units of time
    public static final long SECOND_MILLIS = 1000;
    public static final long MINUTE_MILLIS = 60 * SECOND_MILLIS;
    public static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    public static final long DAY_MILLIS = 24 * HOUR_MILLIS;

    private static String piece(String s, char start, char end) {
        int startIndex = s.indexOf(start), endIndex = s.indexOf(end);
        return s.substring(startIndex + 1, endIndex);
    }

    private static String piece(String s, char start) {
        int startIndex = s.indexOf(start);
        return s.substring(startIndex + 1);
    }

    private static String rep(String s, String orig, String replacement) {
        return s.replaceAll(orig, replacement);
    }

    // Known session tags that induce special behaviors
    public interface Tags {

        // tag categories
        public static final String CATEGORY_THEME = "THEME";
        public static final String CATEGORY_TOPIC = "TOPIC";
        public static final String CATEGORY_TYPE = "TYPE";

        public static final Map<String, Integer> CATEGORY_DISPLAY_ORDERS
                = new HashMap<String, Integer>();
    }

    static {
        Tags.CATEGORY_DISPLAY_ORDERS.put(Tags.CATEGORY_THEME, 0);
        Tags.CATEGORY_DISPLAY_ORDERS.put(Tags.CATEGORY_TOPIC, 1);
        Tags.CATEGORY_DISPLAY_ORDERS.put(Tags.CATEGORY_TYPE, 2);
    }
}
