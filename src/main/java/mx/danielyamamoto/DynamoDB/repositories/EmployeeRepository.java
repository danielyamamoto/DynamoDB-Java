package mx.danielyamamoto.DynamoDB.repositories;

import mx.danielyamamoto.DynamoDB.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

@Repository
public class EmployeeRepository {
    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public EmployeeRepository() { super(); }

    // INSERT
    public void save(final Employee employee) {
        DynamoDbTable<Employee> employeeTable = getTable();
        employeeTable.putItem(employee);
    }

    // READ
    public Iterable<Employee> findAll() {
        DynamoDbTable<Employee> employeeTable = getTable();
        return employeeTable.scan().items();
    }

    public Iterable<Employee> findEmployee(final Integer employeeId) {
        DynamoDbTable<Employee> employeeTable = getTable();

        // Create a QueryConditional object that is used in the query operation
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(employeeId).build());

        Iterable<Employee> result = employeeTable.query(r -> r.queryConditional(queryConditional)).items();
        return result;
    }

    // DELETE
    public void deleteById(final Integer employeeId) {
        DynamoDbTable<Employee> employeeTable = getTable();

        Key key = Key.builder().partitionValue(employeeId).build();

        DeleteItemEnhancedRequest deleteRequest = DeleteItemEnhancedRequest
                .builder()
                .key(key)
                .build();

        employeeTable.deleteItem(deleteRequest);
    }

    // UPDATE
    public Employee update(final Employee employee) {
        DynamoDbTable<Employee> employeeTable = getTable();
        Key key = Key.builder().partitionValue(employee.getEmployeeId()).build();
        Employee employeeRegister = employeeTable.getItem(r->r.key(key));
        // Info to update
        employeeRegister.setName(employee.getName());
        employeeRegister.setLastname(employee.getLastname());
        employeeRegister.setActive(employee.getActive());
        // Update
        employeeTable.updateItem(employeeRegister);
        return employee;
    }

    // PRIVATE METHODS
    private DynamoDbTable<Employee> getTable() {
        DynamoDbTable<Employee> recordingTable = dynamoDbEnhancedClient.table("Employee", TableSchema.fromBean(Employee.class));
        return recordingTable;
    }
}
