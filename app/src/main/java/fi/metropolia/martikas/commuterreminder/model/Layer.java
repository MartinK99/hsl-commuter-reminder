package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum Layer {
    STOP;

    public String toValue() {
        switch (this) {
        case STOP: return "stop";
        }
        return null;
    }

    public static Layer forValue(String value) throws IOException {
        if (value.equals("stop")) return STOP;
        throw new IOException("Cannot deserialize Layer");
    }
}
