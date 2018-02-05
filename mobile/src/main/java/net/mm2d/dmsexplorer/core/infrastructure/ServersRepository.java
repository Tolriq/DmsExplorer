/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.infrastructure;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.mm2d.dmsexplorer.core.domain.Server;
import net.mm2d.dmsexplorer.core.domain.ServerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class ServersRepository implements ServerRepository {
    private final Collection<ServerRepository> mRepositories = new ArrayList<>();

    public ServersRepository(ServerRepository... repositories) {
        Collections.addAll(mRepositories, repositories);
    }

    @Override
    public void setOnDiscoverListener(@Nullable final OnDiscoverListener listener) {
        for (final ServerRepository repository : mRepositories) {
            repository.setOnDiscoverListener(listener);
        }
    }

    @Override
    public void setOnLostListener(@Nullable final OnLostListener listener) {
        for (final ServerRepository repository : mRepositories) {
            repository.setOnLostListener(listener);
        }
    }

    @Override
    public void initialize() {
        for (final ServerRepository repository : mRepositories) {
            repository.initialize();
        }
    }

    @Override
    public void terminate() {
        for (final ServerRepository repository : mRepositories) {
            repository.terminate();
        }
    }

    @Override
    public void reset() {
        for (final ServerRepository repository : mRepositories) {
            repository.reset();
        }
    }

    @Override
    public void startSearch() {
        for (final ServerRepository repository : mRepositories) {
            repository.startSearch();
        }
    }

    @Override
    public void stopSearch() {
        for (final ServerRepository repository : mRepositories) {
            repository.stopSearch();
        }
    }

    @NonNull
    @Override
    public List<Server> getServerList() {
        final List<Server> result = new ArrayList<>();
        for (final ServerRepository repository : mRepositories) {
            result.addAll(repository.getServerList());
        }
        return result;
    }
}
