/**
 * regionID,constellationID,solarSystemID,solarSystemName,x,y,z,xMin,xMax,yMin,yMax,zMin,zMax,luminosity,border,fringe,corridor,hub,international,regional,constellation,security,factionID,radius,sunTypeID,securityClass
 * 10000001,20000001,30000001,Tanoo,-88510792599980608.0000000000,42369443966878896.0000000000,-44513525346479696.0000000000,-88511903148490592.0000000000,-88509256471760608.0000000000,42369297927489104.0000000000,42369645749263904.0000000000,44513036355892800.0000000000,44514111689782896.0000000000,0.0157500000,1,0,0,1,1,1,None,0.8583240688,500007,1323338364984.0000000000,45041,B
 */

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Celestial {
    private String regionID;
    private String constellationID;
    private String solarSystemID;
    private String solarSystemName;
    private String x;
    private String y;
    private String z;
    private String xMin;
    private String xMax;
    private String yMin;
    private String yMax;
    private String zMin;
    private String zMax;
    private String luminosity;
    private String border;
    private String fringe;
    private String corridor;
    private String hub;
    private String international;
    private String regional;
    private String constellation;
    private String security;
    private String factionID;
    private String radius;
    private String sunTypeID;
    private String securityClass;

    public Celestial(String regionID, String constellationID, String solarSystemID, String solarSystemName, String x, String y, String z, String xMin, String xMax, String yMin, String yMax, String zMin, String zMax, String luminosity, String border, String fringe, String corridor, String hub, String international, String regional, String constellation, String security, String factionID, String radius, String sunTypeID, String securityClass) {
        this.regionID = regionID;
        this.constellationID = constellationID;
        this.solarSystemID = solarSystemID;
        this.solarSystemName = solarSystemName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        this.zMin = zMin;
        this.zMax = zMax;
        this.luminosity = luminosity;
        this.border = border;
        this.fringe = fringe;
        this.corridor = corridor;
        this.hub = hub;
        this.international = international;
        this.regional = regional;
        this.constellation = constellation;
        this.security = security;
        this.factionID = factionID;
        this.radius = radius;
        this.sunTypeID = sunTypeID;
        this.securityClass = securityClass;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getConstellationID() {
        return constellationID;
    }

    public void setConstellationID(String constellationID) {
        this.constellationID = constellationID;
    }

    public String getSolarSystemID() {
        return solarSystemID;
    }

    public void setSolarSystemID(String solarSystemID) {
        this.solarSystemID = solarSystemID;
    }

    public String getSolarSystemName() {
        return solarSystemName;
    }

    public void setSolarSystemName(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getxMin() {
        return xMin;
    }

    public void setxMin(String xMin) {
        this.xMin = xMin;
    }

    public String getxMax() {
        return xMax;
    }

    public void setxMax(String xMax) {
        this.xMax = xMax;
    }

    public String getyMin() {
        return yMin;
    }

    public void setyMin(String yMin) {
        this.yMin = yMin;
    }

    public String getyMax() {
        return yMax;
    }

    public void setyMax(String yMax) {
        this.yMax = yMax;
    }

    public String getzMin() {
        return zMin;
    }

    public void setzMin(String zMin) {
        this.zMin = zMin;
    }

    public String getzMax() {
        return zMax;
    }

    public void setzMax(String zMax) {
        this.zMax = zMax;
    }

    public String getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(String luminosity) {
        this.luminosity = luminosity;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getFringe() {
        return fringe;
    }

    public void setFringe(String fringe) {
        this.fringe = fringe;
    }

    public String getCorridor() {
        return corridor;
    }

    public void setCorridor(String corridor) {
        this.corridor = corridor;
    }

    public String getHub() {
        return hub;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getFactionID() {
        return factionID;
    }

    public void setFactionID(String factionID) {
        this.factionID = factionID;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getSunTypeID() {
        return sunTypeID;
    }

    public void setSunTypeID(String sunTypeID) {
        this.sunTypeID = sunTypeID;
    }

    public String getSecurityClass() {
        return securityClass;
    }

    public void setSecurityClass(String securityClass) {
        this.securityClass = securityClass;
    }

    public String getAllValues() {
        return "'" + regionID + "'"
                + "," + "'" + constellationID + "'"
                + "," + "'" + solarSystemID + "'"
                + "," + "'" + solarSystemName + "'"
                + "," + "'" + x + "'"
                + "," + "'" + y + "'"
                + "," + "'" + z + "'"
                + "," + "'" + xMin + "'"
                + "," + "'" + xMax + "'"
                + "," + "'" + yMin + "'"
                + "," + "'" + yMax + "'"
                + "," + "'" + zMin + "'"
                + "," + "'" + zMax + "'"
                + "," + "'" + luminosity + "'"
                + "," + "'" + border + "'"
                + "," + "'" + fringe + "'"
                + "," + "'" + corridor + "'"
                + "," + "'" + hub + "'"
                + "," + "'" + international + "'"
                + "," + "'" + regional + "'"
                + "," + "'" + constellation + "'"
                + "," + "'" + security + "'"
                + "," + "'" + factionID + "'"
                + "," + "'" + radius + "'"
                + "," + "'" + sunTypeID + "'"
                + "," + "'" + securityClass + "'";
    }
}
