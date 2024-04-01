package com.investment.fundInvest.Service;

import com.investment.fundInvest.Exception.InvestorAlreadyExist;
import com.investment.fundInvest.Model.investorModel;

import java.util.List;

public interface InvestorInterface {
    public investorModel addInvestor(investorModel im) throws InvestorAlreadyExist;
    public investorModel getInvestorDetailsById(int investorId);
//    public String deleteInvestorById(int investorId);
    investorModel addInfo(investorModel i, int id);
    List<investorModel> getAllInvestorDetails();

    public String showInterest(int investorId, int startId, int ideaId);
}
