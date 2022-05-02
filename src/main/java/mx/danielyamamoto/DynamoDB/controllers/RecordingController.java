package mx.danielyamamoto.DynamoDB.controllers;

import mx.danielyamamoto.DynamoDB.dto.RecordingDTO;
import mx.danielyamamoto.DynamoDB.models.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.danielyamamoto.DynamoDB.services.RecordingService;

@RestController
@RequestMapping("/v1/recording")
public class RecordingController {
    @Autowired
    RecordingService recordingService;

    @PostMapping("/save")
    public RecordingDTO save(@RequestBody RecordingDTO recording) throws Exception {
        recordingService.createRecording(recording);
        return recording;
    }

    //select all tuples
    @GetMapping("/all")
    public ResponseEntity<Iterable<Recording>> getAll(){
        return ResponseEntity.ok(recordingService.listAll());
    }


}
