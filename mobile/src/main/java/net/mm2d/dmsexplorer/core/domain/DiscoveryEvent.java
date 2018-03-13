package net.mm2d.dmsexplorer.core.domain;

import android.support.annotation.NonNull;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class DiscoveryEvent {
    public enum Type {
        DISCOVER,
        LOST
    }

    @NonNull
    public static DiscoveryEvent discover(@NonNull final Server server) {
        return new DiscoveryEvent(Type.DISCOVER, server);
    }

    @NonNull
    public static DiscoveryEvent lost(@NonNull final Server server) {
        return new DiscoveryEvent(Type.LOST, server);
    }

    @NonNull
    private final Type mType;
    @NonNull
    private final Server mServer;

    private DiscoveryEvent(@NonNull final Type type, @NonNull final Server server) {
        mType = type;
        mServer = server;
    }

    @NonNull
    public Type getType() {
        return mType;
    }

    @NonNull
    public Server getServer() {
        return mServer;
    }
}
