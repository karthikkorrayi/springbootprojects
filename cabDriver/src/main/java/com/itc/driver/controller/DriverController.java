package com.itc.driver.controller;

import com.itc.driver.exception.DriverNotFoundException;
import com.itc.driver.exception.DuplicateDriverIdException;
import com.itc.driver.model.Driver;
import com.itc.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverService serv;


    @PostMapping
    public ResponseEntity<?> addDriver(@RequestBody Driver d){
        try{
            serv.addDriver(d);
            return new ResponseEntity<>("Driver added", HttpStatus.CREATED);
        }
        catch(DuplicateDriverIdException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Driver> getAllDrivers(){
        return serv.getAllDrivers();
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable int driverId, @RequestBody Driver driver){
        try{
            serv.updateDriver(driverId, driver);
            return  new ResponseEntity<>("Driver data Updated",HttpStatus.OK);
        }catch(DriverNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable int driverId){
        try{
            serv.deleteDriver(driverId);
            return new ResponseEntity<>("Driver data deleted",HttpStatus.OK);
        }
        catch (DriverNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{city}")
    public List<Driver> getDriversDetailsByCity(@PathVariable String city){
        return serv.getDriversDetailsByCity(city);
    }


    @GetMapping("/{amount}")
    public List<Driver> getDriversByMinimumQuoteAmount(@PathVariable double amount){
        return serv.getDriversByMinimumQuoteAmount(amount);
    }
}
