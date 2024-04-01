package com.investment.fundInvest.Model;

public class wishListID {
    private int ideaId;
    private String ideaDesc;
    private String startupName;
    private String ideaType;
    private double amountInvested;

    @Override
    public String toString() {
        return "wishListID{" +
                "ideaId=" + ideaId +
                ", ideaDesc='" + ideaDesc + '\'' +
                ", startupName='" + startupName + '\'' +
                ", ideaType='" + ideaType + '\'' +
                ", amountInvested=" + amountInvested +
                '}';
    }

    public wishListID() {
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
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public wishListID(int ideaId, String ideaDesc, String startupName, String ideaType, double amountInvested) {
        this.ideaId = ideaId;
        this.ideaDesc = ideaDesc;
        this.startupName = startupName;
        this.ideaType = ideaType;
        this.amountInvested = amountInvested;
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

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public String getIdeaType() {
        return ideaType;
    }

    public void setIdeaType(String ideaType) {
        this.ideaType = ideaType;
    }

    public double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(double amountInvested) {
        this.amountInvested = amountInvested;
    }
}
