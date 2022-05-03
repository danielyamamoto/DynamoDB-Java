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
    public ResponseEntity<Iterable<Recording>> getAll(){
        return ResponseEntity.ok(recordingService.listAll());
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
