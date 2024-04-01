package com.itc.driver.repository;

import com.itc.driver.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repo extends JpaRepository<Driver, Integer> {
    List<Driver> findByCity(String city);
    List<Driver> findByMinimumQuoteAmount( double amount);
}
