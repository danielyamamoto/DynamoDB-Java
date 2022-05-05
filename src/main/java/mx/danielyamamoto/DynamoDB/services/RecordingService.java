package mx.danielyamamoto.DynamoDB.services;

import mx.danielyamamoto.DynamoDB.dto.RecordingDTO;
import mx.danielyamamoto.DynamoDB.models.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.danielyamamoto.DynamoDB.repositories.RecordingRepository;

@Service
public class RecordingService {
    @Autowired
    RecordingRepository recordingRepository;

    // CREATE
    public void createRecording(final RecordingDTO recordingDTO) {
        Recording r = new Recording();
        r.setAgentId(recordingDTO.getAgentId());
        r.setTimestamp(recordingDTO.getTimestamp());
        r.setVideoId(recordingDTO.getVideoId());
        r.setDuration(recordingDTO.getDuration());
        r.setTag(recordingDTO.getTag());
        r.setCategory(recordingDTO.getCategory());
        r.setSuccessfulOutcome(recordingDTO.getSuccessfulOutcome());
        r.setAgentName(recordingDTO.getAgentName());
        r.setAgentLastname(recordingDTO.getAgentLastname());
        r.setNoteId(recordingDTO.getNoteId());
        r.setNoteText(recordingDTO.getNoteText());
        r.setCustomerId(recordingDTO.getCustomerId());
        r.setCustomerName(recordingDTO.getCustomerName());
        r.setCustomerLastname(recordingDTO.getCustomerLastname());
        r.setResolution(recordingDTO.getResolution());
        r.setAllowSharing(recordingDTO.getAllowSharing());
        recordingRepository.save(r);
    }

    // READ
    public Iterable<Recording> listAll() {
        return recordingRepository.findAll();
    }

    public Iterable<Recording> findRecording(final String agentId) {
        return recordingRepository.findRecording(agentId);
    }

    public Recording findById(final String agentId, final Long timestamp) {
        return recordingRepository.findById(agentId, timestamp);
    }

    public Iterable<Recording> findByAgentName(final String agentName) {
        return recordingRepository.findByAgentName(agentName);
    }

    // DELETE
    public void deleteById(final String agentId, final Long timestamp) {
        recordingRepository.deleteById(agentId, timestamp);
    }

    // UPDATE
    public Recording update(RecordingDTO recordingDTO) {
        Recording r = new Recording();
        r.setAgentId(recordingDTO.getAgentId());
        r.setTimestamp(recordingDTO.getTimestamp());
        // Data to update
        r.setAgentName(recordingDTO.getAgentName());
        r.setAgentLastname(recordingDTO.getAgentLastname());
        return recordingRepository.update(r);
    }
}
