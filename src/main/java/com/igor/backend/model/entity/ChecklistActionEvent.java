package com.igor.backend.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistActionEvent {
	 	@JsonProperty("checklist_action_event_type")
	    private String checklistActionEventType;

	    @JsonProperty("checklist_action_event_path")
	    private String checklistActionEventPath;
}
