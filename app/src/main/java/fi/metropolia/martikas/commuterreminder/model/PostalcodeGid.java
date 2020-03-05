package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum PostalcodeGid {
    WHOSONFIRST_POSTALCODE_421479569;

    public String toValue() {
        switch (this) {
        case WHOSONFIRST_POSTALCODE_421479569: return "whosonfirst:postalcode:421479569";
        }
        return null;
    }

    public static PostalcodeGid forValue(String value) throws IOException {
        if (value.equals("whosonfirst:postalcode:421479569")) return WHOSONFIRST_POSTALCODE_421479569;
        throw new IOException("Cannot deserialize PostalcodeGid");
    }
}
