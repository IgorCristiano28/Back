package com.igor.backend.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishDetailsDTO {
	
		@JsonProperty("environment")
	    private String environment;

	    @JsonProperty("locale")
	    private String locale;

	    @JsonProperty("time")
	    private String time;

	    @JsonProperty("user")
	    private String user;

}
