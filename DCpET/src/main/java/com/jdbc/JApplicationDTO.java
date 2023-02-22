package com.jdbc;

public class JApplicationDTO {
	private int applicationId;
	private String userId,reason, status, leaveStart, leaveEnd;
	
	public int getApplicationId() {
		return applicationId;
	}
	
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getLeaveStart() {
		return leaveStart;
	}
	
	public void setLeaveStart(String leaveStart) {
		this.leaveStart = leaveStart;
	}
	
	public String getLeaveEnd() {
		return leaveEnd;
	}
	
	public void setLeaveEnd(String leaveEnd) {
		this.leaveEnd = leaveEnd;
	}
}
