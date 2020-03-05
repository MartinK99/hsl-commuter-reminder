package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum Country {
    SUOMI;

    public String toValue() {
        switch (this) {
        case SUOMI: return "Suomi";
        }
        return null;
    }

    public static Country forValue(String value) throws IOException {
        if (value.equals("Suomi")) return SUOMI;
        throw new IOException("Cannot deserialize Country");
    }
}
