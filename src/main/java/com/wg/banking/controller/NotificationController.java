package com.wg.banking.controller;

import com.wg.banking.constants.StringConstants;
import com.wg.banking.constants.UserConstants;
import com.wg.banking.dao.UserDAO;
import com.wg.banking.dao.impl.UserDAOImpl;
import com.wg.banking.helper.GetUserInput;
import com.wg.banking.helper.UniqueIdGenerator;
import com.wg.banking.helper.ValidateInputs;
import com.wg.banking.helper.printer.UserPrinter;
import com.wg.banking.model.Notification;
import com.wg.banking.model.NotificationDetails;
import com.wg.banking.model.User;
import com.wg.banking.service.NotificationService;
import com.wg.banking.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NotificationController {
	private NotificationService notificationService;
    private Scanner scanner;
    private UserService userService;
    private UserDAO userDAO;
    

//    public NotificationController(NotificationService notificationService) {
//        this.notificationService = notificationService;
//        this.scanner = new Scanner(System.in);
//        userDAO = new UserDAOImpl();
//        userService = new UserService(userDAO);
//    }

    public void addNotification() {
        String notificationId = UniqueIdGenerator.generateUniqueId();
        
        System.out.print(StringConstants.ENTER_NOTIFICATION_MESSAGE);
        String message = scanner.nextLine();
        while(!ValidateInputs.isValidString(message)) {
        	System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
            System.out.print(StringConstants.ENTER_NOTIFICATION_MESSAGE);
        	message = scanner.nextLine();
        }
        
        System.out.print(StringConstants.ENTER_TYPE_OF_NOTIFICATION);
        String notificationType = scanner.nextLine().toUpperCase();
         
        while(!ValidateInputs.isValidNotificationType(notificationType)) {
        	System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
        	notificationType = scanner.nextLine().toUpperCase();
        }
        
        System.out.print(StringConstants.SELECT_THE_RECEIVER);
        List<User> users = userService.getAllActiveUsers()
        								.stream()
        								.filter(user -> user.getRole().toString().equalsIgnoreCase(UserConstants.CUSTOMER))
        								.collect(Collectors.toList());
        UserPrinter.printUsers(users);
        System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
        int index = GetUserInput.getUserChoice();
        
        while(index <=0 || index > users.size()) {
        	System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
        	System.out.println(StringConstants.ENTER_INDEX_MESSAGE);
        	index = GetUserInput.getUserChoice();
        }
        
        String receiverId = users.get(index-1).getUserId();
        
        Date now = new Date(System.currentTimeMillis());
        
        Notification notification = new Notification(notificationId, message, Notification.NotificationType.valueOf(notificationType), receiverId, now);
        
        notificationService.addNotification(notification);
    }

    public void getNotificationById(String notificationId) {
    	try {
            Notification notification = notificationService.getNotificationById(notificationId);
            if (notification == null) {
                System.out.println("Notification not found.");
                return;
            } 
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_NOTIFICATIONS + e.getMessage());
        } 
    }

    public List<Notification> getAllNotifications() {
    	List<Notification> notifications = new ArrayList<>();
    	try {
            notifications = notificationService.getAllNotifications();
                       
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_NOTIFICATIONS + e.getMessage());
        }
    	return notifications;
    }
    
    public List<NotificationDetails> getAllNotificationDetails() {
    	List<NotificationDetails> notifications = new ArrayList<>();
    	try { 
            notifications = notificationService.getAllNotificationDetails();
                       
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_NOTIFICATIONS + e.getMessage());
        }
    	return notifications;
    }
    
    public List<NotificationDetails> getAllNotificationDetails(User user) {
    	List<NotificationDetails> notifications = new ArrayList<>();
    	try { 
    		notifications = notificationService.getAllNotificationDetails(user);
    		
    	} catch (Exception e) {
    		System.out.println(StringConstants.ERROR_RETRIEVING_NOTIFICATIONS + e.getMessage());
    	}
    	return notifications;
    }
    
    public List<Notification> getAllNotifications(String userId) {
    	List<Notification> notifications = new ArrayList<>();
    	try {
            notifications = notificationService.getAllNotifications(userId);
                        
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_NOTIFICATIONS + e.getMessage());
        }
    	return notifications;
    }
}

