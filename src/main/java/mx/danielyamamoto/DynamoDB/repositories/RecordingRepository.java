package mx.danielyamamoto.DynamoDB.repositories;

import mx.danielyamamoto.DynamoDB.dto.RecordingDTO;
import mx.danielyamamoto.DynamoDB.models.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;

@Repository
public class RecordingRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public RecordingRepository() {
        super();
    }

    public void save(final Recording recording) {
        DynamoDbTable<Recording> recordingTable = getTable();
        recordingTable.putItem(recording);
    }

    private DynamoDbTable<Recording> getTable() {
        DynamoDbTable<Recording> recordingTable = dynamoDbEnhancedClient.table("Recording", TableSchema.fromBean(Recording.class));
        return recordingTable;
    }

    public Iterable<Recording> findAll() {
        DynamoDbTable<Recording> recordingTable = getTable();
        return recordingTable.scan().items();
    }

    public void deleteById(final String agentId, final Long timestamp) {
        DynamoDbTable<Recording> recordingTable = getTable();

        Key key = Key.builder().partitionValue(agentId).sortValue(timestamp).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        recordingTable.deleteItem(deleteRequest);
    }

    public Recording update(final Recording recording) {
        DynamoDbTable<Recording> recordingTable = getTable();
        Key key = Key.builder().partitionValue(recording.getAgentId()).sortValue(recording.getTimestamp()).build();
        Recording recordingRegister = recordingTable.getItem(r->r.key(key));
        // Info to update
        recordingRegister.setAgentName(recording.getAgentName());
        recordingRegister.setAgentLastname(recording.getAgentLastname());
        // Update
        recordingTable.updateItem(recordingRegister);
        return recording;
    }
}
