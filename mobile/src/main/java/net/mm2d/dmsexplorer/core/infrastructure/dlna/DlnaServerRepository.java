/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer.core.infrastructure.dlna;

import android.support.annotation.NonNull;

import net.mm2d.android.upnp.cds.MediaServer;
import net.mm2d.android.upnp.cds.MsControlPoint;
import net.mm2d.android.upnp.cds.MsControlPoint.MsDiscoveryListener;
import net.mm2d.dmsexplorer.core.domain.DiscoveryEvent;
import net.mm2d.dmsexplorer.core.domain.ServerRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class DlnaServerRepository implements ServerRepository {
    private Subject<DiscoveryEvent> mDiscoveryEventSubject = PublishSubject.create();
    private final MsControlPoint mMsControlPoint;
    private final List<DlnaServer> mDlnaServers = new ArrayList<>();

    public DlnaServerRepository(@NonNull final MsControlPoint cp) {
        mMsControlPoint = cp;
        cp.setMsDiscoveryListener(new MsDiscoveryListener() {
            @Override
            public void onDiscover(@NonNull final MediaServer server) {
                discover(new DlnaServer(server));
            }

            @Override
            public void onLost(@NonNull final MediaServer server) {
                lost(new DlnaServer(server));
            }
        });
    }

    private void discover(@NonNull final DlnaServer server) {
        mDlnaServers.add(server);
        mDiscoveryEventSubject.onNext(DiscoveryEvent.discover(server));
    }

    private void lost(@NonNull final DlnaServer server) {
        mDlnaServers.remove(server);
        mDiscoveryEventSubject.onNext(DiscoveryEvent.lost(server));
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
    public Observable<DiscoveryEvent> getDiscoveryEvent() {
        return mDiscoveryEventSubject;
    }
}
