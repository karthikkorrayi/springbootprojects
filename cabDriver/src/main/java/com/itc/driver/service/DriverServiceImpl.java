package com.itc.driver.service;

import com.itc.driver.exception.DriverNotFoundException;
import com.itc.driver.exception.DuplicateDriverIdException;
import com.itc.driver.model.Driver;
import com.itc.driver.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService{
    @Autowired
    private Repo repo;

    @Override
    public void addDriver(Driver driver) {
        if(repo.existsById(driver.getDriverId())){
            throw new DuplicateDriverIdException("driver already exists");
        }
        repo.save(driver);

    }

    @Override
    public List<Driver> getAllDrivers() {
        return repo.findAll();
    }

    @Override
    public void updateDriver(int driverId, Driver updated) {
        if(!repo.existsById(driverId)){
            throw new DriverNotFoundException("Driver not found");
        }
        updated.setDriverId(driverId);
        repo.save(updated);
    }

    @Override
    public void deleteDriver(int driverId) {
        if(!repo.existsById(driverId)){
            throw new DriverNotFoundException("Driver not found");
        }
        repo.deleteById(driverId);
    }

    @Override
    public List<Driver> getDriversDetailsByCity(String city) {
        return repo.findByCity(city);
    }

    @Override
    public List<Driver> getDriversByMinimumQuoteAmount(double amount) {
        return repo.findByMinimumQuoteAmount(amount);
    }
}
