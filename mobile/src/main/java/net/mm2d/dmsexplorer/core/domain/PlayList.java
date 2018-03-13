/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.domain;

import android.support.annotation.NonNull;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public interface PlayList extends Iterable<Entry> {
    void setCurrent(int index);

    @NonNull
    Entry getCurrent();

    @NonNull
    Entry get(int index);

    int size();
}
