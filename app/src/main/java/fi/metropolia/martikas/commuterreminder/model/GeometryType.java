package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum GeometryType {
    POINT;

    public String toValue() {
        switch (this) {
        case POINT: return "Point";
        }
        return null;
    }

    public static GeometryType forValue(String value) throws IOException {
        if (value.equals("Point")) return POINT;
        throw new IOException("Cannot deserialize GeometryType");
    }
}
