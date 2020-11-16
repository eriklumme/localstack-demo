package dev.lumme.localstackdemo.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.GetBucketLocationRequest;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

import java.util.List;
import java.util.Optional;

@Service
public class S3Service {

    private static final String FILE_BUCKET = "file_bucket";

    private final S3Client s3;

    public S3Service() {
        s3 = S3Client.builder().endpointOverride(Config.ENDPOINT).region(Config.DEFAULT_REGION).build();

    }

    public List<Bucket> listBuckets() {
        return s3.listBuckets().buckets();
    }

    public Optional<String> findBucketLocation(String bucketName) {
        try {
            GetBucketLocationRequest request = GetBucketLocationRequest.builder().bucket(bucketName).build();
            return Optional.of(s3.getBucketLocation(request).locationConstraintAsString());
        } catch (NoSuchBucketException e) {
            return Optional.empty();
        }
    }
}
