/*
 * Copyright (c) 2018 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.settings;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.util.ArrayMap;

import net.mm2d.dmsexplorer.R;
import net.mm2d.log.Log;

import java.util.Map;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public enum Orientation {
    UNSPECIFIED(
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED,
            R.string.orientation_unspecified
    ),
    PORTRAIT(
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
            R.string.orientation_portrait
    ),
    LANDSCAPE(
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
            R.string.orientation_landscape
    ),
    REVERSE_PORTRAIT(
            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT,
            R.string.orientation_reverse_portrait
    ),
    REVERSE_LANDSCAPE(
            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE,
            R.string.orientation_reverse_landscape
    ),;
    private static final Map<String, Orientation> sMap = new ArrayMap<>(values().length);

    static {
        for (final Orientation orientation : values()) {
            sMap.put(orientation.name(), orientation);
        }
    }

    private final int mValue;
    @StringRes
    private final int mNameId;

    Orientation(
            final int value,
            @StringRes final int nameId) {
        mValue = value;
        mNameId = nameId;
    }

    @NonNull
    public String getName(@NonNull final Context context) {
        return context.getString(mNameId);
    }

    public void setRequestedOrientation(@NonNull final Activity activity) {
        try {
            activity.setRequestedOrientation(mValue);
        } catch (final Exception e) {
            Log.d(e);
        }
    }

    @NonNull
    public static Orientation of(@NonNull final String name) {
        final Orientation result = sMap.get(name);
        return result != null ? result : UNSPECIFIED;
    }
}
