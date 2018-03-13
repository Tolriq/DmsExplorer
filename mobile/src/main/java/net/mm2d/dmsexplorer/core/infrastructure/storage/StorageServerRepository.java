/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.infrastructure.storage;

import android.support.annotation.NonNull;

import net.mm2d.dmsexplorer.core.domain.DiscoveryEvent;
import net.mm2d.dmsexplorer.core.domain.ServerRepository;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class StorageServerRepository implements ServerRepository {
    private Subject<DiscoveryEvent> mDiscoveryEventSubject = PublishSubject.create();

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
    public Observable<DiscoveryEvent> getDiscoveryEvent() {
        return mDiscoveryEventSubject;
    }
}
