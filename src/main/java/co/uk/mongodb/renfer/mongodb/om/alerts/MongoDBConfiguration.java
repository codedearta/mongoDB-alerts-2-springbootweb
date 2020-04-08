package co.uk.mongodb.renfer.mongodb.om.alerts;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MongoDBConfiguration {

    @Value("${mongodb.uri}")
    private String mongoURI;

    @Value("${mongodb.opsmanager.scheme}")
    private String scheme;

    @Value("${mongodb.opsmanager.host}")
    private String host;

    @Value("${mongodb.opsmanager.port}")
    private int port;

    @Value("${mongodb.opsmanager.project-id}")
    private String projectId;

    @Value("${mongodb.opsmanager.api.baseurl}")
    private String baseUrl;

    @Value("${mongodb.opsmanager.api.key.public}")
    private String publicKey;

    @Value("${mongodb.opsmanager.api.key.private}")
    private String privateKey;

    @Bean
    public MongoClient mongoClient() {

        return MongoClients.create(mongoURI);
    }

    @Bean
    public String alertsUrl() {
        return scheme + "://" + host + ":" + port + "/" + baseUrl + "/" + projectId + "/alerts";
    }

    @Bean
    public RestTemplate restTemplate() {
        new HttpHost(baseUrl);
        HttpHost omhost = new HttpHost(host, port, scheme);
        CloseableHttpClient client = HttpClientBuilder.create().
                setDefaultCredentialsProvider(provider()).useSystemProperties().build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactoryDigestAuth(client, omhost);

        return new RestTemplate(requestFactory);
    }

    private CredentialsProvider provider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials(publicKey, privateKey);
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }
}
