package pl.atk.shopisticated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Shopisticated backend main class.
 */
@SpringBootApplication
public class ShopisticatedApplication {

    public static void main(String[] args) {
        new SpringApplication(ShopisticatedApplication.class).run(args);
    }
}
