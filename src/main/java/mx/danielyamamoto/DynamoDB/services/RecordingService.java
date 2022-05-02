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

    //READ
    public Iterable<Recording> listAll() {
        return recordingRepository.findAll();
    }
}
