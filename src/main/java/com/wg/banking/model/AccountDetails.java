package com.wg.banking.model;

public class AccountDetails {
    private String accountNo;   
    private String ownerName;   
    private double balance;     
    private String branch; 

    public AccountDetails() {}

    public AccountDetails(String accountNo, String ownerName, double balance, String branch) {
        this.accountNo = accountNo;
        this.ownerName = ownerName;
        this.balance = balance;
        this.branch = branch;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "accountNo='" + accountNo + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", branch='" + branch + '\'' +
                '}';
    }
}
