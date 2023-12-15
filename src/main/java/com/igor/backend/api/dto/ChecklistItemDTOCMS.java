package com.igor.backend.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistItemDTOCMS {
	@JsonProperty("_content_type_uid")
    private String contentTypeUid;
	
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

    private List<Object> tags;

    private String title;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("updated_by")
    private String updatedBy;

    @JsonProperty("publish_details")
    private PublishDetailsDTO publishDetails;
}
