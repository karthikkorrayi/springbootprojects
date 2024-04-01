package com.investment.fundInvest.Service;

import com.investment.fundInvest.Exception.InvestorAlreadyExist;
import com.investment.fundInvest.Exception.InvestorNotExist;
import com.investment.fundInvest.Model.investorModel;
import com.investment.fundInvest.Model.wishListID;
import com.investment.fundInvest.RabbitMQConfig.investorDto;
import com.investment.fundInvest.Repository.InvestorRepository;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestorService implements InvestorInterface{
    @Autowired
    DirectExchange dr;
    @Autowired
    RabbitTemplate rt;

    @Autowired
    private InvestorRepository irepo;
    @Override
    public investorModel addInvestor(investorModel im) throws InvestorAlreadyExist {


        if(irepo.findById(im.getInvestorId()).isPresent())
        {
            throw new InvestorAlreadyExist();
        }
        investorModel u1=irepo.save(im);
        return u1;

    }

    @Override
    public investorModel getInvestorDetailsById(int investorId) {
        return irepo.findById(investorId).get();
    }


    public String deleteInvestorById(int investorId) throws InvestorNotExist
    {

        if(!irepo.findById(investorId).isPresent())
        {
            throw new InvestorNotExist();
        }

        return "deleted";
    }

    @Override
    public investorModel addInfo(investorModel i, int id) {
        investorModel im=irepo.findById(id).get();
        im.setAmountInvested(i.getAmountInvested());
        List<wishListID> wish=new ArrayList<wishListID>();
        im.setWishlist(wish);
        im.setFundingAmount(i.getFundingAmount());
        return irepo.save(im);
    }

    @Override
    public List<investorModel> getAllInvestorDetails() {
        return irepo.findAll();
    }

    @Override
    public String showInterest(int investorId, int startId, int ideaId){
        investorModel imodel=irepo.findById(investorId).get();
        JSONObject obj=new JSONObject();
        investorDto dto=new investorDto();
       // investorModel invModel=irepo.findById(investorId).get();
        imodel.setFundingAmount(imodel.getFundingAmount()-imodel.getAmountInvested());
        irepo.save(imodel);
        obj.put("fund",imodel.getAmountInvested());
        obj.put("name",imodel.getInvestorName());
        obj.put("email",imodel.getInvestorEmail());
        obj.put("startId",startId);
        obj.put("ideaId",ideaId);
        obj.put("invId",investorId);
        dto.setJsonObject(obj);
        rt.convertAndSend(dr.getName(),"interest_route",dto);
        return "We have conveyed your interest to the startup";
    }

//    @Override
//    public InvestorModel addWishlist( int id,Wishlist_Investor model) {
//
//        InvestorModel im=irepo.findById(id).get();
//        List<Wishlist_Investor> wl=im.getWishlists();
//        wl.add(model);
//        im.setWishlists(wl);
//        irepo.save(im);
//        return im;
//    }
//
//


}
