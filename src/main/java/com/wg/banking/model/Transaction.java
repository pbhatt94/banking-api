package com.wg.banking.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Transaction {
    private String transactionId;
    private double amount;
    private TransactionType transactionType;
    private String sourceAccountId;
    private String destinationAccountId;
    private Timestamp createdAt;

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER
    }

    public Transaction() { }

    public Transaction(String transactionId, double amount,
                       TransactionType transactionType, String sourceAccountId, 
                       String destinationAccountId, Timestamp createdAt) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.createdAt = createdAt;
    } 

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", sourceAccountId='" + sourceAccountId + '\'' +
                ", destinationAccountId='" + destinationAccountId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(amount, createdAt, destinationAccountId, sourceAccountId, transactionId, transactionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(destinationAccountId, other.destinationAccountId)
				&& Objects.equals(sourceAccountId, other.sourceAccountId)
				&& Objects.equals(transactionId, other.transactionId) && transactionType == other.transactionType;
	}
    
    
}
