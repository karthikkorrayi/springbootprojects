package com.FundFreaks.FundStartup.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class ideaModel {
    @Id
    private int ideaId;
    private String ideaDesc;
    private String ideaType;
    private double ideaAmount;
    private double pendingAmount;
    private boolean invested;
    private String startupName;

    @Override
    public String toString() {
        return "ideaModel{" +
                "ideaId=" + ideaId +
                ", ideaDesc='" + ideaDesc + '\'' +
                ", ideaType='" + ideaType + '\'' +
                ", ideaAmount=" + ideaAmount +
                ", pendingAmount=" + pendingAmount +
                ", invested=" + invested +
                ", startupName='" + startupName + '\'' +
                '}';
    }

    public ideaModel(String startupName) {
        this.startupName = startupName;
    }

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public ideaModel() {
        super();
    }

    public ideaModel(int ideaId, String ideaDesc, String ideaType, double ideaAmount, double pendingAmount, boolean invested, String startupName) {
        this.ideaId = ideaId;
        this.ideaDesc = ideaDesc;
        this.ideaType = ideaType;
        this.ideaAmount = ideaAmount;
        this.pendingAmount = pendingAmount;
        this.invested = invested;
        this.startupName = startupName;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public String getIdeaDesc() {
        return ideaDesc;
    }

    public void setIdeaDesc(String ideaDesc) {
        this.ideaDesc = ideaDesc;
    }

    public String getIdeaType() {
        return ideaType;
    }

    public void setIdeaType(String ideaType) {
        this.ideaType = ideaType;
    }

    public double getIdeaAmount() {
        return ideaAmount;
    }

    public void setIdeaAmount(double ideaAmount) {
        this.ideaAmount = ideaAmount;
    }

    public double getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public boolean isInvested() {
        return invested;
    }

    public void setInvested(boolean invested) {
        this.invested = invested;
    }
}
