package application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfiguration {

    @Value("${api.key}")
    private String key;

    @Value("${api.baseURL}")
    private String baseUrl;

    public String getKey() {
        return key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
