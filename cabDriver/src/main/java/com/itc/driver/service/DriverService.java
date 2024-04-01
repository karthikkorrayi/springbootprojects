package com.itc.driver.service;

import com.itc.driver.model.Driver;

import java.util.List;

public interface DriverService {
    void addDriver(Driver driver);
    List<Driver> getAllDrivers();
    void updateDriver(int driverId, Driver updated);
    void deleteDriver(int driverId);
    List<Driver> getDriversDetailsByCity(String city);
    List<Driver> getDriversByMinimumQuoteAmount(double amount);

}
