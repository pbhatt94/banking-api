package com.wg.banking.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.wg.banking.dao.NotificationDAO;
import com.wg.banking.helper.LoggingUtil;
import com.wg.banking.model.Account;
import com.wg.banking.model.Branch;
import com.wg.banking.model.Notification;
import com.wg.banking.model.NotificationDetails;
import com.wg.banking.model.User;

public class NotificationService {
    private NotificationDAO notificationDAO;
    private UserService userService;
    private BranchService branchService;
    private AccountService accountService;
    
    private static Logger logger = LoggingUtil.getLogger(NotificationService.class);

    public NotificationService(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }
    
	public NotificationService(NotificationDAO notificationDAO, UserService userService,
			BranchService branchService, AccountService accountService) {
    	this.notificationDAO = notificationDAO;
          this.userService = userService;
          this.accountService = accountService;
          this.branchService = branchService;
    }
    
    
    public List<NotificationDetails> getAllNotificationDetails() {
    	List<NotificationDetails> notificationDetails = new ArrayList<>();
    	try {
    		List<Notification> notifications =  notificationDAO.getAllNotifications();
    		
    		List<Notification> sortedNotifications = notifications.stream()
    				.sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
    				.collect(Collectors.toList()); 
    		
    		
    		List<User> allUsers = userService.getAllActiveUsers();
    		Map<String, Object> userIdToObjectMapping = new HashMap<>();
    		userIdToObjectMapping = allUsers.stream()
								            .filter(user -> user != null && user.getUserId() != null)
								            .collect(Collectors.toMap(User::getUserId, Function.identity()));
    		for(Notification notification: sortedNotifications) {
    			User receiver = (User) userIdToObjectMapping.get(notification.getReceiverId());
    			NotificationDetails notificationDetail = new NotificationDetails(notification, receiver);
    			notificationDetails.add(notificationDetail);
    		}
    	} catch (ClassNotFoundException | SQLException e) {
    		logger.severe(e.getMessage());
    		e.printStackTrace();
    	}
    	return notificationDetails;
    }   
    
    public List<NotificationDetails> getAllNotificationDetails(User manager) {
    	List<NotificationDetails> notificationDetails = new ArrayList<>();
    	try {
    		List<Notification> notifications =  notificationDAO.getAllNotifications();
    		
    		List<Notification> sortedNotifications = notifications.stream()
    				.sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
    				.collect(Collectors.toList()); 
    		
    		
    		Branch branch = branchService.getBranchByManagerId(manager);
    		
    		List<Account> accounts = accountService.getAllAccounts(branch.getBranchId());
    		Set<String> branchAccountIDs = accounts.stream()
    												.map(account -> account.getOwnerId())
    												.collect(Collectors.toSet());
    		
    		List<User> allUsers = userService.getAllActiveUsers()
    										.stream()
    										.filter(user -> branchAccountIDs.contains(user.getUserId()))
    										.collect(Collectors.toList());
    		
    		final Map<String, Object> userIdToObjectMapping = allUsers.stream()
    				.filter(user -> user != null && user.getUserId() != null)
    				.collect(Collectors.toMap(User::getUserId, Function.identity()));
    		
    		List<Notification> filteredNotifications = sortedNotifications.stream()
    												.filter(notification -> userIdToObjectMapping.containsKey(notification.getReceiverId()))
    												.collect(Collectors.toList());
    		
    		for(Notification notification: filteredNotifications) {
    			User receiver = (User) userIdToObjectMapping.get(notification.getReceiverId());
    			NotificationDetails notificationDetail = new NotificationDetails(notification, receiver);
    			notificationDetails.add(notificationDetail);
    		}
    	} catch (ClassNotFoundException | SQLException e) {
    		logger.severe(e.getMessage());
    		e.printStackTrace();
    	}
    	return notificationDetails;
    }  
    
    public Notification getNotificationById(String notificationId) {
        try {
			return notificationDAO.getNotificationById(notificationId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
        return null;
    }

    public List<Notification> getAllNotifications() {
        try {
			List<Notification> notifications =  notificationDAO.getAllNotifications();
			List<Notification> sortedNotifications = notifications.stream()
            .sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))
            .collect(Collectors.toList()); 

			return sortedNotifications;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
        return null;
    }
       

    public void addNotification(Notification notification)  {
        try {
			notificationDAO.addNotification(notification);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
    }
    
    public List<Notification> getAllNotifications(String userId) {
        try {
			List<Notification> notifications = notificationDAO.getAllNotifications(userId);
			return notifications;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
        return null;
    }
}
