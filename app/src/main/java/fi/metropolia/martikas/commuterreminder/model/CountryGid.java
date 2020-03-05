package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum CountryGid {
    WHOSONFIRST_COUNTRY_0;

    public String toValue() {
        switch (this) {
        case WHOSONFIRST_COUNTRY_0: return "whosonfirst:country:0";
        }
        return null;
    }

    public static CountryGid forValue(String value) throws IOException {
        if (value.equals("whosonfirst:country:0")) return WHOSONFIRST_COUNTRY_0;
        throw new IOException("Cannot deserialize CountryGid");
    }
}
