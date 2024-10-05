package com.wg.banking.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wg.banking.constants.StringConstants;
import com.wg.banking.constants.UserConstants;
import com.wg.banking.helper.GetUserInput;
import com.wg.banking.helper.PasswordUtil;
import com.wg.banking.helper.ValidateInputs;
import com.wg.banking.model.User;
import com.wg.banking.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	private Scanner scanner = new Scanner(System.in);
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(User user) {
        try {
            userService.addUser(user);
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_ADDING_USER + e.getMessage());
        }
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable String userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
            	System.out.println(StringConstants.USER_NOT_FOUND); 
            	return null;
            } 
            return user;            
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_USER + e.getMessage());
        }
        return null;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
    	List<User> users = new ArrayList<>();
        try {
            users = userService.getAllActiveUsers();
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_USER + e.getMessage());
        }
        return users;
    }
    
    public List<User> getAllInactiveUsers() {
    	List<User> users = new ArrayList<>();
        try {
            users = userService.getAllInactiveUsers();
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_USER + e.getMessage());
        }
        return users;
    }

    public boolean updateUser(String userId) {
    	try {
            User user = userService.getUserById(userId);
            if (user == null) {
            	System.out.println(StringConstants.USER_NOT_FOUND);
            	return false;
            }
            
            boolean continueUpdating = true;

            while (continueUpdating) {
                System.out.println(StringConstants.UPDATE_USER_MENU);

                System.out.print(StringConstants.SELECT_AN_OPTION);
                int choice = GetUserInput.getUserChoice();
                
                String columnToUpdate = "";

                switch (choice) {
                    case 1:
                    	columnToUpdate = UserConstants.EMAIL_COLUMN;
                    	System.out.print(StringConstants.ENTER_EMAIL);
                		String email = scanner.nextLine();
                		
                		while(!ValidateInputs.isValidEmail(email)) {
                			System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
                			System.out.print(StringConstants.ENTER_EMAIL);
                			email = scanner.next();
                		}
                        user.setEmail(email);
                        break;
                    case 2:
                    	columnToUpdate = UserConstants.USERNAME_COLUMN;
                    	System.out.print(StringConstants.ENTER_USERNAME);
                		String username = scanner.nextLine();
                		
                		while(!ValidateInputs.isValidUsername(username)) {
                			System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
                			System.out.print(StringConstants.ENTER_USERNAME);
                			username = scanner.nextLine();
                		}
                        user.setUsername(username);
                        break;
                    case 3:
                    	columnToUpdate = UserConstants.PASSWORD_COLUMN;
                    	System.out.print(StringConstants.ENTER_PASSWORD);
                		String password = scanner.nextLine();
                		
                		while(!PasswordUtil.isPasswordStrong(password)) {
                			System.out.println(StringConstants.PASSWORD_NOT_STRONG_ENOUGH);
                			System.out.print(StringConstants.ENTER_PASSWORD);
                			password = scanner.next();
                		}                            
                		
                		password = PasswordUtil.hashPassword(password);
                		user.setPassword(password);
                        break;
                    case 4:
						columnToUpdate = UserConstants.AGE_COLUMN;
						System.out.print(StringConstants.ENTER_AGE);
						int age = GetUserInput.getUserChoice();
						
						while(!ValidateInputs.isValidAge(age)) {
							System.out.println(StringConstants.ENTER_A_VALID_AGE);
							System.out.print(StringConstants.ENTER_AGE);
							age = GetUserInput.getUserChoice();
						}
                        user.setAge(age);
                        break;
                    case 5:
                    	columnToUpdate = UserConstants.GENDER_COLUMN;
                    	System.out.print(StringConstants.ENTER_GENDER);
                		
                		String genderString = scanner.nextLine().toUpperCase();
                		while(!ValidateInputs.isValidGender(genderString)) {
                			System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
                			System.out.print(StringConstants.ENTER_GENDER);
                			genderString = scanner.next();
                		}
                		
                		User.Gender gender = User.Gender.valueOf(genderString.toUpperCase());
                		System.out.println(gender.toString());
                        user.setGender(gender);
                        break;
                    case 6:
						columnToUpdate = UserConstants.PHONE_NO_COLUMN;
						System.out.print(StringConstants.ENTER_PHONE_NUMBER);
						String phoneNo = scanner.nextLine();
						
						while(!ValidateInputs.isValidPhoneNo(phoneNo)) {
							System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
							System.out.print(StringConstants.ENTER_PHONE_NUMBER);
							phoneNo = scanner.next();
						}
                        user.setPhoneNo(phoneNo);
                        break;
                    case 7:
						columnToUpdate = UserConstants.ADDRESS_COLUMN;
                    	System.out.print(StringConstants.ENTER_ADDRESS);
            			String address = scanner.nextLine();
            			while(!ValidateInputs.isValidString(address)) {
            				System.out.println(StringConstants.INVALID_INPUT_MESSAGE);
            				System.out.print(StringConstants.ENTER_ADDRESS);
            				address = scanner.nextLine();
            			}
                        user.setAddress(address);
                        break;
                    case 8:
                    	continueUpdating = false;
                        break;
                    default:
                        System.out.println(StringConstants.INVALID_SWITCH_CASE_INPUT);
                }
                if(!continueUpdating) break;
                user.setUpdatedAt(new Date());
                userService.updateUser(user, columnToUpdate);
            }
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_UPDATING_USER + e.getMessage());
            return false;
        }
    	return true;
    }

    public boolean deleteUser(String userId) {
        try {
            return userService.deleteUser(userId);
        } catch (Exception e) {
            System.out.println(StringConstants.ERROR_DELETING_USER + e.getMessage());
        }
        return false;
    }
    
    public List<User> getAvailableUsers() {
    	return userService.getAvailableUsers();
    }
    
    public List<User> getAvailableManagers() {
    	return userService.getAvailableManagers();
    }
}
