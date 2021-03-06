package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum Accuracy {
    CENTROID;

    public String toValue() {
        switch (this) {
        case CENTROID: return "centroid";
        }
        return null;
    }

    public static Accuracy forValue(String value) throws IOException {
        if (value.equals("centroid")) return CENTROID;
        throw new IOException("Cannot deserialize Accuracy");
    }
}
