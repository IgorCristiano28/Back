package com.igor.backend.model.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItem {
	@JsonProperty("_content_type_uid")
    private String contentTypeUid;

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

    @JsonProperty("checklist_action_event")
    private ChecklistActionEvent checklistActionEvent;

    @JsonProperty("checklist_item_subtitle")
    private String checklistItemSubtitle;

    @JsonProperty("checklist_item_title")
    private String checklistItemTitle;

    @JsonProperty("checklist_item_uid")
    private String checklistItemUid;

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
    private PublishDetails publishDetails;
	
	

}
