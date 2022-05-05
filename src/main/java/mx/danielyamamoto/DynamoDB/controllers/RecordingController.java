package mx.danielyamamoto.DynamoDB.controllers;

import mx.danielyamamoto.DynamoDB.dto.RecordingDTO;
import mx.danielyamamoto.DynamoDB.models.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.danielyamamoto.DynamoDB.services.RecordingService;

@RestController
@RequestMapping("/v1/recording")
public class RecordingController {
    @Autowired
    RecordingService recordingService;

    // Insert a tuple
    @PostMapping("/save")
    public RecordingDTO save(@RequestBody RecordingDTO recording) throws Exception {
        recordingService.createRecording(recording);
        return recording;
    }

    // Select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Recording>> getAll() {
        return ResponseEntity.ok(recordingService.listAll());
    }

    @GetMapping("/{agentId}")
    public ResponseEntity<Iterable<Recording>> getRecording(@PathVariable("agentId") String agentId) {
        return ResponseEntity.ok(recordingService.findRecording(agentId));
    }

    // By Index
    @GetMapping("/agentName/{agentName}")
    public ResponseEntity<Iterable<Recording>> getByAgentName(@PathVariable("agentName") String agentName) {
        // if(!personaService.existsId(personaID))
        //    return new ResponseEntity("no existe", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(recordingService.findByAgentName(agentName));
    }

    @GetMapping("/{agentId}/{timestamp}")
    public Recording getRecordingById(@PathVariable("agentId") String agentId, @PathVariable("timestamp") Long timestamp) {
        return recordingService.findById(agentId, timestamp);
    }

    // Delete a tuple
    @DeleteMapping("/delete/{agentId}/{timestamp}")
    public ResponseEntity<?> deleteById(@PathVariable("agentId") String personaID, @PathVariable("timestamp") Long timestamp) {
        recordingService.deleteById(personaID, timestamp);
        return new ResponseEntity("Agent deleted", HttpStatus.OK);
    }

    // Update a tuple
    @PutMapping("/update")
    public ResponseEntity<Recording> update(@RequestBody RecordingDTO recording) {
        return ResponseEntity.ok(recordingService.update(recording));
    }
}
