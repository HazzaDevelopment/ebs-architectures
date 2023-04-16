package org.ebs.ingestor;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import org.ebs.shared.serverless.ConnectionFactory;
import org.ebs.shared.serverless.config.ApplicationConfig;
import org.ebs.shared.serverless.config.ConfigKey;
import org.ebs.shared.serverless.dao.*;
import org.ebs.shared.serverless.entity.Article;
import org.ebs.shared.serverless.entity.Graph;
import org.ebs.shared.serverless.entity.Heatmap;
import org.ebs.shared.serverless.entity.Resource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class Handler implements RequestHandler<SQSEvent, Object> {

    public static final Gson GSON = new GsonBuilder().create();

    public Handler(){
        System.out.println("*** THIS IS PRINTING FROM THE LAMBDA ***");
    }

    //convenience method so we can run handleRequest()
    public static void main(String... args){
        Handler handler = new Handler();
        handler.handleRequest(null, null);
    }

    @SneakyThrows
    public Object handleRequest(SQSEvent sqsEvent, Context context) {
        //get the environment
        String environment = System.getenv("ENVIRONMENT");

        //get config based off environment
        Map<ConfigKey, String> config = ApplicationConfig.getConfig(environment);

        //get connection
        Connection conn = new ConnectionFactory(config).getConnection();

        //create DAO's
        GenericDao<Article> articleDao = new ArticleDao(conn);
        GenericDao<Graph> graphDao = new GraphDao(conn);
        GenericDao<Heatmap> heatmapDao = new HeatmapDao(conn);
        GenericDao<Resource> resourceDao = new ResourceDao(conn);

        for(SQSEvent.SQSMessage message : sqsEvent.getRecords()) {
            String body = message.getBody();
            System.out.println("Received: " + body);
            final ObjectNode node = new ObjectMapper().readValue(body, ObjectNode.class);
            String type = node.get("type").asText();
            String data = node.get("data").toString();

            System.out.println("DATA IS: " + data);
            if(type.equals("article")) {
                Article article = GSON.fromJson(data, Article.class);
                articleDao.save(article);
            }
            if(type.equals("graph")) {
                Graph graph = GSON.fromJson(data, Graph.class);
                graphDao.save(graph);
            }
            if(type.equals("heatmap")) {
                Heatmap heatmap = GSON.fromJson(data, Heatmap.class);
                heatmapDao.save(heatmap);
            }
            if(type.equals("resource")) {
                Resource resource = GSON.fromJson(data, Resource.class);
                resourceDao.save(resource);
            }
        }
        System.out.println("Finished processing inserts.");

        System.out.println("Sending message...");
        AwsClientBuilder.EndpointConfiguration s = new AwsClientBuilder.EndpointConfiguration(
                "https://sqs.us-east-2.amazonaws.com/",
                "us-east-2"
        );
        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard().withEndpointConfiguration(s).build();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl("https://sqs.us-east-2.amazonaws.com/539931274965/renderer-queue")
                .withMessageBody("hello world");
        amazonSQS.sendMessage(send_msg_request);
        System.out.println("Finished sending message/");

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "1";
    }
}
