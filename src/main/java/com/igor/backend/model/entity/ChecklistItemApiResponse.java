package com.igor.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChecklistItemApiResponse {
	@JsonProperty("uid")
    private String uid;

    @JsonProperty("_content_type_uid")
    private String contentTypeUid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getContentTypeUid() {
		return contentTypeUid;
	}

	public void setContentTypeUid(String contentTypeUid) {
		this.contentTypeUid = contentTypeUid;
	}
    
    

}
