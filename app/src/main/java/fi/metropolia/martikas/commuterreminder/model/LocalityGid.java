package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum LocalityGid {
    WHOSONFIRST_LOCALITY_101748417;

    public String toValue() {
        switch (this) {
        case WHOSONFIRST_LOCALITY_101748417: return "whosonfirst:locality:101748417";
        }
        return null;
    }

    public static LocalityGid forValue(String value) throws IOException {
        if (value.equals("whosonfirst:locality:101748417")) return WHOSONFIRST_LOCALITY_101748417;
        throw new IOException("Cannot deserialize LocalityGid");
    }
}
