package com.itc.driver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Driver {
    @Id
    private int driverId;
    private String firstName;
    private String telephoneNumber;
    private String address;
    private String city;
    private String engineSize;
    private double quoteAmount;

    public Driver(int driverId, String firstName, String telephoneNumber, String address, String city, String engineSize, double quoteAmount) {
        this.driverId = driverId;
        this.firstName = firstName;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.city = city;
        this.engineSize = engineSize;
        this.quoteAmount = quoteAmount;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(String engineSize) {
        this.engineSize = engineSize;
    }

    public double getQuoteAmount() {
        return quoteAmount;
    }

    public void setQuoteAmount(double quoteAmount) {
        this.quoteAmount = quoteAmount;
    }
}
