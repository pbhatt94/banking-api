package com.wg.banking.controller;

import com.wg.banking.constants.TransactionConstants;
import com.wg.banking.helper.UniqueIdGenerator;
import com.wg.banking.helper.printer.TransactionPrinter;
import com.wg.banking.model.Transaction;
import com.wg.banking.service.TransactionService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private static final String ERROR_RETRIEVING_TRANSACTIONS = "Error retrieving transactions: ";
	private static final String TRANSACTION_NOT_FOUND = "Transaction not found.";
	private TransactionService transactionService;
    

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void addTransaction(Transaction transaction) {                
        try {
			transactionService.addTransaction(transaction);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
    }

    public void getTransactionById(String transactionId) {
    	try {
            Transaction transaction = transactionService.getTransactionById(transactionId);
            if (transaction == null) {
                System.out.println(TRANSACTION_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(ERROR_RETRIEVING_TRANSACTIONS + e.getMessage());
        }
    }

    public List<Transaction> getAllTransactions() {
    	List<Transaction> transactions = new ArrayList<>();
    	try {
            transactions = transactionService.getAllTransactions();
        } catch (Exception e) {
            System.out.println(ERROR_RETRIEVING_TRANSACTIONS + e.getMessage());
        }
    	return transactions;
    }
    
    public void getAllTransactions(String accountNo) {
    	try {
            List<Transaction> transactions = transactionService.getAllTransactions(accountNo);
            TransactionPrinter.printTransactions(transactions);
        } catch (Exception e) {
            System.out.println(ERROR_RETRIEVING_TRANSACTIONS + e.getMessage());
        }
    }
    
    public List<Transaction> getAllTransactionsByBranch(String branchId) {
    	List<Transaction> transactions = new ArrayList<>();
    	try {
            transactions = transactionService.getAllTransactionsByBranch(branchId);
        } catch (Exception e) {
            System.out.println(ERROR_RETRIEVING_TRANSACTIONS + e.getMessage());
        }
    	return transactions;
    }
    
    
    public void withdrawMoney(double amount, String sourceAccountNo, String destinationAccountNo) {
    	String transactionId = UniqueIdGenerator.generateUniqueId();
    	Timestamp now = new Timestamp(System.currentTimeMillis());
		String transactionTypeString = TransactionConstants.WITHDRAWAL_TYPE;
        Transaction transaction = new Transaction(transactionId, amount, Transaction.TransactionType.valueOf(transactionTypeString), sourceAccountNo, destinationAccountNo, now);
        addTransaction(transaction);
    }
    
    public void depositMoney(double amount, String sourceAccountNo, String destinationAccountNo) {
    	String transactionId = UniqueIdGenerator.generateUniqueId();
    	Timestamp now = new Timestamp(System.currentTimeMillis());
		String transactionTypeString = TransactionConstants.DEPOSIT_TYPE;
        Transaction transaction = new Transaction(transactionId, amount, Transaction.TransactionType.valueOf(transactionTypeString), sourceAccountNo, destinationAccountNo, now);
        addTransaction(transaction);
    }
    
    public void transferMoney(double amount, String sourceAccountNo, String destinationAccountNo) {
    	String transactionId = UniqueIdGenerator.generateUniqueId();
    	Timestamp now = new Timestamp(System.currentTimeMillis());
		String transactionTypeString = TransactionConstants.TRANSFER_TYPE;
        Transaction transaction = new Transaction(transactionId, amount, Transaction.TransactionType.valueOf(transactionTypeString), sourceAccountNo, destinationAccountNo, now);
        addTransaction(transaction);
    }
}
