package mx.danielyamamoto.DynamoDB.dto;

import java.util.Set;

public class RecordingDTO {
    private Integer duration, videoId, noteId, customerId;
    private String agentId, category, agentName, agentLastname, noteText, customerName, customerLastname, resolution;
    private Boolean successfulOutcome, allowSharing;
    private Long timestamp;
    private Set<String> tag;

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }

    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    public Integer getVideoId() { return videoId; }
    public void setVideoId(Integer videoId) { this.videoId = videoId; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Set<String> getTag() { return tag; }
    public void setTag(Set<String> tag) { this.tag = tag; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Boolean getSuccessfulOutcome() { return successfulOutcome; }
    public void setSuccessfulOutcome(Boolean successfulOutcome) { this.successfulOutcome = successfulOutcome; }

    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }

    public String getAgentLastname() { return agentLastname; }
    public void setAgentLastname(String agentLastname) { this.agentLastname = agentLastname; }

    public Integer getNoteId() { return noteId; }
    public void setNoteId(Integer noteId) { this.noteId = noteId; }

    public String getNoteText() { return noteText; }
    public void setNoteText(String noteText) { this.noteText = noteText; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerLastname() { return customerLastname; }
    public void setCustomerLastname(String customerLastname) { this.customerLastname = customerLastname; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public Boolean getAllowSharing() { return allowSharing; }
    public void setAllowSharing(Boolean allowSharing) { this.allowSharing = allowSharing; }
}
