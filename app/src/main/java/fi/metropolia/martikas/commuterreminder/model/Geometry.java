package fi.metropolia.martikas.commuterreminder.model;

public class Geometry {
    private GeometryType type;
    private double[] coordinates;

    public GeometryType getType() { return type; }
    public void setType(GeometryType value) { this.type = value; }

    public double[] getCoordinates() { return coordinates; }
    public void setCoordinates(double[] value) { this.coordinates = value; }
}
