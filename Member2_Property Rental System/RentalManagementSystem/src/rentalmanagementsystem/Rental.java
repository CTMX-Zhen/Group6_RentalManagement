/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmanagementsystem;

/**
 *
 * @author User
 */
public class Rental {

    private long id;
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

    public Rental(long id, String propName, String completionYear, double monthlyRent,
            String location, String propertyType, String rooms, String parking,
            String bathroom, String size, String furnished) {
        this.id = id;
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
    }

    public long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Property [ID=" + id + ", Name=" + propName + ", Rent=" + monthlyRent
                + ", Location=" + location + ", Type=" + propertyType
                + ", Rooms=" + rooms + ", Parking=" + parking + ", Bathroom=" + bathroom
                + ", Size=" + size + ", Furnished=" + furnished + "]";
    }
}
