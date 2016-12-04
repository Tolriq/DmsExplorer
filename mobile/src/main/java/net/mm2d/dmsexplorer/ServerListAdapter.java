/*
 * Copyright (c) 2016 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.dmsexplorer;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.mm2d.android.cds.MediaServer;
import net.mm2d.android.util.ThemeUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介(OHMAE Ryosuke)</a>
 */
public class ServerListAdapter
        extends RecyclerView.Adapter<ServerListAdapter.ViewHolder> {
    private static final String TAG = "ServerListAdapter";

    public interface OnItemClickListener {
        void onItemClick(View v, View accent, int position, MediaServer server);
    }

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final List<MediaServer> mList;
    private OnItemClickListener mListener;
    private int mSelection = -1;

    public ServerListAdapter(Context context, Collection<? extends MediaServer> servers) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        if (servers == null) {
            mList = new ArrayList<>();
        } else {
            mList = new ArrayList<>(servers);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.li_server, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.applyItem(position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mListener = l;
    }

    public void clear() {
        mList.clear();
    }

    public void addAll(Collection<? extends MediaServer> servers) {
        mList.addAll(servers);
    }

    public int add(MediaServer server) {
        mList.add(server);
        return mList.size() - 1;
    }

    public int remove(MediaServer server) {
        final int position = mList.indexOf(server);
        if (position >= 0) {
            mList.remove(position);
        }
        return position;
    }

    public void setSelection(int position) {
        mSelection = position;
        notifyDataSetChanged();
    }

    private final View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final ViewHolder holder = (ViewHolder) v.getTag();
            final MediaServer server = holder.getItem();
            final int position = holder.getListPosition();
            final View accent = holder.getAccent();
            if (mListener != null) {
                mListener.onItemClick(v, accent, position, server);
            }
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final View mMark;
        private final TextView mAccent;
        private final TextView mText1;
        private final TextView mText2;
        private int mPosition;
        private MediaServer mServer;
        private final GradientDrawable mAccentBackground;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mMark = mView.findViewById(R.id.mark);
            mAccent = (TextView) mView.findViewById(R.id.textAccent);
            mText1 = (TextView) mView.findViewById(R.id.text1);
            mText2 = (TextView) mView.findViewById(R.id.text2);
            mView.setOnClickListener(mItemClickListener);
            mView.setTag(this);
            final int radius = mView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.accent_radius);
            mAccentBackground = new GradientDrawable();
            mAccentBackground.setCornerRadius(radius);
            mAccent.setBackground(mAccentBackground);
        }

        public void applyItem(int position, MediaServer server) {
            mPosition = position;
            if (position == mSelection) {
                mMark.setVisibility(View.VISIBLE);
                mView.setBackgroundResource(R.drawable.bg_list_item_selected);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final int z = mView.getContext().getResources()
                            .getDimensionPixelSize(R.dimen.raise_focus);
                    mView.setTranslationZ(z);
                }
            } else {
                mMark.setVisibility(View.INVISIBLE);
                mView.setBackgroundResource(R.drawable.bg_list_item);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mView.setTranslationZ(0);
                }
            }
            mServer = server;
            final String name = server.getFriendlyName();
            if (!TextUtils.isEmpty(name)) {
                final String c = name.substring(0, 1);
                mAccent.setText(c);
            } else {
                mAccent.setText("");
            }
            mAccentBackground.setColor(ThemeUtils.getAccentColor(name));
            mText1.setText(name);
            final StringBuilder sb = new StringBuilder();
            sb.append("IP: ");
            sb.append(server.getIpAddress());
            final String serial = server.getSerialNumber();
            if (serial != null && !serial.isEmpty()) {
                sb.append("  ");
                sb.append("Serial: ");
                sb.append(serial);
            }
            mText2.setText(sb.toString());
        }

        public View getAccent() {
            return mAccent;
        }

        public MediaServer getItem() {
            return mServer;
        }

        public int getListPosition() {
            return mPosition;
        }
    }
}
