package com.FundFreaks.FundStartup.repository;

import com.FundFreaks.FundStartup.model.startupModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface startupRepo extends MongoRepository<startupModel,Integer> {
    @Query("{'ideas.ideaType':?0}")
    public List<startupModel> findByCategory(String ideaType);
    @Query("{'startCity':?0}")
    public List<startupModel> findByCity(String city);
    @Query("{'startName':?0}")
    public startupModel findByName(String name);
}
