package com.FundFreaks.FundStartup.service;

import com.FundFreaks.FundStartup.exception.ideaAlreadyExist;
import com.FundFreaks.FundStartup.exception.startupAlreadyExist;
import com.FundFreaks.FundStartup.exception.startupNotFound;
import com.FundFreaks.FundStartup.model.ideaModel;
import com.FundFreaks.FundStartup.model.startupModel;
import com.FundFreaks.FundStartup.model.whoInvested;

import java.util.List;

public interface startupService {
    public startupModel saveStartup(startupModel model) throws startupAlreadyExist;

    public startupModel getById(int id) throws startupNotFound;

    public List<ideaModel> findIdeaByCategory(String category);

    public startupModel addIdea(ideaModel model, int id) throws startupAlreadyExist, ideaAlreadyExist;

    public startupModel deleteIdea(int id,int idd) throws startupNotFound;
    public String fetchIdeaSe(int sid,int id,int iid);

    public List<ideaModel> getIdee(int id);
    public List<startupModel> findCity(String city);

    public List<ideaModel> getName(String name);

    public List<startupModel> getAll();

    public String updateIdea(int startupId, int ideaId, ideaModel idd) throws startupNotFound;
    public void updateInvested(int startupId, int ideaId, ideaModel idd);
    public List<whoInvested> showI(int sid, int id);

    public startupModel updateInfoo(int id, startupModel model);
}
