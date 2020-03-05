package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum FeatureType {
    FEATURE;

    public String toValue() {
        switch (this) {
        case FEATURE: return "Feature";
        }
        return null;
    }

    public static FeatureType forValue(String value) throws IOException {
        if (value.equals("Feature")) return FEATURE;
        throw new IOException("Cannot deserialize FeatureType");
    }
}
