package com.investment.fundInvest.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document

public class investorModel {
    @Id
    private int investorId;
    private String investorName;
    private String investorEmail;
    private double amountInvested;
    private double fundingAmount;
    private List<wishListID> wishlist;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public investorModel() {
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
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "InvestorModel{" +
                "investorId=" + investorId +
                ", investorName='" + investorName + '\'' +
                ", investorEmail='" + investorEmail + '\'' +
                ", amountInvested=" + amountInvested +
                ", fundingAmount=" + fundingAmount +
                ", wishlist=" + wishlist +
                '}';
    }

    public investorModel(int investorId, String investorName, String investorEmail, double amountIvested, double fundingAmount, List<wishListID> wishlist) {
        this.investorId = investorId;
        this.investorName = investorName;
        this.investorEmail = investorEmail;
        this.amountInvested = amountInvested;
        this.fundingAmount = fundingAmount;
        this.wishlist = wishlist;
    }

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorEmail() {
        return investorEmail;
    }

    public void setInvestorEmail(String investorEmail) {
        this.investorEmail = investorEmail;
    }

    public double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public double getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(double fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public List<wishListID> getWishlist() {
        return wishlist;
    }

    public void setWishlist(List<wishListID> wishlist) {
        this.wishlist = wishlist;
    }
}
