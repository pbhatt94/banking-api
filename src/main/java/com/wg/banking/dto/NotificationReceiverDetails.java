package com.wg.banking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationReceiverDetails {
	private String id;
	
	private String name;
	
	private String email;
	
	private String username;
}
