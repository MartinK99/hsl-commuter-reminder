package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum LocaladminGid {
    WHOSONFIRST_LOCALADMIN_907199715;

    public String toValue() {
        switch (this) {
        case WHOSONFIRST_LOCALADMIN_907199715: return "whosonfirst:localadmin:907199715";
        }
        return null;
    }

    public static LocaladminGid forValue(String value) throws IOException {
        if (value.equals("whosonfirst:localadmin:907199715")) return WHOSONFIRST_LOCALADMIN_907199715;
        throw new IOException("Cannot deserialize LocaladminGid");
    }
}
