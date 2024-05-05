import android.util.Log;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rdsdata.AWSRDSData;
import com.amazonaws.services.rdsdata.AWSRDSDataClientBuilder;
import com.amazonaws.services.rdsdata.model.*;

public class AWSDatabaseManager {
    private static final String TAG = "AWSDatabaseManager";

    // Replace with your AWS database credentials and connection details
    private static final String AWS_ACCESS_KEY = "your_access_key";
    private static final String AWS_SECRET_KEY = "your_secret_key";
    private static final String AWS_REGION = "your_aws_region";
    private static final String AWS_DATABASE_ARN = "your_database_arn";
    private static final String AWS_DATABASE_SECRET_ARN = "your_database_secret_arn";

    public static void insertData(String plateNumber, String timestamp, String coordinates) {
        try {
            // Create AWS credentials
            BasicAWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY);

            // Create AWS RDS Data client
            AWSRDSData rdsDataClient = AWSRDSDataClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.fromName(AWS_REGION))
                    .build();

            // Create parameters for the SQL statement
            java.util.List<SqlParameter> params = new java.util.ArrayList<>();
            params.add(new SqlParameter().withName("plateNumber").withValue(new Field().withStringValue(plateNumber)));
            params.add(new SqlParameter().withName("timestamp").withValue(new Field().withStringValue(timestamp)));
            params.add(new SqlParameter().withName("coordinates").withValue(new Field().withStringValue(coordinates)));

            // Create the SQL statement to insert data
            ExecuteStatementRequest request = new ExecuteStatementRequest()
                    .withDatabase(AWS_DATABASE_ARN)
                    .withSecretArn(AWS_DATABASE_SECRET_ARN)
                    .withSql("INSERT INTO your_table_name (plate_number, timestamp, coordinates) VALUES (:plateNumber, :timestamp, :coordinates)")
                    .withParameters(params);

            // Execute the SQL statement
            rdsDataClient.executeStatement(request);

            // Log success message
            Log.d(TAG, "Data inserted into the AWS SQL database.");
        } catch (Exception e) {
            // Log error message
            Log.e(TAG, "Failed to insert data into the AWS SQL database: " + e.getMessage());
        }
    }
}
