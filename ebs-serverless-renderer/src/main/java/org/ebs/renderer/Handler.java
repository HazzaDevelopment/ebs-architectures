package org.ebs.renderer;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import lombok.SneakyThrows;
import org.ebs.shared.serverless.dao.*;
import org.ebs.shared.serverless.entity.Article;
import org.ebs.shared.serverless.entity.Graph;
import org.ebs.shared.serverless.entity.Heatmap;
import org.ebs.shared.serverless.entity.Resource;
import org.ebs.shared.serverless.ConnectionFactory;
import org.ebs.shared.serverless.config.ApplicationConfig;
import org.ebs.shared.serverless.config.ConfigKey;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Handler implements RequestHandler<SQSEvent, Object> {

    private final GraphCreator graphCreator = new GraphCreator();

    public Handler(){
        System.out.println("*** THIS IS PRINTING FROM THE RENDERER LAMBDA ***");
    }

    //convenience method so we can run handleRequest()
    public static void main(String... args){
        Handler handler = new Handler();
        handler.handleRequest(null, null);
    }

    @SneakyThrows
    public Object handleRequest(SQSEvent sqsEvent, com.amazonaws.services.lambda.runtime.Context awsCtx) {
        //get the environment
        String environment = System.getenv("ENVIRONMENT");

        //get config based off environment
        Map<ConfigKey, String> config = ApplicationConfig.getConfig(environment);

        System.out.println("Getting connection with JDBC URL: " + config.get(ConfigKey.JDBC_URL));

        //get connection
        Connection conn = new ConnectionFactory(config).getConnection();

        System.out.println("Connected to DB");

        S3Client client = S3Client.builder().build();

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");

        //create DAO's
        GenericDao<Graph> graphDao = new GraphDao(conn);
        GenericDao<Article> articleDao = new ArticleDao(conn);
        GenericDao<Heatmap> heatmapDao = new HeatmapDao(conn);
        GenericDao<Resource> resourceDao = new ResourceDao(conn);

        Graph crimeGraphDb = graphDao.findByType("crime");
        Graph covidGraphDb = graphDao.findByType("covid");
        Graph warningGraphDb = graphDao.findByType("warning");
        List<Heatmap> heatmapsDb = heatmapDao.findAll();

        String crimeGraph = graphCreator.createStandardGraph("crimeChart", "line", crimeGraphDb.getLabels(), crimeGraphDb.getTitleLabel(), crimeGraphDb.getData());
        String covidGraph = graphCreator.createStandardGraph("covidCasesChart", "line", covidGraphDb.getLabels(), covidGraphDb.getTitleLabel(), covidGraphDb.getData());
        String warningGraph = graphCreator.createStandardGraph("warningChart", "bar", warningGraphDb.getLabels(), warningGraphDb.getTitleLabel(), warningGraphDb.getData());
        String heatmapGraph = graphCreator.createHeatmapGraph(heatmapsDb);

        List<Article> articles = articleDao.findAll();
        List<Resource> resources = resourceDao.findAll();

        Context context = new Context();
        context.setVariable("resources", resources);
        context.setVariable("articles", articles);
        context.setVariable("crimeGraph", crimeGraph);
        context.setVariable("covidGraph", covidGraph);
        context.setVariable("warningGraph", warningGraph);
        context.setVariable("heatmapGraph", heatmapGraph);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        String renderedIndexPage = templateEngine.process("index", context);
        System.out.println(renderedIndexPage);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(config.get(ConfigKey.BUCKET_NAME))
                .contentType("text/html")
                .key("index.html").build();

        client.putObject(request, RequestBody.fromBytes(renderedIndexPage.getBytes()));

        if(environment.equals("DEV")) {
            Files.write(Paths.get("out/index.html"), renderedIndexPage.getBytes());
            Files.copy(Paths.get("src/main/resources/css/main.css"), Paths.get("out/css/main.css"), REPLACE_EXISTING);
        }

        //upload index page to s3
        for(Resource resource: resources){
            Context resourceContext = new Context();
            resourceContext.setVariable("mainTitle", resource.getTitle());
            resourceContext.setVariable("descriptionHeading", resource.getDescriptionHeading());
            resourceContext.setVariable("descriptionBody", resource.getDescriptionPanel());
            resourceContext.setVariable("adviceHeading", resource.getAdvicePanelHeading());
            resourceContext.setVariable("adviceBody", resource.getAdvicePanel());
            resourceContext.setVariable("listItemsHeading", resource.getListItemsHeading());
            resourceContext.setVariable("listItems", resource.getListItems());

            String resourceResult = templateEngine.process("resource", resourceContext);

            if(environment.equals("DEV")) {
                String path = "out/resources/" + resource.getUrl() + ".html";
                Files.write(Paths.get(path), resourceResult.getBytes());
            }

            PutObjectRequest resourceRequest = PutObjectRequest.builder()
                    .bucket(config.get(ConfigKey.BUCKET_NAME))
                    .contentType("text/html")
                    .key("resources/" + resource.getUrl() + ".html").build();

            client.putObject(resourceRequest, RequestBody.fromBytes(resourceResult.getBytes()));
            System.out.println(resourceResult);
        }

        System.out.println("Finished processing");

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "1";
    }
}
