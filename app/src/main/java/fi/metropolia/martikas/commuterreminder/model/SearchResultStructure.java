package fi.metropolia.martikas.commuterreminder.model;

public class SearchResultStructure {
    private Geocoding geocoding;
    private String type;
    private Feature[] features;
    private double[] bbox;

    public Geocoding getGeocoding() { return geocoding; }
    public void setGeocoding(Geocoding value) { this.geocoding = value; }

    public String getType() { return type; }
    public void setType(String value) { this.type = value; }

    public Feature[] getFeatures() { return features; }
    public void setFeatures(Feature[] value) { this.features = value; }

    public double[] getBbox() { return bbox; }
    public void setBbox(double[] value) { this.bbox = value; }
}
