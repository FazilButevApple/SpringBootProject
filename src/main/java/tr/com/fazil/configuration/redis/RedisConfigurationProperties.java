package tr.com.fazil.configuration.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfigurationProperties {

    private String host;
    private int port;
    private String password;
    private Integer minIdle;
    private Integer maxIdle;
    private Integer maxTotal;
    private Integer maxWaitMillis;
    private Integer connectTimeout;
    private Integer readTimeout;
    private List<String> nodes;
    private Integer maxRedirects;

}

