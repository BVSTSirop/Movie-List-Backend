package application.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class AuthToken {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss 'UTC'", timezone = "UTC")
    @JsonProperty("expires_at")
    private Instant expiry;

    @JsonProperty("request_token")
    private String  token;

    public AuthToken() { }

    public Instant getExpiry() {
        return expiry;
    }

    public void setExpiry(Instant expiry) {
        this.expiry = expiry;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "expiry=" + expiry +
                ", token='" + token + '\'' +
                '}';
    }
}
