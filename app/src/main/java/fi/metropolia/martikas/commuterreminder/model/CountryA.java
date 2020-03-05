package fi.metropolia.martikas.commuterreminder.model;

import java.io.IOException;

public enum CountryA {
    FIN;

    public String toValue() {
        switch (this) {
        case FIN: return "FIN";
        }
        return null;
    }

    public static CountryA forValue(String value) throws IOException {
        if (value.equals("FIN")) return FIN;
        throw new IOException("Cannot deserialize CountryA");
    }
}
