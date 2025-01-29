package com.wg.banking.dto;

import com.wg.banking.model.IssuePriority;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IssueDto {
	@NotNull
	@NotEmpty
	private String title;
	
	@NotNull
	@NotEmpty
	private String description;
	
	@NotNull
	@NotEmpty
	private String userId;
	
	private IssuePriority priority;
}
