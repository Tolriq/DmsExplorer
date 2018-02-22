/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.infrastructure;

import android.support.annotation.NonNull;

import net.mm2d.dmsexplorer.core.domain.DiscoveryEvent;
import net.mm2d.dmsexplorer.core.domain.ServerRepository;

import java.util.Arrays;
import java.util.Collection;

import io.reactivex.Observable;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class ServersRepository implements ServerRepository {
    @NonNull
    private final Collection<ServerRepository> mRepositories;

    public ServersRepository(ServerRepository... repositories) {
        mRepositories = Arrays.asList(repositories);
    }

    @Override
    public void initialize() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::initialize);
    }

    @Override
    public void terminate() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::terminate);
    }

    @Override
    public void reset() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::reset);
    }

    @Override
    public void startSearch() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::startSearch);
    }

    @Override
    public void stopSearch() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::stopSearch);
    }

    @NonNull
    @Override
    public Observable<DiscoveryEvent> discovery() {
        return Observable.fromIterable(mRepositories)
                .flatMap(ServerRepository::discovery);
    }
}
