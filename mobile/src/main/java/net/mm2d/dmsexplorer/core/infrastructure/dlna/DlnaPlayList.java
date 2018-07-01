package net.mm2d.dmsexplorer.core.infrastructure.dlna;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import net.mm2d.dmsexplorer.core.domain.Entry;
import net.mm2d.dmsexplorer.core.domain.PlayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author [大前良介 (OHMAE Ryosuke)](mailto:ryo@mm2d.net)
 */
public class DlnaPlayList implements PlayList {
    private int mCursor;
    private final List<Entry> mEntries = Collections.synchronizedList(new ArrayList<>());

    @SuppressLint("CheckResult")
    DlnaPlayList(@NonNull final Observable<DlnaEntry> observable) {
        observable.subscribe(mEntries::add);
    }

    @Override
    public void setCurrent(final int index) {
        mCursor = index;
    }

    @NonNull
    @Override
    public Entry getCurrent() {
        return mEntries.get(mCursor);
    }

    @NonNull
    @Override
    public Entry get(final int index) {
        return mEntries.get(index);
    }

    @Override
    public int size() {
        return mEntries.size();
    }

    @NonNull
    @Override
    public Iterator<Entry> iterator() {
        return mEntries.iterator();
    }
}
