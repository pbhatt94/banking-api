package com.wg.banking.model;

import java.util.Date;
import java.util.Objects;

public class User {
    private String userId; // Primary Key
    private String name;
    private String email;
    private String username;
    private String password;
    private int age;
    private Gender gender;
    private String phoneNo;
    private String address;
    private Role role;
    private Date createdAt;
    private Date updatedAt;

    // Enum for Gender
    public enum Gender {
        M, F, O
    }

    // Enum for Role
    public enum Role {
        CUSTOMER, BRANCH_MANAGER, ADMIN
    }

    // Constructors
    public User() {
    }

    public User(String userId, String name, String email, String username, String password,
                int age, Gender gender, String phoneNo, String address, Role role, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(address, age, createdAt, email, gender, name, password, phoneNo, role, updatedAt, userId,
				username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(address, other.address) && age == other.age && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(email, other.email) && gender == other.gender && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phoneNo, other.phoneNo)
				&& role == other.role && Objects.equals(updatedAt, other.updatedAt)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
	}
    
}
