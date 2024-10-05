package com.wg.banking.model;

import java.util.Date;
import java.util.Objects;

public class Issue {

    public enum Status {
        RESOLVED,
        PENDING
    }

    private String issueID;
    private String message;
    private String userId;
    private Status status;
    private Date createdAt;
    
    public Issue() { }

    public Issue(String issueID, String message, String userId, Status status, Date createdAt) {
        this.issueID = issueID;
        this.message = message;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }


	public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

    @Override
    public String toString() {
        return "Issue{" +
                "issueID='" + issueID + '\'' +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(createdAt, issueID, message, status, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(issueID, other.issueID)
				&& Objects.equals(message, other.message) && status == other.status
				&& Objects.equals(userId, other.userId);
	}
    
    
}
