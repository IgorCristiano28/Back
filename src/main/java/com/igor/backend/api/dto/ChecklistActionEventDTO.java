package com.igor.backend.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igor.backend.model.entity.ChecklistActionEvent;
import com.igor.backend.model.entity.ChecklistItem;
import com.igor.backend.model.entity.PublishDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistActionEventDTO {

	 	@JsonProperty("checklist_action_event_type")
	    private String checklistActionEventType;

	    @JsonProperty("checklist_action_event_path")
	    private String checklistActionEventPath;
}
