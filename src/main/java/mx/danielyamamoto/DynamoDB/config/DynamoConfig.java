package mx.danielyamamoto.DynamoDB.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class DynamoConfig {
    /*
    @Bean
    public DynamoDbClient getDynamoDbClient() {
        //Ver documentación
        //https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/CodeSamples.Java.html#CodeSamples.Java.Credentials
        DynamoDbClient client = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                // The region is meaningless for local DynamoDb but required for client builder validation
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("87kvbg", "j1w7y")))
                .build();
        return client;
        //return DynamoDbClient.builder().region(Region.US_EAST_1).credentialsProvider(credentialsProvider).build();
    }
    */

    @Bean
    public DynamoDbClient getDynamoDbClient() {
        AwsCredentialsProvider credentialsProvider =
                DefaultCredentialsProvider.builder().profileName("default").build();

        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider).build();
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(getDynamoDbClient())
                .build();
    }
}
