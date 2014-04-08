package org.aredis.net;

import java.util.Comparator;

import org.aredis.util.SortedArray;
import org.aredis.util.SortedArray.IndexUpdater;

public class ServerInfoComparator implements Comparator<ServerInfo> {

    private static final SortedArray<ServerInfo> serverInfosArray = new SortedArray<ServerInfo>();

    public static final ServerInfoComparator INSTANCE = new ServerInfoComparator();

    private ServerInfoComparator() {
    }

    public static ServerInfo findItem(ServerInfo serverInfo, IndexUpdater indexUpdater) {
        return serverInfosArray.findItem(serverInfo, INSTANCE, indexUpdater);
    }

    @Override
    public int compare(ServerInfo s1, ServerInfo s2) {
        int result = 0;
        if (s1 != null) {
            result = 1;
            if (s2 != null) {
                String host1 = s1.getHost();
                String host2 = s2.getHost();
                result = 0;
                if (host1 != null) {
                    result = 1;
                    if (host2 != null) {
                        result = host1.compareTo(host2);
                    }
                } else if (host2 != null) {
                    result = -1;
                }
                if (result == 0) {
                    result = s1.getPort() - s2.getPort();
                }
            }
        } else if (s2 != null) {
            result = -1;
        }

        return result;
    }

}
