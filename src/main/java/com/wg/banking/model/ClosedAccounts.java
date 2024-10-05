package com.wg.banking.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ClosedAccounts {

    private String id;       
    private String username;  
    private Timestamp closedAt; 
    
    // Default constructor
    public ClosedAccounts() {
    }

    // Parameterized constructor
    public ClosedAccounts(String id, String username, Timestamp closedAt) {
        this.id = id;
        this.username = username;
        this.closedAt = closedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter for closedAt
    public Timestamp getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Timestamp closedAt) {
        this.closedAt = closedAt;
    }

    @Override
    public String toString() {
        return "ClosedAccount{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", closedAt=" + closedAt +
               '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(closedAt, id, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClosedAccounts other = (ClosedAccounts) obj;
		return Objects.equals(closedAt, other.closedAt) && Objects.equals(id, other.id)
				&& Objects.equals(username, other.username);
	}
    
    
}
