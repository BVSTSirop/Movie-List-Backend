package application;

import application.api.AuthToken;
import application.config.ApiConfiguration;
import application.repository.PersonRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.JSR310DateTimeDeserializerBase;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Application {

    @Autowired
    private ApiConfiguration apiConfiguration;

    private AuthToken authToken;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(PersonRepository userRepository) throws IOException {
        this.authToken = authenticate();

        return args -> userRepository.findAll().forEach(System.out::println);
    }

    private AuthToken authenticate() throws IOException {
        String requestUrl = String.format("https://api.themoviedb.org/3/authentication/token/new?api_key=%s", apiConfiguration.getKey());

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            final HttpGet request  = new HttpGet(requestUrl);

            return client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent().readAllBytes(), AuthToken.class));
        }
    }
}