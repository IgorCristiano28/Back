package com.igor.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishDetails {
	 @JsonProperty("environment")
	    private String environment;

	    @JsonProperty("locale")
	    private String locale;

	    @JsonProperty("time")
	    private String time;

	    @JsonProperty("user")
	    private String user;
	    
	    
	

}
