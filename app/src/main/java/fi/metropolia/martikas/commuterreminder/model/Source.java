package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum Source {
    GTFSHSL;

    public String toValue() {
        switch (this) {
        case GTFSHSL: return "gtfshsl";
        }
        return null;
    }

    public static Source forValue(String value) throws IOException {
        if (value.equals("gtfshsl")) return GTFSHSL;
        throw new IOException("Cannot deserialize Source");
    }
}
