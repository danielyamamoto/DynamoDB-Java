package mx.danielyamamoto.DynamoDB.repositories;

import mx.danielyamamoto.DynamoDB.models.Recording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordingRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public RecordingRepository() {
        super();
    }

    // INSERT
    public void save(final Recording recording) {
        DynamoDbTable<Recording> recordingTable = getTable();
        recordingTable.putItem(recording);
    }

    // READ
    public Iterable<Recording> findAll() {
        DynamoDbTable<Recording> recordingTable = getTable();
        return recordingTable.scan().items();
    }

    public Iterable<Recording> findRecording(final String agentId) {
        DynamoDbTable<Recording> recordingTable = getTable();

        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(agentId).build());

        Iterable<Recording> result = recordingTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }

    public Recording findById(final String agentId, final Long timestamp) {
        DynamoDbTable<Recording> recordingTable = getTable();
        Key key = Key.builder().partitionValue(agentId).sortValue(timestamp).build();

        Recording result = recordingTable.getItem(key);
        return result;
    }

    // Utilización de índice global secundario con nombre como partition key
    public Iterable<Recording> findByAgentName(final String agentName) {
        List<Recording> listAgents = new ArrayList<>();
        Iterable<Recording> iterableRecordings;

        DynamoDbIndex<Recording> SGIAgentName = getTable().index("GSIAgentName");

        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(agentName).build());

        PageIterable<Recording> items = null;
        items = (PageIterable<Recording>) SGIAgentName.query(r -> r.queryConditional(queryConditional));
        items.forEach(flowPage -> {
            listAgents.addAll(flowPage.items());
        });

        iterableRecordings = listAgents;

        return iterableRecordings;
    }

    // DELETE
    public void deleteById(final String agentId, final Long timestamp) {
        DynamoDbTable<Recording> recordingTable = getTable();

        Key key = Key.builder().partitionValue(agentId).sortValue(timestamp).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        recordingTable.deleteItem(deleteRequest);
    }

    // UPDATE
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

    // PRIVATE METHODS
    private DynamoDbTable<Recording> getTable() {
        DynamoDbTable<Recording> recordingTable = dynamoDbEnhancedClient.table("Recording", TableSchema.fromBean(Recording.class));
        return recordingTable;
    }
}
