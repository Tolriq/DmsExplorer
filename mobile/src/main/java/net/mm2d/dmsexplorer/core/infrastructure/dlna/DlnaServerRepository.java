/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.infrastructure.dlna;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.mm2d.dmsexplorer.core.domain.Server;
import net.mm2d.dmsexplorer.core.domain.ServerRepository;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class DlnaServerRepository implements ServerRepository {
    @Override
    public void setOnDiscoverListener(@Nullable final OnDiscoverListener listener) {
    }

    @Override
    public void setOnLostListener(@Nullable final OnLostListener listener) {
    }

    @Override
    public void initialize() {
    }

    @Override
    public void terminate() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void startSearch() {
    }

    @Override
    public void stopSearch() {
    }

    @NonNull
    @Override
    public List<Server> getServerList() {
        return Collections.emptyList();
    }
}
