/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.domain;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;


/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public interface ServerRepository {
    interface OnDiscoverListener {
        void onDiscover(@NonNull Server server);
    }

    interface OnLostListener {
        void onLost(@NonNull Server server);
    }

    void setOnDiscoverListener(@Nullable OnDiscoverListener listener);

    void setOnLostListener(@Nullable OnLostListener listener);

    void initialize();

    void terminate();

    void reset();

    void startSearch();

    void stopSearch();

    @NonNull
    List<Server> getServerList();
}
