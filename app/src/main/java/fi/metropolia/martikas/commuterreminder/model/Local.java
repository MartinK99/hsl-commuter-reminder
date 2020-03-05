package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum Local {
    HELSINKI;

    public String toValue() {
        switch (this) {
        case HELSINKI: return "Helsinki";
        }
        return null;
    }

    public static Local forValue(String value) throws IOException {
        if (value.equals("Helsinki")) return HELSINKI;
        throw new IOException("Cannot deserialize Local");
    }
}
