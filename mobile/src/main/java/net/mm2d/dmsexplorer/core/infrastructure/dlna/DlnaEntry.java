package net.mm2d.dmsexplorer.core.infrastructure.dlna;

import android.support.annotation.NonNull;

import net.mm2d.android.upnp.cds.CdsObject;
import net.mm2d.dmsexplorer.core.domain.Entry;
import net.mm2d.dmsexplorer.core.domain.PlayList;
import net.mm2d.dmsexplorer.domain.entity.ContentType;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public class DlnaEntry implements Entry {
    @NonNull
    private final DlnaServer mDlnaServer;
    @NonNull
    private final DlnaEntry mParentEntry;
    @NonNull
    private final CdsObject mCdsObject;
    @Nullable
    private Subject<DlnaEntry> mSubject;
    @Nullable
    private Disposable mDisposable;

    DlnaEntry(
            @NonNull final DlnaServer server,
            @NonNull final DlnaEntry parent,
            @NonNull final CdsObject cdsObject) {
        mDlnaServer = server;
        mParentEntry = parent;
        mCdsObject = cdsObject;
    }

    @Override
    public boolean isContent() {
        return mCdsObject.isItem();
    }

    @Override
    public boolean isContainer() {
        return mCdsObject.isContainer();
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    @Override
    public Single<Integer> delete() {
        return null;
    }

    @NonNull
    @Override
    public ContentType getType() {
        switch (mCdsObject.getType()) {
            case CdsObject.TYPE_VIDEO:
                return ContentType.MOVIE;
            case CdsObject.TYPE_AUDIO:
                return ContentType.MUSIC;
            case CdsObject.TYPE_IMAGE:
                return ContentType.PHOTO;
            case CdsObject.TYPE_CONTAINER:
                return ContentType.CONTAINER;
            case CdsObject.TYPE_UNKNOWN:
                return ContentType.UNKNOWN;
        }
        return ContentType.UNKNOWN;
    }

    @NonNull
    @Override
    public DlnaServer getServer() {
        return mDlnaServer;
    }

    @NonNull
    @Override
    public DlnaEntry getParent() {
        return mParentEntry;
    }

    @NonNull
    @Override
    public String getName() {
        return mCdsObject.getTitle();
    }

    @NonNull
    @Override
    public Observable<DlnaEntry> readEntries(final boolean noCache) {
        if (!noCache && mSubject != null) {
            return mSubject;
        }
        dispose();
        mSubject = ReplaySubject.<DlnaEntry>create().toSerialized();
        mDisposable = mDlnaServer.browse(mCdsObject.getObjectId())
                .observeOn(Schedulers.io())
                .map(this::createChildEntry)
                .subscribe(mSubject::onNext, mSubject::onError, mSubject::onComplete);
        return mSubject.doOnDispose(this::dispose);
    }

    @NonNull
    private DlnaEntry createChildEntry(@NonNull final CdsObject cdsObject) {
        return new DlnaEntry(mDlnaServer, this, cdsObject);
    }

    private void dispose() {
        if (mDisposable == null) {
            return;
        }
        mDisposable.dispose();
        mDisposable = null;
        if (mSubject != null && !mSubject.hasComplete()) {
            mSubject.onComplete();
        }
    }

    @NonNull
    @Override
    public PlayList createPlayList(final ContentType type) {
        final Observable<DlnaEntry> observable = readEntries(false)
                .filter(entry -> entry.getType() == type);
        return new DlnaPlayList(observable);
    }
}
