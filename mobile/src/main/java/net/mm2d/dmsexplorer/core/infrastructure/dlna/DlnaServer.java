package net.mm2d.dmsexplorer.core.infrastructure.dlna;

import android.support.annotation.NonNull;

import net.mm2d.android.upnp.cds.CdsObject;
import net.mm2d.android.upnp.cds.MediaServer;
import net.mm2d.dmsexplorer.core.domain.Entry;
import net.mm2d.dmsexplorer.core.domain.Server;

import io.reactivex.Observable;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public class DlnaServer implements Server {
    private final MediaServer mMediaServer;

    public DlnaServer(@NonNull final MediaServer server) {
        mMediaServer = server;
    }

    @NonNull
    Observable<CdsObject> browse(@NonNull final String objectId) {
        return mMediaServer.browse(objectId);
    }

    @NonNull
    @Override
    public String getName() {
        return mMediaServer.getFriendlyName();
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @NonNull
    @Override
    public Entry getRoot() {
        return null;
    }

    @Override
    public void setActive(final boolean active) {
    }
}
