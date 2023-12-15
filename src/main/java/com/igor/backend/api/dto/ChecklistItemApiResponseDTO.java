package com.igor.backend.api.dto;

import com.igor.backend.model.entity.ChecklistItem;

public class ChecklistItemApiResponseDTO {
	
		private String uid;
		private String contentTypeUid;
		private boolean indicadorItemChecklist;
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
		public boolean isIndicadorItemChecklist() {
			return indicadorItemChecklist;
		}
		public void setIndicadorItemChecklist(boolean indicadorItemChecklist) {
			this.indicadorItemChecklist = indicadorItemChecklist;
		}
	    
		
		public static ChecklistItemApiResponseDTO fromChecklistItem(ChecklistItem checklistItem) {
	        ChecklistItemApiResponseDTO dto = new ChecklistItemApiResponseDTO();
	        dto.setUid(checklistItem.getUid());
	        // Mapeie outros campos se necessário
	        // dto.setAlgumCampo(checklistItem.getAlgumCampo());
	        return dto;
	    }
	    

}
