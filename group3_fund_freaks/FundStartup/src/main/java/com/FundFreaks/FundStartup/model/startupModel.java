package com.FundFreaks.FundStartup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Startup_info")
public class startupModel {
    @Id
    private int id;
    private String startName;
    private String startEmail;
    private String startCity;
    private String startType;
    private List<ideaModel> ideas;

    public startupModel() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public startupModel(List<ideaModel> ideas) {
        this.ideas = ideas;
    }

    public List<ideaModel> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<ideaModel> ideas) {
        this.ideas = ideas;
    }

    public startupModel(int i, String zolo, String s, String bangalore, String pg, ideaModel ideaModel) {
        super();
    }

    public startupModel(int id, String startName, String startEmail, String startCity, String startType, List<ideaModel> ideas) {
        this.id = id;
        this.startName = startName;
        this.startEmail = startEmail;
        this.startCity = startCity;
        this.startType = startType;
        this.ideas = ideas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getStartEmail() {
        return startEmail;
    }

    public void setStartEmail(String startEmail) {
        this.startEmail = startEmail;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartType() {
        return startType;
    }

    public void setStartType(String startType) {
        this.startType = startType;
    }

}
