package com.FundFreaks.FundStartup.service;

import com.FundFreaks.FundStartup.RabbitMQConfig.StartupDTO;
import com.FundFreaks.FundStartup.model.User;
import com.FundFreaks.FundStartup.model.ideaModel;
import com.FundFreaks.FundStartup.model.startupModel;
import com.FundFreaks.FundStartup.model.whoInvested;
import com.FundFreaks.FundStartup.repository.interestRepo;
import com.FundFreaks.FundStartup.repository.startupRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class startupListen {
    @Autowired
    interestRepo repoo;
    @Autowired
    ObjectMapper objmap;
    @Autowired
    startupRepo repo;
    @RabbitListener(queues = "startup_auth_queue")
    public void saveLog(StartupDTO dto) throws JsonProcessingException {
        Object nmsg1 = dto.getJsonObject().get("slog");
        String jsonString =objmap.writeValueAsString(nmsg1);
        try{
            User usr=objmap.readValue(jsonString, User.class);
            System.out.println("User registered successfully");
            startupModel str=new startupModel();
            str.setId(usr.getId());
            str.setStartEmail(usr.getEmail());
            str.setStartName(usr.getUname());
            List<ideaModel> ideas=new ArrayList<ideaModel>();
            str.setIdeas(ideas);
            repo.save(str);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
    @RabbitListener(queues = "interest_queue")
    public void saveInterest(StartupDTO dto){
        String name = dto.getJsonObject().get("name").toString();
        String email= dto.getJsonObject().get("email").toString();
        String fund = dto.getJsonObject().get("fund").toString();
        String sid = dto.getJsonObject().get("startId").toString();
        String iid = dto.getJsonObject().get("ideaId").toString();
        String invId=dto.getJsonObject().get("invId").toString();
        whoInvested inv=new whoInvested();
        double funds=Double.parseDouble(fund);
        int startId=Integer.parseInt(sid);
        int ideId=Integer.parseInt(iid);
        int invv=Integer.parseInt(invId);
        inv.setFund(funds);
        inv.setEmail(email);
        inv.setName(name);
        inv.setStartupId(startId);
        inv.setIdeaId(ideId);
        inv.setInvestorId(invv);
        repoo.save(inv);
        System.out.println("Investor showed interest");
    }

}
