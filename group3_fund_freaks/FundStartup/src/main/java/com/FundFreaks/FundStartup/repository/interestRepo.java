package com.FundFreaks.FundStartup.repository;

import com.FundFreaks.FundStartup.model.whoInvested;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface interestRepo extends MongoRepository<whoInvested,Integer> {
    @Query("{'startupId':?0}")
    public List<whoInvested> findByStartupId(int startupId);
    @Query("{'ideaId':?0}")
    public whoInvested findByIdeaId(int ideaId);
    @Query("{'investorId':?0}")
    public whoInvested findByInvestorId(int invId);
}
