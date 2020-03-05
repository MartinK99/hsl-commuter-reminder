package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum RegionGid {
    WHOSONFIRST_REGION_85683067;

    public String toValue() {
        switch (this) {
        case WHOSONFIRST_REGION_85683067: return "whosonfirst:region:85683067";
        }
        return null;
    }

    public static RegionGid forValue(String value) throws IOException {
        if (value.equals("whosonfirst:region:85683067")) return WHOSONFIRST_REGION_85683067;
        throw new IOException("Cannot deserialize RegionGid");
    }
}
