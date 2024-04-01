package com.investment.fundInvest.Repository;

import com.investment.fundInvest.Model.investorModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends MongoRepository<investorModel, Integer> {

}
