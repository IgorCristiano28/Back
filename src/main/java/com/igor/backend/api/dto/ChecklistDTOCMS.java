package com.igor.backend.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDTOCMS {
	
	 	@JsonProperty("_version")
	    private int version;

	    @JsonProperty("locale")
	    private String locale;

	    @JsonProperty("uid")
	    private String uid;

	    @JsonProperty("ACL")
	    private Object acl;

	    @JsonProperty("_in_progress")
	    private boolean inProgress;

	    @JsonProperty("checklist_icon")
	    private String checklistIcon;

	    @JsonProperty("checklist_items")
	    private List<ChecklistItemDTOCMS> checklistItems;

	    @JsonProperty("checklist_title")
	    private String checklistTitle;

	    @JsonProperty("checklist_type")
	    private String checklistType;

	    @JsonProperty("checklist_uid")
	    private String checklistUid;

	    @JsonProperty("created_at")
	    private String createdAt;

	    @JsonProperty("created_by")
	    private String createdBy;

	    @JsonProperty("tags")
	    private List<Object> tags;

	    @JsonProperty("title")
	    private String title;

	    @JsonProperty("updated_at")
	    private String updatedAt;

	    @JsonProperty("updated_by")
	    private String updatedBy;

	    @JsonProperty("publish_details")
	    private PublishDetailsDTO publishDetails;

}
