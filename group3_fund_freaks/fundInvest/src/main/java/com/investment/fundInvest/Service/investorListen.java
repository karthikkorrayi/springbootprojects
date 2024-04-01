package com.investment.fundInvest.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.investment.fundInvest.Model.*;
import com.investment.fundInvest.RabbitMQConfig.investorDto;
import com.investment.fundInvest.Repository.InvestorRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class investorListen {
    @Autowired
    ObjectMapper objmap=new ObjectMapper();
    @Autowired
    InvestorRepository repo;
    @RabbitListener(queues = "investor_auth_queue")
    public void saveLog(investorDto dto) throws JsonProcessingException {
        Object nmsg1 = dto.getJsonObject().get("ilog");
        String jsonString =objmap.writeValueAsString(nmsg1);
        try{
            User usr=objmap.readValue(jsonString,User.class);
            System.out.println("User registered successfully");
            investorModel inv=new investorModel();
            inv.setInvestorId(usr.getId());
            inv.setInvestorEmail(usr.getEmail());
            inv.setInvestorName(usr.getUname());
            repo.save(inv);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
    @RabbitListener(queues = "idea_queue")
    public void wishlistInvest(investorDto dto) throws JsonProcessingException {
        Object nmsg1 = dto.getJsonObject().get("idea");
        String nmsg2 = dto.getJsonObject().get("investor").toString();
        int id = Integer.parseInt(nmsg2);
        System.out.println("Idea shipped successfully");
        String jsonString = objmap.writeValueAsString(nmsg1);
        try {
            ideaModel idea = objmap.readValue(jsonString, ideaModel.class);
            if (repo.findById(id).isPresent()) {
                investorModel imodel = repo.findById(id).get();
                wishListID wish = new wishListID();
                wish.setAmountInvested(idea.getPendingAmount());
                wish.setIdeaId(idea.getIdeaId());
                wish.setIdeaType(idea.getIdeaType());
                wish.setStartupName(idea.getStartupName());
                wish.setIdeaDesc(idea.getIdeaDesc());
                List<wishListID> wishlist = imodel.getWishlist();
                wishlist.add(wish);
                imodel.setWishlist(wishlist);
                repo.save(imodel);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
//    @RabbitListener(queues = "startup_queue")
//    public void getStartup(investorDto dto){
//        String temp=dto.getJsonObject().toJSONString();
//        System.out.println(temp);
//        System.out.println("Startups shipped successfully");
//    }

}
