package net.mm2d.dmsexplorer.core.infrastructure.dlna;

import android.support.annotation.NonNull;

import net.mm2d.android.upnp.cds.CdsObject;
import net.mm2d.dmsexplorer.core.domain.Entry;
import net.mm2d.dmsexplorer.core.domain.PlayList;
import net.mm2d.dmsexplorer.core.domain.Server;
import net.mm2d.dmsexplorer.domain.entity.ContentType;

import io.reactivex.Observable;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public class DlnaEntry implements Entry {
    private final DlnaServer mDlnaServer;
    private final CdsObject mCdsObject;

    public DlnaEntry(@NonNull final DlnaServer server, @NonNull final CdsObject object) {
        mDlnaServer = server;
        mCdsObject = object;
    }

    @Override
    public boolean isContent() {
        return mCdsObject.isItem();
    }

    @Override
    public boolean isDirectory() {
        return mCdsObject.isContainer();
    }

    @NonNull
    @Override
    public ContentType getType() {
        return null;
    }

    @NonNull
    @Override
    public Server getServer() {
        return mDlnaServer;
    }

    @NonNull
    @Override
    public Entry getParent() {
        return null;
    }

    @NonNull
    @Override
    public String getName() {
        return null;
    }

    @NonNull
    @Override
    public String getPath() {
        return null;
    }

    @NonNull
    @Override
    public Observable<Entry> readEntries(final boolean noCache) {
        return null;
    }

    @NonNull
    @Override
    public PlayList createPlayList(final ContentType type) {
        return null;
    }
}
