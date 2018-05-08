/*
 * Copyright (c) 2018 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.domain;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

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

    @SuppressLint("CheckResult")
    @Override
    public void initialize() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::initialize);
    }

    @SuppressLint("CheckResult")
    @Override
    public void terminate() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::terminate);
    }

    @SuppressLint("CheckResult")
    @Override
    public void reset() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::reset);
    }

    @SuppressLint("CheckResult")
    @Override
    public void startSearch() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::startSearch);
    }

    @SuppressLint("CheckResult")
    @Override
    public void stopSearch() {
        Observable.fromIterable(mRepositories)
                .subscribe(ServerRepository::stopSearch);
    }

    @NonNull
    @Override
    public Observable<DiscoveryEvent> getDiscoveryEvent() {
        return Observable.fromIterable(mRepositories)
                .flatMap(ServerRepository::getDiscoveryEvent);
    }
}
