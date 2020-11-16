package dev.lumme.localstackdemo.controller;

import dev.lumme.localstackdemo.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import dev.lumme.localstackdemo.service.S3Service;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.util.List;
import java.util.Optional;

@RestController
public class LocalStackController {

    private final S3Service s3Service;
    private final EmailService emailService;

    public LocalStackController(S3Service s3Service, EmailService emailService) {
        this.s3Service = s3Service;
        this.emailService = emailService;
    }

    @GetMapping
    public String index() {
        return "LocalStack Demo Application<br>" +
                "---------------------------<br>" +
                "<a href='/buckets'>/buckets</a> - View buckets<br>" +
                "<a href='/emails'>/emails</a> - Send email<br>";
    }

    @GetMapping("/buckets")
    public String buckets() {
        List<Bucket> buckets = s3Service.listBuckets();
        if (buckets.isEmpty()) {
            return "There are no buckets.";
        }
        StringBuilder sb = new StringBuilder("Buckets<br>-------<br>");
        for (Bucket bucket: buckets) {
            sb.append(String.format("<a href='/buckets/%s'>%s</a><br>", bucket.name(), bucket.name()));
        }
        return sb.toString();
    }

    @GetMapping("/buckets/{bucket}")
    public String buckets(@PathVariable("bucket") String bucket) {
        Optional<String> bucketLocation = s3Service.findBucketLocation(bucket);
        return bucketLocation.map(s -> "Bucket is located in " + s)
                .orElseGet(() -> String.format("Bucket '%s' not found", bucket));
    }

    @GetMapping("/emails")
    public String emails() {
        String from = "from@example.com", to = "to@example.com", subject = "Test mail", body = "Test body";
        int status = emailService.sendEmail(from, to, subject, body);
        return "E-mail sent, status code is " + status;
    }
}
