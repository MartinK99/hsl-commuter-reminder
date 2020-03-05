package fi.metropolia.martikas.commuterreminder.model;

public class Properties {
    private String id;
    private String gid;
    private Layer layer;
    private Source source;
    private String sourceID;
    private String name;
    private String postalcode;
    private PostalcodeGid postalcodeGid;
    private double confidence;
    private Accuracy accuracy;
    private Country country;
    private CountryGid countryGid;
    private CountryA countryA;
    private Region region;
    private RegionGid regionGid;
    private Local localadmin;
    private LocaladminGid localadminGid;
    private Local locality;
    private LocalityGid localityGid;
    private Neighbourhood neighbourhood;
    private NeighbourhoodGid neighbourhoodGid;
    private String label;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getGid() { return gid; }
    public void setGid(String value) { this.gid = value; }

    public Layer getLayer() { return layer; }
    public void setLayer(Layer value) { this.layer = value; }

    public Source getSource() { return source; }
    public void setSource(Source value) { this.source = value; }

    public String getSourceID() { return sourceID; }
    public void setSourceID(String value) { this.sourceID = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getPostalcode() { return postalcode; }
    public void setPostalcode(String value) { this.postalcode = value; }

    public PostalcodeGid getPostalcodeGid() { return postalcodeGid; }
    public void setPostalcodeGid(PostalcodeGid value) { this.postalcodeGid = value; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double value) { this.confidence = value; }

    public Accuracy getAccuracy() { return accuracy; }
    public void setAccuracy(Accuracy value) { this.accuracy = value; }

    public Country getCountry() { return country; }
    public void setCountry(Country value) { this.country = value; }

    public CountryGid getCountryGid() { return countryGid; }
    public void setCountryGid(CountryGid value) { this.countryGid = value; }

    public CountryA getCountryA() { return countryA; }
    public void setCountryA(CountryA value) { this.countryA = value; }

    public Region getRegion() { return region; }
    public void setRegion(Region value) { this.region = value; }

    public RegionGid getRegionGid() { return regionGid; }
    public void setRegionGid(RegionGid value) { this.regionGid = value; }

    public Local getLocaladmin() { return localadmin; }
    public void setLocaladmin(Local value) { this.localadmin = value; }

    public LocaladminGid getLocaladminGid() { return localadminGid; }
    public void setLocaladminGid(LocaladminGid value) { this.localadminGid = value; }

    public Local getLocality() { return locality; }
    public void setLocality(Local value) { this.locality = value; }

    public LocalityGid getLocalityGid() { return localityGid; }
    public void setLocalityGid(LocalityGid value) { this.localityGid = value; }

    public Neighbourhood getNeighbourhood() { return neighbourhood; }
    public void setNeighbourhood(Neighbourhood value) { this.neighbourhood = value; }

    public NeighbourhoodGid getNeighbourhoodGid() { return neighbourhoodGid; }
    public void setNeighbourhoodGid(NeighbourhoodGid value) { this.neighbourhoodGid = value; }

    public String getLabel() { return label; }
    public void setLabel(String value) { this.label = value; }
}
