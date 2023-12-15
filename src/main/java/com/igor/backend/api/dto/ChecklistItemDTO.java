package com.igor.backend.api.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ChecklistItemDTO {
	 	private String uid;
	    private String contentTypeUid;
	    private String checklistTitle;
	    private String checklistType;
	    private boolean indicadorItemChecklist;
	    private LocalDateTime updatedAt;
		
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
		public String getChecklistTitle() {
			return checklistTitle;
		}
		public void setChecklistTitle(String checklistTitle) {
			this.checklistTitle = checklistTitle;
		}
		public String getChecklistType() {
			return checklistType;
		}
		public void setChecklistType(String checklistType) {
			this.checklistType = checklistType;
		}
		public boolean isIndicadorItemChecklist() {
			return indicadorItemChecklist;
		}
		public void setIndicadorItemChecklist(boolean indicadorItemChecklist) {
			this.indicadorItemChecklist = indicadorItemChecklist;
		}
		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
		
}
