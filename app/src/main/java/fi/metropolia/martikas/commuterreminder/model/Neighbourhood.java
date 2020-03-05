package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum Neighbourhood {
    KAMPPI;

    public String toValue() {
        switch (this) {
        case KAMPPI: return "Kamppi";
        }
        return null;
    }

    public static Neighbourhood forValue(String value) throws IOException {
        if (value.equals("Kamppi")) return KAMPPI;
        throw new IOException("Cannot deserialize Neighbourhood");
    }
}
