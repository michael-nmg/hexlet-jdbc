public class CelestialBuilder {
    private String regionID = "";
    private String constellationID = "";
    private String solarSystemID = "";
    private String solarSystemName = "";
    private String x = "";
    private String y = "";
    private String z = "";
    private String xMin = "";
    private String xMax = "";
    private String yMin = "";
    private String yMax = "";
    private String zMin = "";
    private String zMax = "";
    private String luminosity = "";
    private String border = "";
    private String fringe = "";
    private String corridor = "";
    private String hub = "";
    private String international = "";
    private String regional = "";
    private String constellation = "";
    private String security = "";
    private String factionID = "";
    private String radius = "";
    private String sunTypeID = "";
    private String securityClass = "";

    public Celestial build() {
        return new Celestial(regionID, constellationID, solarSystemID,
                solarSystemName, x, y, z, xMin, xMax, yMin, yMax, zMin, zMax,
                luminosity, border, fringe, corridor, hub, international,
                regional, constellation, security, factionID, radius,
                sunTypeID, securityClass);
    }

    public CelestialBuilder setRegionID(String regionID) {
        this.regionID = regionID;
        return this;
    }

    public CelestialBuilder setConstellationID(String constellationID) {
        this.constellationID = constellationID;
        return this;
    }

    public CelestialBuilder setSolarSystemID(String solarSystemID) {
        this.solarSystemID = solarSystemID;
        return this;
    }

    public CelestialBuilder setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
        return this;
    }

    public CelestialBuilder setX(String x) {
        this.x = x;
        return this;
    }

    public CelestialBuilder setY(String y) {
        this.y = y;
        return this;
    }

    public CelestialBuilder setZ(String z) {
        this.z = z;
        return this;
    }

    public CelestialBuilder setxMin(String xMin) {
        this.xMin = xMin;
        return this;
    }

    public CelestialBuilder setxMax(String xMax) {
        this.xMax = xMax;
        return this;
    }

    public CelestialBuilder setyMin(String yMin) {
        this.yMin = yMin;
        return this;
    }

    public CelestialBuilder setyMax(String yMax) {
        this.yMax = yMax;
        return this;
    }

    public CelestialBuilder setzMin(String zMin) {
        this.zMin = zMin;
        return this;
    }

    public CelestialBuilder setzMax(String zMax) {
        this.zMax = zMax;
        return this;
    }

    public CelestialBuilder setLuminosity(String luminosity) {
        this.luminosity = luminosity;
        return this;
    }

    public CelestialBuilder setBorder(String border) {
        this.border = border;
        return this;
    }

    public CelestialBuilder setFringe(String fringe) {
        this.fringe = fringe;
        return this;
    }

    public CelestialBuilder setCorridor(String corridor) {
        this.corridor = corridor;
        return this;
    }

    public CelestialBuilder setHub(String hub) {
        this.hub = hub;
        return this;
    }

    public CelestialBuilder setInternational(String international) {
        this.international = international;
        return this;
    }

    public CelestialBuilder setRegional(String regional) {
        this.regional = regional;
        return this;
    }

    public CelestialBuilder setConstellation(String constellation) {
        this.constellation = constellation;
        return this;
    }

    public CelestialBuilder setSecurity(String security) {
        this.security = security;
        return this;
    }

    public CelestialBuilder setFactionID(String factionID) {
        this.factionID = factionID;
        return this;
    }

    public CelestialBuilder setRadius(String radius) {
        this.radius = radius;
        return this;
    }

    public CelestialBuilder setSunTypeID(String sunTypeID) {
        this.sunTypeID = sunTypeID;
        return this;
    }

    public CelestialBuilder setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
        return this;
    }

    @Override
    public String toString() {
        return "CelestialBuilder{" +
                "regionID='" + regionID + '\'' +
                ", constellationID='" + constellationID + '\'' +
                ", solarSystemID='" + solarSystemID + '\'' +
                ", solarSystemName='" + solarSystemName + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", z='" + z + '\'' +
                ", xMin='" + xMin + '\'' +
                ", xMax='" + xMax + '\'' +
                ", yMin='" + yMin + '\'' +
                ", yMax='" + yMax + '\'' +
                ", zMin='" + zMin + '\'' +
                ", zMax='" + zMax + '\'' +
                ", luminosity='" + luminosity + '\'' +
                ", border='" + border + '\'' +
                ", fringe='" + fringe + '\'' +
                ", corridor='" + corridor + '\'' +
                ", hub='" + hub + '\'' +
                ", international='" + international + '\'' +
                ", regional='" + regional + '\'' +
                ", constellation='" + constellation + '\'' +
                ", security='" + security + '\'' +
                ", factionID='" + factionID + '\'' +
                ", radius='" + radius + '\'' +
                ", sunTypeID='" + sunTypeID + '\'' +
                ", securityClass='" + securityClass + '\'' +
                '}';
    }
}
