package fi.metropolia.martikas.commuterreminder.model;

public class Feature {
    private FeatureType type;
    private Geometry geometry;
    private Properties properties;

    public FeatureType getType() { return type; }
    public void setType(FeatureType value) { this.type = value; }

    public Geometry getGeometry() { return geometry; }
    public void setGeometry(Geometry value) { this.geometry = value; }

    public Properties getProperties() { return properties; }
    public void setProperties(Properties value) { this.properties = value; }
}
