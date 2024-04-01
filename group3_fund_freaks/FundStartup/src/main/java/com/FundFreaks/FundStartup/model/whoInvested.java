package com.FundFreaks.FundStartup.model;

public class whoInvested {
    private String name;
    private String email;
    private Double fund;
    private int startupId;
    private int ideaId;
    private int investorId;
    private boolean accepted;

    public whoInvested() {
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

    public whoInvested(String name, String email, Double fund, int startupId, int ideaId, int investorId, boolean accepted) {
        this.name = name;
        this.email = email;
        this.fund = fund;
        this.startupId = startupId;
        this.ideaId = ideaId;
        this.investorId = investorId;
        this.accepted = accepted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public int getStartupId() {
        return startupId;
    }

    public void setStartupId(int startupId) {
        this.startupId = startupId;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
