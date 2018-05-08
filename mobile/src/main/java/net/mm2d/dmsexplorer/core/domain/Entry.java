/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.domain;

import android.support.annotation.NonNull;

import net.mm2d.dmsexplorer.domain.entity.ContentType;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public interface Entry {
    boolean isContent();

    boolean isDirectory();

    boolean isDeletable();

    Single<Integer> delete();

    @NonNull
    ContentType getType();

    @NonNull
    Server getServer();

    @NonNull
    Entry getParent();

    @NonNull
    String getName();

    @NonNull
    String getPath();

    @NonNull
    Observable<Entry> readEntries(boolean noCache);

    @NonNull
    PlayList createPlayList(ContentType type);
}
