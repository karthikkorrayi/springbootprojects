package com.FundFreaks.FundStartup.service;

import com.FundFreaks.FundStartup.RabbitMQConfig.StartupDTO;
import com.FundFreaks.FundStartup.exception.ideaAlreadyExist;
import com.FundFreaks.FundStartup.exception.startupAlreadyExist;
import com.FundFreaks.FundStartup.exception.startupNotFound;
import com.FundFreaks.FundStartup.model.ideaModel;
import com.FundFreaks.FundStartup.model.startupModel;
import com.FundFreaks.FundStartup.model.whoInvested;
import com.FundFreaks.FundStartup.repository.interestRepo;
import com.FundFreaks.FundStartup.repository.startupRepo;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class startupServiceimp implements startupService {
    @Autowired
    private startupRepo repo;
    @Autowired
    private RabbitTemplate rt;
    @Autowired
    private DirectExchange exchange;
    @Autowired
    private interestRepo repos;
    @Override
    public startupModel saveStartup(startupModel model) throws startupAlreadyExist {
        if (!repo.findById(model.getId()).isEmpty()) {
            throw new startupAlreadyExist();
        }
        return repo.save(model);
    }

    @Override
    public startupModel getById(int id) throws startupNotFound {
        if (!repo.findById(id).isPresent()) {
            throw new startupNotFound();
        }
        return repo.findById(id).get();
    }

    @Override
    public List<ideaModel> findIdeaByCategory(String category) {
        List<startupModel> startup=repo.findByCategory(category);
        List<ideaModel> idee=new ArrayList<ideaModel>();
        for(startupModel str:startup)
        {
            List<ideaModel> ide=str.getIdeas();
            for(ideaModel id:ide)
            {
                if(id.getIdeaType().equals(category))
                {
                    idee.add(id);
                }
            }
        }
        return idee;
    }

    @Override
    public startupModel addIdea(ideaModel idee, int id) throws ideaAlreadyExist {
        Optional<startupModel> model = repo.findById(id);
        List<ideaModel> ideas=model.get().getIdeas();
        for(ideaModel im:ideas)
        {
            if(im.getIdeaId()==idee.getIdeaId())
            {
                throw new ideaAlreadyExist();
            }
        }
        startupModel str = model.get();
        List<ideaModel> ide = str.getIdeas();
        ide.add(idee);
        str.setIdeas(ide);
        repo.save(str);
        return str;
    }

    @Override
    public startupModel deleteIdea(int id, int idd) throws startupNotFound {
        if (!repo.findById((id)).isPresent()) {
            throw new startupNotFound();
        }
        Optional<startupModel> model = repo.findById(id);
        startupModel str = model.get();
        List<ideaModel> idea = str.getIdeas();
        int i = 0;
        for (ideaModel ide : idea) {
            if (ide.getIdeaId() == idd) {
                idea.remove(i);
                break;
            }
            i++;
        }
        str.setIdeas(idea);
        repo.save(str);
        return str;
    }

    @Override
    public String fetchIdeaSe(int sid, int id,int iid) {
        startupModel model = repo.findById(sid).get();
        startupModel temp = model;
        List<whoInvested> who=repos.findByStartupId(sid);
        List<ideaModel> ide = temp.getIdeas();
        for (ideaModel ids : ide) {

          if (ids.getIdeaId() == id) {
                if(ids.isInvested()==false)
                {
                    double amount=ids.getPendingAmount();
                    for(whoInvested w:who)
                    {
                        double fund = 0.0;
                        if(w.getInvestorId()==iid)
                        {
                            fund=w.getFund();
                            if(fund>amount)
                            {
                                w.setFund(fund-amount);
                                amount=0.0;
                            }
                            else if(amount>=fund)
                            {
                                w.setFund(0.0);
                                amount=amount-fund;
                            }
                            w.setAccepted(true);
                            repos.save(w);
                        }
                    }
                    ids.setPendingAmount(amount);
                    if(amount==0.0) {
                        ids.setInvested(true);
                    }
                    updateInvested(model.getId(),ids.getIdeaId(),ids);
                    JSONObject obj = new JSONObject();
                    obj.put("idea", ids);
                    obj.put("investor",iid);
                    StartupDTO str = new StartupDTO();
                    str.setJsonObject(obj);
                    rt.convertAndSend(exchange.getName(), "idea_route", str);
                }
                else if(ids.isInvested()==true)
                {
                    return "This idea has collected the required amount";
                }
                return "Successfully chosen investor!!";
           }
        }
        return "Idea Not Found";
    }

    @Override
    public List<ideaModel> getIdee(int id) {
        startupModel str=repo.findById(id).get();
        List<ideaModel> idd = str.getIdeas();
        return idd;
    }

    @Override
    public List<startupModel> findCity(String city) {
        return repo.findByCity(city);
    }

    @Override
    public List<ideaModel> getName(String name) {
        startupModel model=repo.findByName(name);
        List<ideaModel> imodel= model.getIdeas();
        return imodel;
    }

    @Override
    public List<startupModel> getAll() {
        List<startupModel> str=repo.findAll();
        return str;
    }

    @Override
    public String updateIdea(int startupId, int ideaId, ideaModel idd) throws startupNotFound {
        if(!repo.findById(startupId).isPresent())
        {
            throw new startupNotFound();
        }
        startupModel str=repo.findById(startupId).get();
        startupModel temp=str;
        List<ideaModel> tempI=str.getIdeas();
        for(ideaModel i:tempI)
        {
            if(i.getIdeaId()==ideaId)
            {
                i.setIdeaType(idd.getIdeaType());
                i.setStartupName(idd.getStartupName());
                i.setIdeaAmount(idd.getIdeaAmount());
                i.setIdeaDesc(idd.getIdeaDesc());
                i.setInvested(idd.isInvested());
                i.setStartupName(idd.getStartupName());
                break;
            }
        }
        repo.save(str);
        return "Successfully updated the idea";
    }
    public void updateInvested(int startupId, int ideaId, ideaModel idd){
        startupModel str=repo.findById(startupId).get();
        startupModel temp=str;
        List<ideaModel> tempI=str.getIdeas();
        for(ideaModel i:tempI)
        {
            if(i.getIdeaId()==ideaId)
            {
                i.setInvested(idd.isInvested());
                i.setPendingAmount(idd.getPendingAmount());
            }
        }
        repo.save(str);
    }

    @Override
    public List<whoInvested> showI(int sid,int id) {
        List<whoInvested> lst=repos.findAll();
        List<whoInvested> temp=new ArrayList<whoInvested>();
        for(whoInvested who:lst)
        {
            if(who.getStartupId()==sid)
            {
                if(who.getIdeaId()==id)
                {
                    temp.add(who);
                }
            }
        }
        return temp;
    }

    @Override
    public startupModel updateInfoo(int id, startupModel model) {
        startupModel mod=repo.findById(id).get();
        mod.setStartCity(model.getStartCity());
        mod.setStartType(model.getStartType());
        mod.setIdeas(model.getIdeas());
        repo.save(mod);
        return mod;
    }
//    public String sendData()
//    {
//        List<ideaModel> temp=new ArrayList<ideaModel>();
//        List<startupModel> str=repo.findAll();
//        for(startupModel s:str)
//        {
//            List<ideaModel> t=s.getIdeas();
//            for(ideaModel m:t)
//            {
//                temp.add(m);
//            }
//        }
//        JSONObject obj=new JSONObject();
//        StartupDTO dto=new StartupDTO();
//        obj.put("startup",startups);
//        dto.setJsonObject(obj);
//        rt.convertAndSend(exchange.getName(), "startup_route", obj);
//        return "Startup info is sent to Investors";
//    }
}
