/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyrentalsystembst;

import java.util.Objects;

/**
 *
 * @author User
 */
public class Property {
    private long adsId;                 
    private String propName;            
    private String completionYear;      
    private double monthlyRent;         
    private String location;            
    private String propertyType;        
    private String rooms;              
    private String parking;            
    private String bathroom;            
    private String size;                
    private String furnished;          
    private String facilities;         
    private String additionalFacilities;
    private String region;              
    private boolean available = true;
    private String tenantName;     
    private String startDate;      

    public Property(long adsId, String propName, String completionYear, double monthlyRent,
                    String location, String propertyType, String rooms, String parking,
                    String bathroom, String size, String furnished,
                    String facilities, String additionalFacilities, String region) {
        this.adsId = adsId;
        this.propName = propName;
        this.completionYear = completionYear;
        this.monthlyRent = monthlyRent;
        this.location = location;
        this.propertyType = propertyType;
        this.rooms = rooms;
        this.parking = parking;
        this.bathroom = bathroom;
        this.size = size;
        this.furnished = furnished;
        this.facilities = facilities;
        this.additionalFacilities = additionalFacilities;
        this.region = region;
    }

    public long getAdsId() {
        return adsId;
    }

    public String getPropName() {
        return propName;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public String getLocation() {
        return location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getRooms() {
        return rooms;
    }

    public String getParking() {
        return parking;
    }

    public String getBathroom() {
        return bathroom;
    }

    public String getSize() {
        return size;
    }

    public String getFurnished() {
        return furnished;
    }

    public String getFacilities() {
        return facilities;
    }

    public String getAdditionalFacilities() {
        return additionalFacilities;
    }

    public String getRegion() {
        return region;
    }

    public void setPropName(String name) {
        this.propName = name;
    }

    public void setCompletionYear(String year) {
        this.completionYear = year;
    }

    public void setMonthlyRent(double rent) {
        this.monthlyRent = rent;
    }

    public void setLocation(String loc) {
        this.location = loc;
    }

    public void setPropertyType(String type) {
        this.propertyType = type;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public void setAdditionalFacilities(String add) {
        this.additionalFacilities = add;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean rentTo(String tenantName, String startDate) {
        if (!available) return false;
        this.available = false;
        this.tenantName = tenantName;
        this.startDate = startDate;
        return true;
    }

    public boolean returnToInventory() {
        if (available) return false;
        this.available = true;
        this.tenantName = null;
        this.startDate = null;
        return true;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getTenantName() {
        return tenantName;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getSqFtOrMinus1() {
        if (size == null) return -1;
        String digits = size.trim().split("\\s")[0].replaceAll("[^0-9]", "");
        if (digits.isEmpty()) return -1;
        try { return Integer.parseInt(digits); } catch (Exception e) { return -1; }
    }

    @Override
    public String toString() {
        return "Property{" +
               "adsId=" + adsId +
               ", name='" + propName + '\'' +
               ", rent=" + monthlyRent +
               ", type='" + propertyType + '\'' +
               ", location='" + location + '\'' +
               ", rooms='" + rooms + '\'' +
               ", size='" + size + '\'' +
               ", furnished='" + furnished + '\'' +
               ", region='" + region + '\'' +
               ", available=" + available +
               (tenantName != null ? ", tenant='" + tenantName + '\'' : "") +
               (startDate != null ? ", startDate='" + startDate + '\'' : "") +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Property)) return false;
        Property other = (Property) o;
        return adsId == other.adsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adsId);
    }
}
