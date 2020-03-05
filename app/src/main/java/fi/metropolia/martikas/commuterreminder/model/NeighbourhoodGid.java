package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum NeighbourhoodGid {
    WHOSONFIRST_NEIGHBOURHOOD_85898845;

    public String toValue() {
        switch (this) {
        case WHOSONFIRST_NEIGHBOURHOOD_85898845: return "whosonfirst:neighbourhood:85898845";
        }
        return null;
    }

    public static NeighbourhoodGid forValue(String value) throws IOException {
        if (value.equals("whosonfirst:neighbourhood:85898845")) return WHOSONFIRST_NEIGHBOURHOOD_85898845;
        throw new IOException("Cannot deserialize NeighbourhoodGid");
    }
}
