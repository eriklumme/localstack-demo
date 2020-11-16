package dev.lumme.localstackdemo.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;

@Service
public class EmailService {

    private final SesClient client;

    public EmailService() {
        client = SesClient.builder().region(Config.DEFAULT_REGION).endpointOverride(Config.ENDPOINT).build();
    }

    public int sendEmail(String from, String to, String subject, String body) {
        SendEmailRequest request = SendEmailRequest.builder()
                .source(from)
                .destination(Destination.builder().toAddresses(to).build())
                .message(Message.builder()
                    .subject(Content.builder().data(subject).charset("UTF-8").build())
                    .body(Body.builder().text(Content.builder().data(body).charset("UTF-8").build()).build())
                    .build())
                .build();

        SendEmailResponse response = client.sendEmail(request);
        return response.sdkHttpResponse().statusCode();
    }
}
