package fi.metropolia.martikas.commuterreminder.model;

public class Geocoding {
    private String version;
    private String attribution;
    private Query query;
    private Engine engine;
    private long timestamp;

    public String getVersion() { return version; }
    public void setVersion(String value) { this.version = value; }

    public String getAttribution() { return attribution; }
    public void setAttribution(String value) { this.attribution = value; }

    public Query getQuery() { return query; }
    public void setQuery(Query value) { this.query = value; }

    public Engine getEngine() { return engine; }
    public void setEngine(Engine value) { this.engine = value; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long value) { this.timestamp = value; }
}
